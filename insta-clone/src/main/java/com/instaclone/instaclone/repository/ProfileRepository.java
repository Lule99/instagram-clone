package com.instaclone.instaclone.repository;

import com.instaclone.instaclone.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    List<Profile> findAllByViral(Boolean viral);

}
