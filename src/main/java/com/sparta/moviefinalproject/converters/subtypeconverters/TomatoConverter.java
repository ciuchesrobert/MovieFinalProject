package com.sparta.moviefinalproject.converters.subtypeconverters;

import com.sparta.moviefinalproject.converters.Converter;
import com.sparta.moviefinalproject.dtos.subdtos.TomatoDto;
import com.sparta.moviefinalproject.entities.subentities.Tomato;

public class TomatoConverter implements Converter<TomatoDto, Tomato> {
    @Override
    public Tomato dtoToEntity(TomatoDto tomatoDto) {
        return new Tomato(tomatoDto.getConsensus(),
                new CriticConverter().dtoToEntity(tomatoDto.getCritic()),
                tomatoDto.getDvd(),
                tomatoDto.getFresh(),
                tomatoDto.getLastUpdated(),
                tomatoDto.getProduction(),
                tomatoDto.getRotten(),
                new ViewerConverter().dtoToEntity(tomatoDto.getViewer()),
                tomatoDto.getWebsite());
    }

    @Override
    public TomatoDto entityToDto(Tomato tomato) {
        return new TomatoDto(tomato.getConsensus(),
                new CriticConverter().entityToDto(tomato.getCritic()),
                tomato.getDvd(),
                tomato.getFresh(),
                tomato.getLastUpdated(),
                tomato.getProduction(),
                tomato.getRotten(),
                new ViewerConverter().entityToDto(tomato.getViewer()),
                tomato.getWebsite());
    }
}
