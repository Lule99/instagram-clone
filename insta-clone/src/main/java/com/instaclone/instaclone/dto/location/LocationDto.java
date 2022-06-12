package com.instaclone.instaclone.dto.location;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {

    private List<String> chain;
    @NotBlank
    private String locationName;
    @NonNull
    private Double longitude;
    @NonNull
    private Double latitude;
}
