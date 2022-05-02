package com.instaclone.instaclone.dto.reaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ToggleReactionResponseDto {
    private String reactionType;
    private boolean update;
}
