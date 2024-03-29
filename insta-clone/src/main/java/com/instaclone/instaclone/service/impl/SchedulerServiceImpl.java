package com.instaclone.instaclone.service.impl;

import com.instaclone.instaclone.model.Post;
import com.instaclone.instaclone.model.Profile;
import com.instaclone.instaclone.service.PostService;
import com.instaclone.instaclone.service.SchedulerService;
import com.instaclone.instaclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulerServiceImpl implements SchedulerService {

    private final PostService postService;
    private final UserService userService;
    private final KieContainer kieContainer;


    @Override
    @Scheduled(cron = "0 0 0 * * *")        //svaki dan u ponoc
    public void calculateViralPosts() {
        removeOldViralPosts();
        addNewViralPosts();
    }

    private void removeOldViralPosts() {
        LocalDateTime weekBefore = LocalDateTime.now().minus(7, ChronoUnit.DAYS);
        List<Post> viralsToDelete = postService.getViralPostsBefore(weekBefore);
        viralsToDelete.forEach(viral -> viral.setViral(false));
        postService.saveAll(viralsToDelete);
    }


    private void addNewViralPosts() {
        postService.calculateViralPosts();
    }



}
