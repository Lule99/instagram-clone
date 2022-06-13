package com.instaclone.instaclone.repository;

import com.instaclone.instaclone.model.Post;
import com.instaclone.instaclone.model.Profile;
import com.instaclone.instaclone.model.Reaction;
import com.instaclone.instaclone.model.enums.ReactionKind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {

    @Query("select r from Reaction r where r.reactionKind = ?1 and r.profile = ?2 and r.post = ?3 and r.active = ?4")
    Reaction reactionForPost(ReactionKind reactionKind, Profile profile, Post post, Boolean active);
    
    @Query("select count(r) from Reaction r where r.post.id = ?1")
    int countReactionByPostId(Long id);}
