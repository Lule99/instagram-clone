package com.instaclone.instaclone.converter.user;

import com.instaclone.instaclone.converter.Converter;
import com.instaclone.instaclone.dto.auth.RegistrationDto;
import com.instaclone.instaclone.model.Categorization;
import com.instaclone.instaclone.model.Location;
import com.instaclone.instaclone.model.Profile;
import com.instaclone.instaclone.model.enums.CategorizationType;
import com.instaclone.instaclone.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class RegistrationDtoToProfile extends Converter<RegistrationDto, Profile> {

    @Autowired
    LocationService locationService;


    @Override
    public Profile convert(RegistrationDto source) {
        Profile profile = getModelMapper().map(source, Profile.class);
        profile.setPosts(new ArrayList<>());
        profile.setFollowers(new HashSet<>());
        profile.setFollowing(new HashSet<>());
        profile.setTimeCreated(LocalDateTime.now());
        profile.setViral(false);

        Location location = locationService.preprocessLocation(source.getLocation());

        Categorization postCategorization = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.POST)
                .categories(List.of(1.,1.,1.,1.,1.,1.,1.,1.,1.,1.,1.,1.,1.,1.,1.,1.)).build();

        postCategorization.setTimeCreated();

        Categorization followCategorization = Categorization.builder()
                .lastUpdate(LocalDateTime.now())
                .categorizationType(CategorizationType.FOLLOW)
                .categories(List.of(1.,1.,1.,1.,1.,1.,1.,1.,1.,1.,1.,1.,1.,1.,1.,1.)).build();
        followCategorization.setTimeCreated();

        profile.setLocation(location);
        profile.setPostCategorization(postCategorization);
        profile.setFollowCategorization(followCategorization);

        return profile;
    }
}
