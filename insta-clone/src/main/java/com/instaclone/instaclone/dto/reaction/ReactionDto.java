package com.instaclone.instaclone.dto.reaction;

import com.instaclone.instaclone.model.enums.ReactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReactionDto {
    private Long id;
    private ReactionType reactionType;
    private String userPicture;
    private String username;
}
