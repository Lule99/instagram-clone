package com.instaclone.instaclone.service.impl;

import com.instaclone.instaclone.converter.user.UserToProfileInfoDtoConverter;
import com.instaclone.instaclone.dto.user.ChangeFollowingStatusDto;
import com.instaclone.instaclone.dto.user.ProfileInfoDto;
import com.instaclone.instaclone.dto.user.UpdateUserDto;
import com.instaclone.instaclone.dto.user.UserDto;
import com.instaclone.instaclone.exception.NotFoundException;
import com.instaclone.instaclone.model.Profile;
import com.instaclone.instaclone.model.User;
import com.instaclone.instaclone.repository.ProfileRepository;
import com.instaclone.instaclone.repository.UserRepository;
import com.instaclone.instaclone.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl extends JPAServiceImpl<Profile> implements ProfileService {

    private final ProfileRepository repository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserToProfileInfoDtoConverter userToProfileInfoDtoConverter = new UserToProfileInfoDtoConverter();

    @Transactional
    @Override
    public UserDto updateUser(UpdateUserDto updateUserDto) {
        User user = userRepository.findByUsername(updateUserDto.getUsername());
        Profile regularUser = user.getProfile();

        if (regularUser == null) throw new NotFoundException("Nije pronadjen korisnik sa tim usernameom!");

        regularUser.setName(updateUserDto.getName());
        regularUser.setBio(updateUserDto.getBio());
        regularUser.setProfilePicture(updateUserDto.getProfilePicture());

        save(regularUser);

        return modelMapper.map(regularUser, UserDto.class);
    }

    @Transactional
    @Override
    public void followUnfollow(ChangeFollowingStatusDto dto) {
        User user = userRepository.findByUsername(dto.getMyUsername());
        Profile profile = user.getProfile();
        if (profile == null) throw new NotFoundException("Nije pronadjen korisnik!");

        User toChangeStatusUser = userRepository.findByUsername(dto.getOtherUsername());
        Profile toChangeStatus = toChangeStatusUser.getProfile();
        if (toChangeStatus == null) throw new NotFoundException("Nije pronadjen korisnik!");

        if (profile.getFollowing().contains(toChangeStatus)) {
            profile.getFollowing().remove(toChangeStatus);
            toChangeStatus.getFollowers().remove(profile);
        } else {
            profile.getFollowing().add(toChangeStatus);
            toChangeStatus.getFollowers().add(profile);
        }

        save(profile);
        save(toChangeStatus);
    }

    @Override
    public Page<ProfileInfoDto> getSuggestions(String username, int page, int size) {

        //TODO logika za sugestije, sad vraca sve...
        page = Math.max(page, 0);
        size = Math.max(size, 1);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "timeCreated");
        Page<User> pageOfProfiles = userRepository.findAll(pageable);
        return pageOfProfiles.map(userToProfileInfoDtoConverter::convert);
    }


    @Override
    protected JpaRepository<Profile, Long> getEntityRepository() {
        return repository;
    }
}
