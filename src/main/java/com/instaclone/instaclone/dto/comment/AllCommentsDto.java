package com.instaclone.instaclone.dto.comment;

import lombok.*;
import org.springframework.data.domain.Page;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllCommentsDto {
    private int numOdComments;
    private Page<CommentDto> comments;
}
