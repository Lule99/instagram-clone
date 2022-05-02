package com.instaclone.instaclone.controller;

import com.instaclone.instaclone.dto.reaction.*;
import com.instaclone.instaclone.service.ReactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reaction")
public class ReactionController {

    private final ReactionService reactionService;

    @PreAuthorize("hasAnyAuthority('REGULAR_USER')")
    @PostMapping("/add-comment-reaction")
    public ReactionDto addCommentReaction(@RequestBody @Validated AddReactionDto dto, Authentication authentication) {
        return reactionService.addCommentReaction(dto, authentication.getName());
    }

    @PreAuthorize("hasAnyAuthority('REGULAR_USER')")
    @DeleteMapping("/remove-comment-reaction")
    public Boolean removeCommentReaction(@RequestBody @Validated RemoveReactionDto dto, Authentication authentication) {
        reactionService.removeCommentReaction(dto, authentication.getName());
        return true;
    }

    @PreAuthorize("hasAnyAuthority('REGULAR_USER')")
    @PostMapping("/toggle-post-reaction")
    public ToggleReactionResponseDto togglePostReaction(@RequestBody @Validated ToggleReactionDto dto, Authentication authentication) {
        return reactionService.togglePostReaction(dto, authentication.getName());
    }

    @PreAuthorize("hasAnyAuthority('REGULAR_USER')")
    @GetMapping("/my-post-reaction/{id}")
    public String myPostReaction(@PathVariable Long id, Authentication authentication) {
        return reactionService.myPostReaction(id, authentication.getName());
    }

}
