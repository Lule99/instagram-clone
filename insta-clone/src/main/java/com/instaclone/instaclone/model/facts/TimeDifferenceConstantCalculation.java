package com.instaclone.instaclone.model.facts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimeDifferenceConstantCalculation {
    private LocalDateTime lastFollowUpdate;
    private LocalDateTime lastPostUpdate;
    private Double dayDifference;
    private boolean active;
    private Double boostFollowCoef;
    private Double boostPostCoef;

    public TimeDifferenceConstantCalculation(LocalDateTime follow, LocalDateTime post) {
        this.lastFollowUpdate = follow;
        this.lastPostUpdate = post;
        this.dayDifference = (double) DAYS.between(follow, post);
        this.active = false;
        this.boostFollowCoef = 0.0;
        this.boostPostCoef = 0.0;
    }
}
