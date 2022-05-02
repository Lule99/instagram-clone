package com.instaclone.instaclone.service.impl;

import com.instaclone.instaclone.converter.comment.CommentToCommentDtoConverter;
import com.instaclone.instaclone.dto.comment.AllCommentsDto;
import com.instaclone.instaclone.dto.comment.CommentDto;
import com.instaclone.instaclone.dto.comment.NewCommentDto;
import com.instaclone.instaclone.exception.EmptyContentException;
import com.instaclone.instaclone.exception.IdIsNullException;
import com.instaclone.instaclone.model.Comment;
import com.instaclone.instaclone.model.Post;
import com.instaclone.instaclone.model.User;
import com.instaclone.instaclone.repository.CommentRepository;
import com.instaclone.instaclone.service.CommentService;
import com.instaclone.instaclone.service.PostService;
import com.instaclone.instaclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl extends JPAServiceImpl<Comment> implements CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;
    private final CommentToCommentDtoConverter commentToCommentDtoConverter = new CommentToCommentDtoConverter();

    @Override
    protected JpaRepository<Comment, Long> getEntityRepository() {
        return commentRepository;
    }

    @Override
    public CommentDto getCommentById(long id) {
        return commentToCommentDtoConverter.convert(findOne(id));
    }

    @Override
    public Page<CommentDto> getCommentsPageByIds(List<Long> ids, int page, int size) {
        page = Math.max(page, 0);
        size = Math.max(size, 1);
        Pageable pageable = PageRequest.of(page, size);

        return commentRepository.getAllByContainingId(ids, pageable)
                .map(commentToCommentDtoConverter::convert);
    }

    @Override
    public AllCommentsDto getCommentsByPost(Post post, int page, int size) {
        page = Math.max(page, 0);
        size = Math.max(size, 1);
        Pageable pageable = PageRequest.of(page, size);

        return AllCommentsDto.builder()
                .comments(getCommentsPageByPost(post, pageable))
                .numOdComments(getNumOfCommentsForPost(post))
                .build();
    }

    @Override
    public CommentDto publishCommentOfPost(String username, NewCommentDto newCommentDto) {

        User user = userService.findByUsername(username);
        Post post = postService.findOne(newCommentDto.getCommentedEntityId());
        Comment newComment = Comment.builder()
                .post(post)
                .commentText(newCommentDto.getText())
                .profile(user.getProfile())
                .build();
        newComment.setTimeCreated(LocalDateTime.now());
        return commentToCommentDtoConverter.convert(save(newComment));
    }

    @Override
    public CommentDto publishCommentOfComment(String username, NewCommentDto newCommentDto) {

        User user = userService.findByUsername(username);
        Comment parentComment = findOne(newCommentDto.getCommentedEntityId());
        Comment newComment = Comment.builder()
                .commentText(newCommentDto.getText())
                .profile(user.getProfile())
                .build();
        if (parentComment.getComments() == null)
            parentComment.setComments(List.of(newComment));
        else
            parentComment.getComments().add(newComment);
        newComment.setTimeCreated(LocalDateTime.now());

        parentComment = save(parentComment);

        return commentToCommentDtoConverter.convert(parentComment.getComments().get(parentComment.getComments().size() - 1));
    }


    private int getNumOfCommentsForPost(Post post) {
        return commentRepository.countByPost(post);
    }

    private Page<CommentDto> getCommentsPageByPost(Post post, Pageable pageable) {
        return commentRepository.getAllByPost(post, pageable)
                .map(commentToCommentDtoConverter::convert);
    }


}
