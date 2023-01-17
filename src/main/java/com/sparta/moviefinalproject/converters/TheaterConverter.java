package com.sparta.moviefinalproject.converters;

import com.sparta.moviefinalproject.dtos.TheaterDTO;
import com.sparta.moviefinalproject.entities.Theater;

public class TheaterConverter implements Converter<TheaterDTO, Theater> {
    @Override
    public Theater dtoToEntity(TheaterDTO theaterDto) {
        return new Theater(theaterDto.getId(),
                theaterDto.getLocation(),
                theaterDto.getTheaterId());
    }

    @Override
    public TheaterDTO entityToDto(Theater theater) {
        return new TheaterDTO(theater.getId(),
                theater.getLocation(),
                theater.getTheaterId());
    }
}
