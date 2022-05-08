package com.instaclone.instaclone.service;

import com.instaclone.instaclone.dto.reaction.*;
import com.instaclone.instaclone.model.Post;
import com.instaclone.instaclone.model.Reaction;

public interface ReactionService extends JPAService<Reaction> {

    ToggleReactionResponseDto togglePostReaction(ToggleReactionDto dto, String username);

    String myPostReaction(Long id, String username);

}
