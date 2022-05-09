package com.instaclone.instaclone.dto.post;

import com.instaclone.instaclone.model.enums.Category;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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
    private String location;
    private List<Category> categories;
    private LocalDateTime time;
    private int numOfShares;
}
