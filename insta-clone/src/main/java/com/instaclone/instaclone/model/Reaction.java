package com.instaclone.instaclone.model;

import com.instaclone.instaclone.model.enums.ReactionKind;
import com.instaclone.instaclone.model.enums.ReactionType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reaction extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private ReactionType reactionType;

    @OneToOne
    private Profile profile;

    @Enumerated(EnumType.STRING)
    private ReactionKind reactionKind;

    @ManyToOne
    private Post post;

}
