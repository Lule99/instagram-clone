package com.instaclone.instaclone.dto.post;

import com.instaclone.instaclone.dto.location.LocationDto;
import com.instaclone.instaclone.model.enums.Category;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewPostDto {
    @NotNull(message = "Post text must not be null!")
    private String text;
    @NotNull(message = "Post picture must not be null!")
    private String picture;
    private LocationDto location;
    private List<Category> categories;
}
