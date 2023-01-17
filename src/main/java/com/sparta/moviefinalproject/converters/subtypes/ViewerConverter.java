package com.sparta.moviefinalproject.converters.subtypes;

import com.sparta.moviefinalproject.converters.Converter;
import com.sparta.moviefinalproject.dtos.subdtos.ViewerDTO;
import com.sparta.moviefinalproject.entities.subentities.Viewer;

public class ViewerConverter implements Converter<ViewerDTO, Viewer> {

    @Override
    public Viewer dtoToEntity(ViewerDTO viewerDto) {
        return new Viewer(viewerDto.getMeter(),
                viewerDto.getNumReviews(),
                viewerDto.getRating());
    }

    @Override
    public ViewerDTO entityToDto(Viewer viewer) {
        return new ViewerDTO(viewer.getMeter(),
                viewer.getNumReviews(),
                viewer.getRating());
    }
}
