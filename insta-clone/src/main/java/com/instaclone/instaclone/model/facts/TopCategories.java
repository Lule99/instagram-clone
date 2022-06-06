package com.instaclone.instaclone.model.facts;

import com.instaclone.instaclone.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopCategories {
    //indexi top kategorija
    List<Integer> topCategories;
    List<Category> categoryNames;
    int numOfCats;

    public TopCategories(List<Integer> tops) {
        this.topCategories = tops;
        this.categoryNames = new ArrayList<>();
        for (Integer ordinal : topCategories) {
            categoryNames.add(Category.values()[ordinal]);
        }
        this.numOfCats = topCategories.size();

    }

}
