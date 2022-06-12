package com.instaclone.instaclone.model.facts;

import com.instaclone.instaclone.model.Location;
import com.instaclone.instaclone.model.Post;
import com.instaclone.instaclone.model.Reaction;
import com.instaclone.instaclone.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostFact {

    Long id ;

    ProfileFact publisher;

    LocalDateTime timeCreated;

    private String text;

    private Location location;

    private String picture;

    private Boolean viral;

    private List<Long> reactionsIds;

    private Integer numOfShares;

    private List<Category> categories;

    public PostFact(Post p) {

        this.id = p.getId();
        this.publisher = new ProfileFact(p.getPublisher());
        this.timeCreated = p.getTimeCreated();
        this.text = p.getText();
        this.location = p.getLocation();
        this.picture = p.getPicture();
        this.viral = p.getViral();
        this.reactionsIds = p.getReactions().stream().map(Reaction::getId).collect(Collectors.toList());
        this.numOfShares = p.getNumOfShares();
        this.categories = p.getCategories();
    }
}
