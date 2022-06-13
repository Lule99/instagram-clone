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
public class FinalCategorization {
    private Boolean initilized;
    private List<Double> categorization;

}
