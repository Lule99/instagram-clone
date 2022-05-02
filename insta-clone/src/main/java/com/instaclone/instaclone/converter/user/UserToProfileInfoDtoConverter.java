package com.instaclone.instaclone.converter.user;

import com.instaclone.instaclone.converter.Converter;
import com.instaclone.instaclone.dto.user.ProfileInfoDto;
import com.instaclone.instaclone.exception.NotFoundException;
import com.instaclone.instaclone.model.Profile;
import com.instaclone.instaclone.model.User;
import org.springframework.transaction.annotation.Transactional;

public class UserToProfileInfoDtoConverter extends Converter<User, ProfileInfoDto> {

    @Override
    @Transactional
    public ProfileInfoDto convert(User user) {
        Profile profile = user.getProfile();
        if(profile == null)
            throw new NotFoundException("Profile Not Found!");

        return ProfileInfoDto.builder()
                .username(user.getUsername())
                .postsNumber(profile.getPosts().size())
                .followersNumber(profile.getFollowers().size())
                .followingNumber(profile.getFollowing().size())
                .name(profile.getName())
                .profilePicture(profile.getProfilePicture())
                .bio(profile.getBio())
                .build();
    }
}
