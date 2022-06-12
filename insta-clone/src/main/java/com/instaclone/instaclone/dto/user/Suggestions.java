package com.instaclone.instaclone.dto.user;

import com.instaclone.instaclone.model.facts.ForComplexRule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Suggestions {
    private List<ProfileInfoDto> profiles;
    private ForComplexRule forComplexRule;
}
