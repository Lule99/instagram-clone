package com.instaclone.instaclone.model;

import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Categorization extends BaseEntity {

    private LocalDateTime lastUpdate;

    @ElementCollection(targetClass = Double.class)
    private List<Double> categories;
}
