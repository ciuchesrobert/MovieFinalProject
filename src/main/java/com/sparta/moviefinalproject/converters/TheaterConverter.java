package com.sparta.moviefinalproject.converters;

import com.sparta.moviefinalproject.dtos.TheaterDto;
import com.sparta.moviefinalproject.entities.Theater;

public class TheaterConverter implements Converter<TheaterDto, Theater> {
    @Override
    public Theater dtoToEntity(TheaterDto theaterDto) {
        return new Theater(theaterDto.getId(),
                theaterDto.getLocation(),
                theaterDto.getTheaterId());
    }

    @Override
    public TheaterDto entityToDto(Theater theater) {
        return new TheaterDto(theater.getId(),
                theater.getLocation(),
                theater.getTheaterId());
    }
}
