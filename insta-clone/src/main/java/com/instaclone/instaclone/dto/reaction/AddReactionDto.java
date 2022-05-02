package com.instaclone.instaclone.dto.reaction;

import com.instaclone.instaclone.model.enums.ReactionType;
import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddReactionDto {

    @NotNull
    private ReactionType reactionType;
    @NotNull
    private Long entityId;
}
