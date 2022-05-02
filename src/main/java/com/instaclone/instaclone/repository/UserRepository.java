package com.instaclone.instaclone.repository;

import com.instaclone.instaclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findFirst10ByUsernameContainingIgnoringCase(String query);
}
