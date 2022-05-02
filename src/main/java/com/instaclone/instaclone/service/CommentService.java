package com.instaclone.instaclone.service;

import com.instaclone.instaclone.dto.comment.AllCommentsDto;
import com.instaclone.instaclone.dto.comment.CommentDto;
import com.instaclone.instaclone.dto.comment.NewCommentDto;
import com.instaclone.instaclone.model.Comment;
import com.instaclone.instaclone.model.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService extends JPAService<Comment> {
    CommentDto getCommentById(long id);

    Page<CommentDto> getCommentsPageByIds(List<Long> ids, int page, int size);

    AllCommentsDto getCommentsByPost(Post post, int page, int size);

    CommentDto publishCommentOfPost(String username, NewCommentDto newCommentDto);

    CommentDto publishCommentOfComment(String username, NewCommentDto newCommentDto);
}
