package com.instaclone.instaclone.model.facts;

import com.instaclone.instaclone.model.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DistanceCalculated {
    private Location location;
    private double distance;
}
