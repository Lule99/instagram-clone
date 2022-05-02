package com.instaclone.instaclone.dto.reaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RemoveReactionDto {
    @NotNull
    private Long id;

    @NotNull
    private Long entityId;
}
