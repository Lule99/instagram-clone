package com.instaclone.instaclone.converter.comment;

import com.instaclone.instaclone.converter.Converter;
import com.instaclone.instaclone.dto.comment.CommentDto;
import com.instaclone.instaclone.model.Comment;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CommentToCommentDtoConverter extends Converter<Comment, CommentDto> {
    @Override
    public CommentDto convert(Comment source) {

        String username = source.getProfile().getUser().getUsername();
        String profilePic = source.getProfile().getProfilePicture();
        CommentDto c = CommentDto.builder()
                .id(source.getId())
                .commentText(source.getCommentText())
                .userProfilePicture(profilePic)
                .username(username)
                .build();
        if(source.getComments() == null || source.getComments().isEmpty())
            c.setChildCommentIds(new ArrayList<>());
        else
            c.setChildCommentIds(source.getComments().stream()
                    .map(Comment::getId).collect(Collectors.toList()));

        return c;
    }
}
