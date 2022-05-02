package com.instaclone.instaclone.repository;

import com.instaclone.instaclone.model.PasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordTokenRepository extends JpaRepository<PasswordToken, Long> {
    PasswordToken findByToken(String token);
}