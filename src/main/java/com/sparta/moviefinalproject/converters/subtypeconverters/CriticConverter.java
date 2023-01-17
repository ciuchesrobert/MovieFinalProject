package com.sparta.moviefinalproject.converters.subtypeconverters;

import com.sparta.moviefinalproject.converters.Converter;
import com.sparta.moviefinalproject.dtos.subdtos.CriticDto;
import com.sparta.moviefinalproject.entities.subentities.Critic;

public class CriticConverter implements Converter<CriticDto, Critic> {
    @Override
    public Critic dtoToEntity(CriticDto criticDto) {
        return new Critic(criticDto.getMeter(),
                criticDto.getNumReviews(),
                criticDto.getRating());
    }

    @Override
    public CriticDto entityToDto(Critic critic) {
        return new CriticDto(critic.getMeter(),
                critic.getNumReviews(),
                critic.getRating());
    }
}
