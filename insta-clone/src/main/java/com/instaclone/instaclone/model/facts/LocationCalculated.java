package com.instaclone.instaclone.model.facts;

import com.instaclone.instaclone.model.Profile;
import com.instaclone.instaclone.model.facts.enums.LocationCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LocationCalculated {
    private Profile to;
    private LocationCategory locationCategory;
}
