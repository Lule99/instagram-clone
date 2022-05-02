package com.instaclone.instaclone.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;
    private String email;
    private String name;
    private String bio;
    private String profilePicture;
}
