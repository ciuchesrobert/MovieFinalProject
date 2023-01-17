package com.sparta.moviefinalproject.converters.subTypeConverters;

import com.sparta.moviefinalproject.converters.Converter;
import com.sparta.moviefinalproject.dtos.subDtos.CriticDto;
import com.sparta.moviefinalproject.entities.subEntities.Critic;

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
