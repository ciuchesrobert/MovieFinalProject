package com.sparta.moviefinalproject.converters.subtypes;

import com.sparta.moviefinalproject.converters.Converter;
import com.sparta.moviefinalproject.dtos.subdtos.ImdbDTO;
import com.sparta.moviefinalproject.entities.subentities.Imdb;

public class ImdbConverter implements Converter<ImdbDTO, Imdb> {

    @Override
    public Imdb dtoToEntity(ImdbDTO imdbDto) {
        return new Imdb(imdbDto.getId(),
                imdbDto.getRating(),
                imdbDto.getVotes());
    }

    @Override
    public ImdbDTO entityToDto(Imdb imdb) {
        return new ImdbDTO(imdb.getId(),
                imdb.getRating(),
                imdb.getVotes());
    }
}
