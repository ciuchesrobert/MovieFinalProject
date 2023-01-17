package com.sparta.moviefinalproject.converters.subtypes;

import com.sparta.moviefinalproject.converters.Converter;
import com.sparta.moviefinalproject.dtos.subdtos.TomatoDTO;
import com.sparta.moviefinalproject.entities.subentities.Tomato;

public class TomatoConverter implements Converter<TomatoDTO, Tomato> {
    @Override
    public Tomato dtoToEntity(TomatoDTO tomatoDto) {
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
    public TomatoDTO entityToDto(Tomato tomato) {
        return new TomatoDTO(tomato.getConsensus(),
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
