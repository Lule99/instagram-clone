package com.instaclone.instaclone.model.facts;

import com.instaclone.instaclone.model.Profile;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileSuggestion {
    private ProfileDrools profile;
    private double similarity;
    private Boolean locationCalculated;
}
