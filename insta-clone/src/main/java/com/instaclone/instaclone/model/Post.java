package com.instaclone.instaclone.model;

import com.instaclone.instaclone.model.enums.Category;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Post extends BaseEntity {

    @ManyToOne
    private Profile publisher;

    @Column(nullable = false)
    private String text;

    @OneToOne
    private Location location;

    private String picture;

    private Boolean viral;

    @OneToMany
    private List<Reaction> reactions;

    private Integer numOfShares;

    @ElementCollection(targetClass = Category.class)
    private List<Category> categories;
}
