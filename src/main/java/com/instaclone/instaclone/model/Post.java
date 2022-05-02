package com.instaclone.instaclone.model;

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

    private String picture;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Comment>comments;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Reaction> reactions;
}
