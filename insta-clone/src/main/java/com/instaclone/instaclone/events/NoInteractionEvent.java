package com.instaclone.instaclone.events;

import com.instaclone.instaclone.model.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("20m")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoInteractionEvent {
    private Profile profile;
    private boolean processed;
}
