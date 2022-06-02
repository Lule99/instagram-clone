package com.instaclone.instaclone.service;

import com.instaclone.instaclone.dto.post.NewPostDto;
import com.instaclone.instaclone.dto.post.PostDto;
import com.instaclone.instaclone.dto.post.UpdatePostDto;
import com.instaclone.instaclone.model.Post;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface PostService extends JPAService<Post> {

    PostDto publishPost(NewPostDto newPost, String username);

    void deletePost(String username, Long postId);

    PostDto updatePost(UpdatePostDto updatePostDto, String username);

    PostDto getOnePost(Long id);

    Page<PostDto> getFeed(String username, int page, int size);

    Page<PostDto> getUserPosts(String username, int page, int size);

    int getNumOfReactions(Post post);

    Page<PostDto> getExplore(String username, int page, int size);

    Boolean sharePost(String username, long id);

    List<Post> getViralPostsBefore(LocalDateTime beforeDate);

    List<Post> getPostsAfter(LocalDateTime afterDate);

    Boolean reload(String name);
}
