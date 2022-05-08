package com.instaclone.instaclone.dto.location;

import lombok.*;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {
    private String state;
    private String region;
    @NotBlank
    private String locationName;
    @NonNull
    private Long longitude;
    @NonNull
    private Long latitude;
}
