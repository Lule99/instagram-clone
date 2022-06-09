package com.instaclone.instaclone.service;

import com.instaclone.instaclone.dto.user.ChangeFollowingStatusDto;
import com.instaclone.instaclone.dto.user.ProfileInfoDto;
import com.instaclone.instaclone.dto.user.UpdateUserDto;
import com.instaclone.instaclone.dto.user.UserDto;
import com.instaclone.instaclone.model.Profile;

import java.util.List;

public interface ProfileService extends JPAService<Profile> {
    UserDto updateUser(UpdateUserDto dto);

    void followUnfollow(ChangeFollowingStatusDto dto);

    List<ProfileInfoDto> getSuggestions(String username);
}
