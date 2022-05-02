package com.instaclone.instaclone.converter.user;

import com.instaclone.instaclone.converter.Converter;
import com.instaclone.instaclone.dto.auth.RegistrationDto;
import com.instaclone.instaclone.model.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

@Component
public class RegistrationDtoToProfile extends Converter<RegistrationDto, Profile> {
    @Override
    public Profile convert(RegistrationDto source) {
        Profile profile = getModelMapper().map(source, Profile.class);
        profile.setPosts(new ArrayList<>());
        profile.setFollowers(new HashSet<>());
        profile.setFollowing(new HashSet<>());
        profile.setTimeCreated(LocalDateTime.now());
        return profile;
    }
}
