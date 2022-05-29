package com.instaclone.instaclone.model.facts;

import com.instaclone.instaclone.model.Profile;
import com.instaclone.instaclone.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostPublished {
    private Profile myProfile;
    private List<Category> categories;
}
