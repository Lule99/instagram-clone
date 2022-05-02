package com.instaclone.instaclone.dto.comment;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewCommentDto {
    @NotNull(message = "commentedEntityId cannot be null!")
    @Positive
    private Long commentedEntityId;
    @NotNull(message = "text cannot be null!")
    @NotBlank
    private String text;
}
