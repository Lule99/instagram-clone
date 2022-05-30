package com.instaclone.instaclone.model.facts;

import com.instaclone.instaclone.model.Profile;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileOfInterest {
    private Profile profile;
    private double similarity;
    private List<Double> differences;
    private double totalDiff;
    private boolean ofInterest;


    public ProfileOfInterest(Profile p, List<Double> interest) {
        this.differences = new ArrayList<>(16);
        for (int i = 0; i < interest.size(); i++) {
            double diff = Math.abs(p.getPostCategorization().getCategories().get(i) - interest.get(i));
            this.differences.add(diff);
            this.totalDiff += diff;
            this.ofInterest = false;
            this.profile = p;
        }
    }
}
