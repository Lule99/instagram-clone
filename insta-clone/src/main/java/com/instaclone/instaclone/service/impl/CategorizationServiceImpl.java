package com.instaclone.instaclone.service.impl;

import com.instaclone.instaclone.model.Categorization;
import com.instaclone.instaclone.model.enums.Category;
import com.instaclone.instaclone.repository.CategorizationRepository;
import com.instaclone.instaclone.service.CategorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Override
    @Transactional
    public void updateParameters(Categorization categorization) {
        double nature = categorization.getCategories().get(0);
        double sport = categorization.getCategories().get(1);
        double travel = categorization.getCategories().get(2);
        double tech = categorization.getCategories().get(3);
        double fashion = categorization.getCategories().get(4);
        double food_drink = categorization.getCategories().get(5);
        double animals = categorization.getCategories().get(6);
        double clubbing = categorization.getCategories().get(7);
        double urbanLife = categorization.getCategories().get(8);
        double art = categorization.getCategories().get(9);
        double selfie = categorization.getCategories().get(10);
        double history_religion = categorization.getCategories().get(11);
        double education = categorization.getCategories().get(12);
        double health = categorization.getCategories().get(13);
        double meme = categorization.getCategories().get(14);
        double other = categorization.getCategories().get(15);

        categorization.setCategories(List.of(animals, health, urbanLife,
                education, art, clubbing, nature, food_drink, travel,
                fashion, selfie, history_religion, tech, sport, meme, other));


        categorizationRepository.save(categorization);

    }

    @Override
    @Transactional
    public void changeCompleteParameters(Categorization categorization) {
        List<Double> cats = new ArrayList<>(categorization.getCategories());
        Collections.reverse(cats);
        categorization.setCategories(cats);
    }
}
