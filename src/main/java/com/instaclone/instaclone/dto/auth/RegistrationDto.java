package com.instaclone.instaclone.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {
    private String username;
    private String password;
    private String repeatedPassword;
    private String email;
    private String name;
    private String bio;
    private String profilePicture;
    private Boolean publicProfile;
}
