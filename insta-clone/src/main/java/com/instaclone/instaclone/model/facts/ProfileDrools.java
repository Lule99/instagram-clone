package com.instaclone.instaclone.model.facts;

import com.instaclone.instaclone.model.Categorization;
import com.instaclone.instaclone.model.Location;
import com.instaclone.instaclone.model.enums.AgeCategory;
import com.instaclone.instaclone.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDrools {
    private Long id;
    private Long user;
    private LocalDate birthday;
    private AgeCategory ageCategory;
    private Gender gender;
    private Location location;
    private Set<Long> followers;
    private Set<Long> following;
    private Categorization postCategorization;
    private Categorization followCategorization;
}
