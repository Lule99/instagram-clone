package com.instaclone.instaclone.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchResultDto {
    private String username;
    private String profilePicture;
    private String name;
}
