package com.instaclone.instaclone.model.facts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileForCalculatingSuggestions {
    private ProfileDrools profile;
    private boolean processed;
}
