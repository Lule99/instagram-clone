package com.instaclone.instaclone.service.impl;

import com.instaclone.instaclone.dto.reaction.*;
import com.instaclone.instaclone.exception.OperationNotAllowedException;
import com.instaclone.instaclone.model.Comment;
import com.instaclone.instaclone.model.Post;
import com.instaclone.instaclone.model.Reaction;
import com.instaclone.instaclone.model.User;
import com.instaclone.instaclone.model.enums.ReactionKind;
import com.instaclone.instaclone.repository.ReactionRepository;
import com.instaclone.instaclone.service.CommentService;
import com.instaclone.instaclone.service.PostService;
import com.instaclone.instaclone.service.ReactionService;
import com.instaclone.instaclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ReactionServiceImpl extends JPAServiceImpl<Reaction> implements ReactionService {

    private final ReactionRepository reactionRepository;
    private final CommentService commentService;
    private final PostService postService;
    private final UserService userService;

    @PostConstruct
    public void init()
    {
        postService.setReactionService(this);
    }

    @Override
    protected JpaRepository<Reaction, Long> getEntityRepository() {
        return reactionRepository;
    }

    @Transactional
    @Override
    public ReactionDto addCommentReaction(AddReactionDto dto, String username) {
        User user = userService.findByUsername(username);
        Comment comment = commentService.findOne(dto.getEntityId());
        Reaction reaction = reactionRepository.reactionForComment(ReactionKind.COMMENT_REACTION, user.getProfile(), comment, true);

        if (reaction == null) {
            return addCommentReaction(dto, user, comment);
        } else {
            return updateCommentReaction(reaction, dto, user);
        }
    }

    private ReactionDto addCommentReaction(AddReactionDto dto, User user, Comment comment) {
        Reaction newReaction = new Reaction(dto.getReactionType(), user.getProfile(), ReactionKind.COMMENT_REACTION, comment, null);
        newReaction.setTimeCreated(LocalDateTime.now());
        save(newReaction);
        comment.getReactions().add(newReaction);
        commentService.save(comment);

        return new ReactionDto(newReaction.getId(), dto.getReactionType(), user.getProfile().getProfilePicture(), user.getUsername());
    }

    private ReactionDto updateCommentReaction(Reaction reaction, AddReactionDto dto, User user) {
        reaction.setReactionType(dto.getReactionType());
        reaction.setTimeCreated(LocalDateTime.now());
        save(reaction);
        return new ReactionDto(reaction.getId(), dto.getReactionType(), user.getProfile().getProfilePicture(), user.getUsername());
    }

    @Transactional
    @Override
    public void removeCommentReaction(RemoveReactionDto dto, String username) {
        User user = userService.findByUsername(username);
        Comment comment = commentService.findOne(dto.getEntityId());
        Reaction reaction = findOne(dto.getId());

        if (reaction.getProfile() != user.getProfile())
            throw new OperationNotAllowedException("Zabranjeno brisanje tudje reakcije!");

        comment.getReactions().remove(reaction);
        commentService.save(comment);
        delete(dto.getId());
    }

    @Transactional
    @Override
    public ToggleReactionResponseDto togglePostReaction(ToggleReactionDto dto, String username) {
        User user = userService.findByUsername(username);
        Post post = postService.findOne(dto.getEntityId());
        Reaction reaction = reactionRepository.reactionForPost(ReactionKind.POST_REACTION, user.getProfile(), post, true);

        if (reaction == null) {
            return addReactionPost(post, dto, user);
        } else if (reaction.getReactionType() == dto.getReactionType()) {
            return removeReactionPost(reaction, post, dto);
        } else {
            return updateReactionPost(reaction, dto);
        }
    }

    @Override
    public String myPostReaction(Long id, String username) {
        User user = userService.findByUsername(username);
        Post post = postService.findOne(id);
        Reaction reaction = reactionRepository.reactionForPost(ReactionKind.POST_REACTION, user.getProfile(), post, true);

        if (reaction == null)
            return "";
        return reaction.getReactionType().toString();
    }

    @Override
    public int countReactionsByPost(Post post) {
        return reactionRepository.countReactionByPostId(post.getId());
    }

    private ToggleReactionResponseDto addReactionPost(Post post, ToggleReactionDto dto, User user) {
        Reaction newReaction = new Reaction(dto.getReactionType(), user.getProfile(), ReactionKind.POST_REACTION, null, post);
        newReaction.setTimeCreated(LocalDateTime.now());
        save(newReaction);
        post.getReactions().add(newReaction);
        postService.save(post);
        return new ToggleReactionResponseDto(dto.getReactionType().toString(), false);
    }

    private ToggleReactionResponseDto updateReactionPost(Reaction reaction, ToggleReactionDto dto) {
        reaction.setReactionType(dto.getReactionType());
        reaction.setTimeCreated(LocalDateTime.now());
        save(reaction);
        return new ToggleReactionResponseDto(dto.getReactionType().toString(), true);
    }

    private ToggleReactionResponseDto removeReactionPost(Reaction reaction, Post post, ToggleReactionDto dto) {
        post.getReactions().remove(reaction);
        postService.save(post);
        reactionRepository.delete(reaction);
        return new ToggleReactionResponseDto(dto.getReactionType().toString(), false);
    }
}
