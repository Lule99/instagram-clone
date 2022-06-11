package com.instaclone.instaclone.converter.user;

import com.instaclone.instaclone.converter.Converter;
import com.instaclone.instaclone.model.Profile;
import com.instaclone.instaclone.model.facts.ProfileDrools;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ProfileToProfileDrools extends Converter<Profile, ProfileDrools> {

    @Override
    public ProfileDrools convert(Profile source) {
        ProfileDrools profileDrools = new ProfileDrools();
        profileDrools.setId(source.getId());
        profileDrools.setUser(source.getUser().getId());
        profileDrools.setBirthday(source.getBirthday());
        profileDrools.setAgeCategory(source.getAgeCategory());
        profileDrools.setGender(source.getGender());
        profileDrools.setLocation(source.getLocation());
        profileDrools.setPostCategorization(source.getPostCategorization());
        profileDrools.setFollowCategorization(source.getFollowCategorization());
        Set<Long> followers = new HashSet<>();
        source.getFollowers().forEach(f -> followers.add(f.getId()));

        Set<Long> following = new HashSet<>();
        source.getFollowing().forEach(f -> following.add(f.getId()));

        profileDrools.setFollowers(followers);
        profileDrools.setFollowing(following);


        return profileDrools;
    }
}
