package com.instaclone.instaclone.service.impl;

import com.instaclone.instaclone.dto.reaction.ToggleReactionDto;
import com.instaclone.instaclone.dto.reaction.ToggleReactionResponseDto;
import com.instaclone.instaclone.model.Post;
import com.instaclone.instaclone.model.Reaction;
import com.instaclone.instaclone.model.User;
import com.instaclone.instaclone.model.enums.ReactionKind;
import com.instaclone.instaclone.repository.CategorizationRepository;
import com.instaclone.instaclone.repository.ReactionRepository;
import com.instaclone.instaclone.service.PostService;
import com.instaclone.instaclone.service.ReactionService;
import com.instaclone.instaclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ReactionServiceImpl extends JPAServiceImpl<Reaction> implements ReactionService {

    private final ReactionRepository reactionRepository;
    private final PostService postService;
    private final UserService userService;
    private final KieContainer kieContainer;
    private final CategorizationRepository categorizationRepository;

    @Override
    protected JpaRepository<Reaction, Long> getEntityRepository() {
        return reactionRepository;
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

    public int countReactionsByPost(Post post) {
        return reactionRepository.countReactionByPostId(post.getId());
    }

    private ToggleReactionResponseDto addReactionPost(Post post, ToggleReactionDto dto, User user) {
        Reaction newReaction = new Reaction(dto.getReactionType(), user.getProfile(), ReactionKind.POST_REACTION, post);
        newReaction.setTimeCreated(LocalDateTime.now());
        save(newReaction);
        post.getReactions().add(newReaction);

        KieSession kieSession = kieContainer.newKieSession("testSession");
        kieSession.getAgenda().getAgendaGroup( "add-reactions" ).setFocus();
        kieSession.insert(newReaction);
        kieSession.insert(user.getProfile());
        kieSession.insert(post);
        kieSession.fireAllRules();
        kieSession.dispose();

        categorizationRepository.save(user.getProfile().getFollowCategorization());
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
