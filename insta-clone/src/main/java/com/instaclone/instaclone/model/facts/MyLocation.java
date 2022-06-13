package com.instaclone.instaclone.model.facts;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kie.api.definition.type.Position;

@Data
@NoArgsConstructor
@Builder
public class MyLocation {
    @Position(0)
    private String name;
    @Position(1)
    private String parent;


    public MyLocation(String parent, String name) {
        this.parent = parent;
        this.name = name;
    }
}

