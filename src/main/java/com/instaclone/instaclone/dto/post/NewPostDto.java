package com.instaclone.instaclone.dto.post;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewPostDto {
    @NotNull(message = "Post text must not be null!")
    private String text;
    @NotNull(message = "Post picture must not be null!")
    private String picture;
}
