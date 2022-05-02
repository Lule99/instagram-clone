package com.instaclone.instaclone.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ModelMapperUtils extends ModelMapper {

    public <EntityClass, DTOClass> List<DTOClass> mapList(List<EntityClass> entityClassList, Class<DTOClass> dtoClass) {
        return entityClassList
                .stream()
                .map(element -> this.map(element, dtoClass))
                .collect(Collectors.toList());
    }

    public <EntityClass, DTOClass> Set<DTOClass> mapSet(Set<EntityClass> entityClassSet, Class<DTOClass> dtoClass) {
        return entityClassSet
                .stream()
                .map(element -> this.map(element, dtoClass))
                .collect(Collectors.toSet());
    }
}

