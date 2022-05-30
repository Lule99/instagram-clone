package com.instaclone.instaclone.model.facts;

import com.instaclone.instaclone.model.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileInFocus {
    private Profile profile;
}
