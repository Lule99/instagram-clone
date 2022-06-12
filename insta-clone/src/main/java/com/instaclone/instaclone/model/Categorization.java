package com.instaclone.instaclone.model;

import com.instaclone.instaclone.model.enums.CategorizationType;
import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Categorization extends BaseEntity {

    private LocalDateTime lastUpdate;

    private CategorizationType categorizationType;

    @ElementCollection(targetClass = Double.class)
    private List<Double> categories;

    public Categorization(Categorization c) {
        lastUpdate = c.lastUpdate;
        categorizationType = c.categorizationType;
        categories = new ArrayList<>(c.categories);
    }
}
