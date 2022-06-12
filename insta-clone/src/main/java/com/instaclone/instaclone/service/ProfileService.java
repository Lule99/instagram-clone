package com.instaclone.instaclone.service;

import com.instaclone.instaclone.dto.user.*;
import com.instaclone.instaclone.model.Profile;

import java.util.List;

public interface ProfileService extends JPAService<Profile> {
    UserDto updateUser(UpdateUserDto dto);

    void followUnfollow(ChangeFollowingStatusDto dto);

    Suggestions getSuggestions(String username);
}
