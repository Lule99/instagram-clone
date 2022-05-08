package com.instaclone.instaclone.dto.auth;

import com.instaclone.instaclone.dto.location.LocationDto;
import com.instaclone.instaclone.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {
    private String username;
    private String password;
    private String repeatedPassword;
    private LocalDate birthday;
    private Gender gender;
    private LocationDto location;
    private String email;
    private String name;
    private String bio;
    private String profilePicture;
}
