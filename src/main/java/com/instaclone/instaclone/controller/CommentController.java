package com.instaclone.instaclone.controller;

import com.instaclone.instaclone.dto.comment.AllCommentsDto;
import com.instaclone.instaclone.dto.comment.CommentDto;
import com.instaclone.instaclone.dto.comment.NewCommentDto;
import com.instaclone.instaclone.model.Comment;
import com.instaclone.instaclone.model.Post;
import com.instaclone.instaclone.service.CommentService;
import com.instaclone.instaclone.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;
    @Autowired
    PostService postService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public AllCommentsDto getCommentsForPost(@RequestParam Long postId,
                                             @RequestParam int page,
                                             @RequestParam int size) {

        Post post = postService.findOne(postId);
        return commentService.getCommentsByPost(post, page, size);
    }

    @GetMapping(value = "/comment", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<CommentDto> getCommentsForComment(@RequestParam Long commentId,
                                                  @RequestParam int page,
                                                  @RequestParam int size) {

        Comment comment = commentService.findOne(commentId);
        List<Long> ids = comment.getComments().stream().map(Comment::getId).collect(Collectors.toList());
        return commentService.getCommentsPageByIds(ids, page, size);
    }

    @GetMapping("/{id}")
    public CommentDto getOneComment(@PathVariable Long id) {
        return commentService.getCommentById(id);
    }

    @PreAuthorize("hasAnyAuthority('REGULAR_USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommentDto publishCommentOnPost(@RequestBody @Validated NewCommentDto dto,
                                           Authentication authentication) {
        return commentService.publishCommentOfPost(authentication.getName(), dto);
    }

    @PreAuthorize("hasAnyAuthority('REGULAR_USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/comment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommentDto publishCommentOnComment(@RequestBody @Validated NewCommentDto dto,
                                              Authentication authentication) {
        return commentService.publishCommentOfComment(authentication.getName(), dto);
    }

}
