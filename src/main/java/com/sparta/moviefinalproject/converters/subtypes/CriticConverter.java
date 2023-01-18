package com.sparta.moviefinalproject.converters.subtypes;

import com.sparta.moviefinalproject.converters.Converter;
import com.sparta.moviefinalproject.dtos.subdtos.CriticDTO;
import com.sparta.moviefinalproject.entities.subentities.Critic;

public class CriticConverter implements Converter<CriticDTO, Critic> {
    @Override
    public Critic dtoToEntity(CriticDTO criticDto) {
        return new Critic(criticDto.getMeter(),
                criticDto.getNumReviews(),
                criticDto.getRating());
    }

    @Override
    public CriticDTO entityToDto(Critic critic) {
        return new CriticDTO(critic.getMeter(),
                critic.getNumReviews(),
                critic.getRating());
    }
}
