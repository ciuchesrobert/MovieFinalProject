package com.sparta.moviefinalproject.converters.subTypeConverters;

import com.sparta.moviefinalproject.converters.Converter;
import com.sparta.moviefinalproject.dtos.subDtos.ViewerDto;
import com.sparta.moviefinalproject.entities.subentities.Viewer;

public class ViewerConverter implements Converter<ViewerDto, Viewer> {

    @Override
    public Viewer dtoToEntity(ViewerDto viewerDto) {
        return new Viewer(viewerDto.getMeter(),
                viewerDto.getNumReviews(),
                viewerDto.getRating());
    }

    @Override
    public ViewerDto entityToDto(Viewer viewer) {
        return new ViewerDto(viewer.getMeter(),
                viewer.getNumReviews(),
                viewer.getRating());
    }
}
