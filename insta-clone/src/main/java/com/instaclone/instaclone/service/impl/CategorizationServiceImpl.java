package com.instaclone.instaclone.service.impl;

import com.instaclone.instaclone.model.Categorization;
import com.instaclone.instaclone.repository.CategorizationRepository;
import com.instaclone.instaclone.service.CategorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategorizationServiceImpl extends JPAServiceImpl<Categorization> implements CategorizationService {

    private final CategorizationRepository categorizationRepository;

    @Override
    protected JpaRepository<Categorization, Long> getEntityRepository() {
        return categorizationRepository;
    }
}
