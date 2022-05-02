package com.instaclone.instaclone.dto.comment;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private Long id;
    private String userProfilePicture;
    private String username;
    private String commentText;
    private List<Long> childCommentIds;
}
