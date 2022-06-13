package com.instaclone.instaclone.model.facts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForComplexRule {
    private List<FinalSuggestion> finalSuggestionList;
    private double min;
    private double max;
    private double sum;
    private double count;
    private double average;
}
