package com.instaclone.instaclone.controller;

import com.instaclone.instaclone.dto.post.NewPostDto;
import com.instaclone.instaclone.dto.post.PostDto;
import com.instaclone.instaclone.dto.post.UpdatePostDto;
import com.instaclone.instaclone.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    PostService postService;

    @PreAuthorize("hasAnyAuthority('REGULAR_USER')")
    @GetMapping(value = "/feed", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<PostDto> getFeed(@RequestParam int page, @RequestParam int size, Authentication authentication) {
        return postService.getFeed(authentication.getName(), page, size);
    }

    @PreAuthorize("hasAnyAuthority('REGULAR_USER')")
    @GetMapping(value = "/explore", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<PostDto> getExplore(@RequestParam int page, @RequestParam int size, Authentication authentication) {
        return postService.getExplore(authentication.getName(), page, size);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PostDto getOnePost(@PathVariable Long id) {
        return postService.getOnePost(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyAuthority('REGULAR_USER')")
    @DeleteMapping(value = "/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deletePost(@PathVariable Long postId, Authentication authentication) {
        postService.deletePost(authentication.getName(), postId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('REGULAR_USER')")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PostDto publishPost(@RequestBody @Validated NewPostDto dto, Authentication authentication) {
        return postService.publishPost(dto, authentication.getName());
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyAuthority('REGULAR_USER')")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PostDto updatePost(@RequestBody @Validated UpdatePostDto updatePostDto, Authentication authentication) {
        return postService.updatePost(updatePostDto, authentication.getName());
    }

    @PreAuthorize("hasAnyAuthority('REGULAR_USER')")
    @GetMapping(value = "/user-posts", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<PostDto> getUserPosts(@RequestParam String username, @RequestParam int page, @RequestParam int size) {
        return postService.getUserPosts(username, page, size);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('REGULAR_USER')")
    @PostMapping(value = "/share/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean sharePost(@PathVariable long id, Authentication authentication) {
        return postService.sharePost(authentication.getName(), id);
    }


}
