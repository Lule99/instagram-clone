package com.instaclone.instaclone.model;

import com.instaclone.instaclone.model.enums.AgeCategory;
import com.instaclone.instaclone.model.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
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

    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private AgeCategory ageCategory;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String name;

    private String bio;

    private String profilePicture;

    private Boolean viral;

    @OneToOne
    private Location location;

    @OneToMany
    private List<Post> posts;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Profile> followers;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Profile> following;

    @OneToOne
    private Categorization postCategorization;

    @OneToOne
    private Categorization followCategorization;


}
