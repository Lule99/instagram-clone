package com.instaclone.instaclone.service;

import com.instaclone.instaclone.dto.auth.RegistrationDto;
import com.instaclone.instaclone.dto.user.ChangePasswordDto;
import com.instaclone.instaclone.dto.user.ProfileInfoDto;
import com.instaclone.instaclone.dto.user.ResetPasswordDto;
import com.instaclone.instaclone.dto.user.UserSearchResultDto;
import com.instaclone.instaclone.model.Profile;
import com.instaclone.instaclone.model.User;

import java.util.List;

public interface UserService extends JPAService<User> {
    User findByUsername(String username);

    User findByEmail(String email);

    Boolean registration(RegistrationDto registrationDto);

    Boolean changePassword(ChangePasswordDto changePasswordDto);

    void sendMailForResetPassword(String email);

    void createPasswordToken(String token, User user);

    Boolean isTokenValid(String token);

    void resetPassword(ResetPasswordDto dto, String token);

    List<UserSearchResultDto> findUsers(String query);

    ProfileInfoDto getProfileInfo(String username);

    Boolean checkIfUserFollowsUser(String username, String followedUsername);

    List<Profile> getProfilesByViral(Boolean viral);

}
