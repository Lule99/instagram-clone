package com.instaclone.instaclone.repository;

import com.instaclone.instaclone.model.Comment;
import com.instaclone.instaclone.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("Select c from Comment c where c.id in ?1")
    Page<Comment> getAllByContainingId(List<Long> isd, Pageable pageable);

    Page<Comment> getAllByPost(Post post, Pageable pageable);

    int countByPost(Post post);
}
