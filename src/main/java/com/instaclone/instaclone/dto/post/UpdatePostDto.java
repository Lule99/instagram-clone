package com.instaclone.instaclone.dto.post;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePostDto {
    @NotNull(message = "PostToUpdateId cannot be null!")
    @Positive
    private Long postToUpdateId;
    @NotNull(message = "Text cannot be null!")
    private String text;
    @NotNull(message = "Picture cannot be null!")
    private String picture;
}
