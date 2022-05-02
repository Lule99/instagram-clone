package com.instaclone.instaclone.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {
    private String username;
    private String name;
    private String bio;
    private String profilePicture;
    private Boolean publicProfile;
}
