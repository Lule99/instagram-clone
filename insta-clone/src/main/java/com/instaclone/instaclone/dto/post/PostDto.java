package com.instaclone.instaclone.dto.post;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    private Long id;
    private String username;
    private String userProfilePicture;
    private String text;
    private String picture;
    private LocalDateTime dateTime;
    private int numOfReactions;

}
