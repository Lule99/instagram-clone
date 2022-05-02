package com.instaclone.instaclone.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeFollowingStatusDto {
    private String myUsername;
    private String otherUsername;
}
