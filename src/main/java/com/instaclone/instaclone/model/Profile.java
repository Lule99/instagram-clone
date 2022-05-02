package com.instaclone.instaclone.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Profile extends BaseEntity{

    @OneToOne
    private User user;

    private String name;

    private String bio;

    private String profilePicture;

    @OneToMany
    private List<Post> posts;

    private Boolean publicProfile;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Profile> followers;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Profile> following;


}
