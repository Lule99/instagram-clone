package com.instaclone.instaclone.service;

import com.instaclone.instaclone.dto.reaction.*;
import com.instaclone.instaclone.model.Post;
import com.instaclone.instaclone.model.Reaction;

public interface ReactionService extends JPAService<Reaction> {

    ReactionDto addCommentReaction(AddReactionDto dto, String username);

    void removeCommentReaction(RemoveReactionDto dto, String username);

    ToggleReactionResponseDto togglePostReaction(ToggleReactionDto dto, String username);

    String myPostReaction(Long id, String username);

    int countReactionsByPost(Post post);
}
