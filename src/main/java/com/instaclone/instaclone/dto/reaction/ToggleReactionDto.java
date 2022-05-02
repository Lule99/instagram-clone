package com.instaclone.instaclone.dto.reaction;

import com.instaclone.instaclone.model.enums.ReactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ToggleReactionDto {
    @NotNull
    private ReactionType reactionType;
    @NotNull
    private Long entityId;
}
