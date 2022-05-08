package com.instaclone.instaclone.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Lob;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Categorization extends BaseEntity{

    private LocalDateTime lastUpdate;

    @Lob
    private List<Double> categories;
}
