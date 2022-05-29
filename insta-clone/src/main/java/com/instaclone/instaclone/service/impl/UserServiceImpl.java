package com.instaclone.instaclone.service.impl;

import com.instaclone.instaclone.converter.user.RegistrationDtoToProfile;
import com.instaclone.instaclone.converter.user.RegistrationDtoToUser;
import com.instaclone.instaclone.converter.user.UserToProfileInfoDtoConverter;
import com.instaclone.instaclone.converter.user.UserToUserSearchResultDto;
import com.instaclone.instaclone.dto.auth.RegistrationDto;
import com.instaclone.instaclone.dto.user.ChangePasswordDto;
import com.instaclone.instaclone.dto.user.ProfileInfoDto;
import com.instaclone.instaclone.dto.user.ResetPasswordDto;
import com.instaclone.instaclone.dto.user.UserSearchResultDto;
import com.instaclone.instaclone.exception.BadCredentialsException;
import com.instaclone.instaclone.exception.EmailException;
import com.instaclone.instaclone.exception.NotFoundException;
import com.instaclone.instaclone.exception.PasswordTokenException;
import com.instaclone.instaclone.model.PasswordToken;
import com.instaclone.instaclone.model.Profile;
import com.instaclone.instaclone.model.User;
import com.instaclone.instaclone.repository.PasswordTokenRepository;
import com.instaclone.instaclone.repository.ProfileRepository;
import com.instaclone.instaclone.repository.UserRepository;
import com.instaclone.instaclone.service.CategorizationService;
import com.instaclone.instaclone.service.ImageService;
import com.instaclone.instaclone.service.LocationService;
import com.instaclone.instaclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends JPAServiceImpl<User> implements UserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final RegistrationDtoToUser registrationDtoToUser;
    private final RegistrationDtoToProfile registrationDtoToProfile;
    private final PasswordEncoder passwordEncoder;
    private final PasswordTokenRepository passwordTokenRepository;
    private final ImageService imageService;
    private final EmailService emailService;
    private final UserToUserSearchResultDto userToUserSearchResultDto;
    private final UserToProfileInfoDtoConverter userToProfileInfoDtoConverter
            = new UserToProfileInfoDtoConverter();
    private final LocationService locationService;
    private final CategorizationService categorizationService;
    private final KieContainer kieContainer;

    private static final String FRONT_APP_URL = "http://localhost:3000";

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null || !user.getActive())
            throw new NotFoundException(String.format("Korisnik %s nije pronadjen!", username));

        return user;
    }

    @Override
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null || !user.getActive())
            throw new NotFoundException(String.format("Korisnik %s nije pronadjen!", email));

        return user;
    }

    @Override
    public Boolean registration(RegistrationDto registrationDto) {
        String picture = registrationDto.getProfilePicture();
        registrationDto.setProfilePicture("");

        User user = registrationDtoToUser.convert(registrationDto);
        Profile profile = registrationDtoToProfile.convert(registrationDto);
        locationService.save(profile.getLocation());
        categorizationService.save(profile.getPostCategorization());
        categorizationService.save(profile.getFollowCategorization());

        if (userRepository.findByUsername(registrationDto.getUsername()) != null)
            throw new BadCredentialsException("Username vec postoji!");
        if (!registrationDto.getPassword().equals(registrationDto.getRepeatedPassword()))
            throw new BadCredentialsException("Sifre se ne poklapaju!");

        KieSession kieSession = kieContainer.newKieSession("testSession");
        kieSession.getAgenda().getAgendaGroup( "age-categories" ).setFocus();
        kieSession.insert(profile);
        kieSession.fireAllRules();
        kieSession.dispose();

        profileRepository.save(profile);
        user.setProfile(profile);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        profile.setUser(user);
        profileRepository.save(profile);

        if (picture.equals(""))
            profile.setProfilePicture("/static/users/default.jpg");
        else
            profile.setProfilePicture(imageService.uploadImage(picture, profile.getId(), "users"));
        profileRepository.save(profile);
        return true;
    }

    @Override
    public Boolean changePassword(ChangePasswordDto changePasswordDto) {
        User user = userRepository.findByUsername(changePasswordDto.getUsername());
        if (user == null)
            throw new NotFoundException("Korisnik nije pronadjen!");

        if (!passwordEncoder.matches(changePasswordDto.getPassword(), user.getPassword()))
            throw new BadCredentialsException("Sifre se ne poklapaju!");

        if (!changePasswordDto.getNewPassword().equals(changePasswordDto.getRepeatedPassword()))
            throw new BadCredentialsException("Sifre se ne poklapaju!");

        user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));

        this.save(user);

        return true;
    }

    @Override
    public void sendMailForResetPassword(String email) {
        User user = findByEmail(email);
        String token = UUID.randomUUID().toString();

        createPasswordToken(token, user);

        try {
            emailService.sendEmailAsync("",
                    user.getEmail(), "Password reset",
                    String.format("Resetujte vasu sifru, blabla\n\n, %s/reset-password/%s", FRONT_APP_URL, token));

        } catch (InterruptedException e) {
            throw new EmailException("Greska sa mejl servisom, pokusajte kasnije!");
        }
    }

    @Override
    public void createPasswordToken(String token, User user) {
        PasswordToken myToken = new PasswordToken();
        myToken.setToken(token);
        myToken.setUser(user);
        myToken.setTimeCreated(LocalDateTime.now());
        myToken.setExpiryDate(LocalDate.now().plusDays(1));
        passwordTokenRepository.save(myToken);
    }

    @Override
    public Boolean isTokenValid(String token) {
        PasswordToken passwordToken = passwordTokenRepository.findByToken(token);
        return passwordToken != null && !passwordToken.getExpiryDate().isBefore(LocalDate.now());
    }

    @Override
    public void resetPassword(ResetPasswordDto dto, String token) {
        if (!isTokenValid(token))
            throw new PasswordTokenException("Token nije validan!");

        if (!dto.getPassword().equals(dto.getRepeatedPassword()))
            throw new BadCredentialsException("Lozinke se ne poklapaju!");

        PasswordToken passwordToken = passwordTokenRepository.findByToken(token);
        User user = passwordToken.getUser();

        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        save(user);
        passwordTokenRepository.delete(passwordToken);
    }

    @Override
    public List<UserSearchResultDto> findUsers(String query) {
        return userRepository.findFirst10ByUsernameContainingIgnoringCase(query)
                .stream().map(userToUserSearchResultDto::convert).collect(Collectors.toList());
    }

    @Override
    public ProfileInfoDto getProfileInfo(String username) {
        User user = findByUsername(username);

        return userToProfileInfoDtoConverter.convert(user);
    }

    @Override
    @Transactional
    public Boolean checkIfUserFollowsUser(String username, String followedUsername) {
        User user = findByUsername(username);
        User followed = findByUsername(followedUsername);

        return user.getProfile().getFollowing().contains(followed.getProfile());
    }

    @Override
    protected JpaRepository<User, Long> getEntityRepository() {
        return this.userRepository;
    }
}
