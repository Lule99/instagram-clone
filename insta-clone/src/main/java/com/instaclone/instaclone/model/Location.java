package com.instaclone.instaclone.model;

import lombok.*;
import org.kie.api.definition.type.Position;

import javax.persistence.Column;
import javax.persistence.Entity;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Location extends BaseEntity {

    @Position(0)
    @Column(nullable = false)
    private String name;
    @Position(1)
    @Column(nullable = false)
    private String parent;
    @Column(nullable = false)
    private String locationName;
    @Column(nullable = false)
    private Double longitude;
    @Column(nullable = false)
    private Double latitude;
}
