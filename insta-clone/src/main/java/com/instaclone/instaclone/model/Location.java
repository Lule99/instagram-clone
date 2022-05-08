package com.instaclone.instaclone.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Location extends BaseEntity {

    private String state;
    private String region;

    @Column(nullable = false)
    private String locationName;
    @Column(nullable = false)
    private Long longitude;
    @Column(nullable = false)
    private Long latitude;
}
