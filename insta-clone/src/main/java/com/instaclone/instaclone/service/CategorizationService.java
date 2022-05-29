package com.instaclone.instaclone.service;

import com.instaclone.instaclone.model.Categorization;
import com.instaclone.instaclone.model.enums.Category;

import java.util.List;

public interface CategorizationService extends JPAService<Categorization>{
    List<Category> getFavoritesCategories(Categorization categorization);
}
