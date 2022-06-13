package com.instaclone.instaclone.repository;

import com.instaclone.instaclone.model.Post;
import com.instaclone.instaclone.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p where p.publisher in ?1")
    Page<Post> getFeed(Set<Profile> publishers, Pageable pageable);

    Page<Post> getPostByPublisher(Profile profile, Pageable pageable);

    @Query(value = "select count(p.reactions) from Post p where p.id = ?1")
    int countReactionsByPost(long postId);

    List<Post> getTop100ByPublisherInAndTimeCreatedAfter(List<Profile> publishers, LocalDateTime createdAfter);

    List<Post> getAllByTimeCreatedAfter(LocalDateTime after);

    List<Post> getAllByViralAndTimeCreatedBefore(boolean viral, LocalDateTime after);

    List<Post> getTop50ByPublisherInAndTimeCreatedAfterOrderByTimeCreatedDesc(List<Profile> publishers, LocalDateTime createdAfter);
}
