package com.instaclone.instaclone.model.facts;

import com.instaclone.instaclone.model.Categorization;
import com.instaclone.instaclone.model.Location;
import com.instaclone.instaclone.model.Post;
import com.instaclone.instaclone.model.Profile;
import com.instaclone.instaclone.model.enums.AgeCategory;
import com.instaclone.instaclone.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileFact {

    private Long id;

    private LocalDate birthday;

    private AgeCategory ageCategory;

    private Gender gender;

    private String name;

    private String bio;

    private String profilePicture;

    private Boolean viral;

    private Location location;

    private List<Long> postsIds;

    private Set<Long> followersIds;

    private Set<Long> followingIds;

    private Categorization postCategorization;

    private Categorization followCategorization;

    private LocalDateTime timeCreated;


    public ProfileFact(Profile p) {

        this.id = p.getId();
        this.birthday = p.getBirthday();
        this.ageCategory = p.getAgeCategory();
        this.gender = p.getGender();
        this.name = p.getName();
        this.bio = p.getBio();
        this.profilePicture = p.getProfilePicture();
        this.viral = p.getViral();
        this.location = p.getLocation();
        this.postsIds = p.getPosts().stream().map(Post::getId).collect(Collectors.toList());
        this.followersIds = p.getFollowers().stream().map(Profile::getId).collect(Collectors.toSet());
        this.followingIds = p.getFollowing().stream().map(Profile::getId).collect(Collectors.toSet());
        this.postCategorization = p.getPostCategorization();
        this.followCategorization = p.getFollowCategorization();
        this.timeCreated = p.getTimeCreated();
    }


}
