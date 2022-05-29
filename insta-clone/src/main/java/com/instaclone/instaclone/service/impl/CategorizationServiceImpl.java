package com.instaclone.instaclone.service.impl;

import com.instaclone.instaclone.model.Categorization;
import com.instaclone.instaclone.model.enums.Category;
import com.instaclone.instaclone.repository.CategorizationRepository;
import com.instaclone.instaclone.service.CategorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategorizationServiceImpl extends JPAServiceImpl<Categorization> implements CategorizationService {

    private final CategorizationRepository categorizationRepository;

    @Override
    protected JpaRepository<Categorization, Long> getEntityRepository() {
        return categorizationRepository;
    }

    @Override
    public List<Category> getFavoritesCategories(Categorization categorization) {
        List<Double> tmpList = new ArrayList<>(categorization.getCategories());
        Collections.sort(tmpList, Collections.reverseOrder());
        List<Category> finalList = new ArrayList();

        for(int i = 0; i < tmpList.size(); i++) {
            Category cat = Category.values()[categorization
                    .getCategories().indexOf(tmpList.get(i))];

            finalList.add(cat);
        }

        return finalList.subList(0, 3);
    }
}
