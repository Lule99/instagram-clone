package com.instaclone.instaclone.dto.user;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileInfoDto {
    String username;
    int postsNumber;
    int followersNumber;
    int followingNumber;
    String name;
    String profilePicture;
    String bio;
}
