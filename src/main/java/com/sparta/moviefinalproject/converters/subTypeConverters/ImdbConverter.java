package com.sparta.moviefinalproject.converters.subTypeConverters;

import com.sparta.moviefinalproject.converters.Converter;
import com.sparta.moviefinalproject.dtos.subDtos.ImdbDto;
import com.sparta.moviefinalproject.entities.subentities.Imdb;

public class ImdbConverter implements Converter<ImdbDto, Imdb> {

    @Override
    public Imdb dtoToEntity(ImdbDto imdbDto) {
        return new Imdb(imdbDto.getId(),
                imdbDto.getRating(),
                imdbDto.getVotes());
    }

    @Override
    public ImdbDto entityToDto(Imdb imdb) {
        return new ImdbDto(imdb.getId(),
                imdb.getRating(),
                imdb.getVotes());
    }
}
