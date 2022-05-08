package com.instaclone.instaclone.repository;

import com.instaclone.instaclone.model.Categorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorizationRepository extends JpaRepository<Categorization, Long> {
}
