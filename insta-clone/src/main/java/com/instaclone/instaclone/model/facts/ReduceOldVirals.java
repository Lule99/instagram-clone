package com.instaclone.instaclone.model.facts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReduceOldVirals {
    List<ViralPost> viralPosts;
    Double avgShares;
    Double avgReactions;
    Double maxReactions;

    public boolean isEmpty(){
        return viralPosts == null || avgShares == null || avgReactions == null || maxReactions == null
                || viralPosts.isEmpty() || avgShares == 0.0 || avgReactions == 0.0 || maxReactions == 0.0;
    }
}
