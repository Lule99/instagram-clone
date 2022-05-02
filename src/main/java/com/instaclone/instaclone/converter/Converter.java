package com.instaclone.instaclone.converter;

import com.instaclone.instaclone.utils.ModelMapperUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class Converter<TFrom, TTo> {

    private ModelMapperUtils modelMapper;

    public abstract TTo convert(TFrom source);

    public ModelMapperUtils getModelMapper() {
        return modelMapper;
    }

    @Autowired
    public final void setModelMapper(ModelMapperUtils modelMapper) {
        this.modelMapper = modelMapper;
    }

}


