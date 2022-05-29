package com.instaclone.instaclone.model.facts;

import com.instaclone.instaclone.model.Profile;
import com.instaclone.instaclone.model.facts.enums.ToggleFollowType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FollowUnfollow {
    private Profile myProfile;
    private Profile otherProfile;
    private ToggleFollowType type;
}
