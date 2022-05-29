package com.instaclone.instaclone.model.facts;

import com.instaclone.instaclone.model.Post;
import com.instaclone.instaclone.model.Reaction;
import com.instaclone.instaclone.model.enums.Category;
import com.instaclone.instaclone.model.enums.ReactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViralPost {
    private Long postId;
    private LocalDateTime datePublished;
    private int daysFromPublishing;
    private double freshnessCoef;
    private int numOfShares;
    private int numOfReactions;
    private int numOfInteractions;
    private double positivityCoef;
    private double negativityCoef;
    private boolean viral;
    private double numReactionsNumFollowersRatio;
    private List<Double> reactionsByType;
    private List<Category> categories;
    private boolean theChosenOne;

    public ViralPost(Post post) {
        this.postId=post.getId();
        this.datePublished = post.getTimeCreated();
        this.daysFromPublishing = (int) DAYS.between(datePublished, LocalDateTime.now());
        this.numOfShares = post.getNumOfShares();
        this.numOfReactions = post.getReactions().size();
        this.numOfInteractions = numOfShares+numOfReactions;
        this.freshnessCoef = (double) numOfInteractions/ (double) daysFromPublishing;
        this.numReactionsNumFollowersRatio = (double) numOfReactions / (double) post.getPublisher().getFollowers().size();
        this.viral = post.getViral();
        this.reactionsByType = calculateReactionsByType(post.getReactions());
        this.categories = post.getCategories();
        this.positivityCoef = calculatePositivityCoef(reactionsByType);
        this.negativityCoef = calculateNegativityCoef(reactionsByType);
        this.theChosenOne=false;


    }

    private double calculateNegativityCoef(List<Double> r) {
        return (2*r.get(0)+r.get(2))/(r.get(1)+ 2 * r.get(3) + r.get(4));
    }

    private double calculatePositivityCoef(List<Double> r) {
        return (r.get(1)+ 2 * r.get(3) + r.get(4))/(2*r.get(0)+r.get(2));
    }


    private List<Double> calculateReactionsByType(List<Reaction> reactions) {
        Map<ReactionType, Long> map = reactions.stream()
                .collect(Collectors.groupingBy(Reaction::getReactionType, Collectors.counting()));

        List<Double> res = new ArrayList<>(5);
        for (int i = 0; i <5 ; i++) {
            res.set(i, map.get(ReactionType.values()[i]).doubleValue());
        }
        return res;
    }


}
