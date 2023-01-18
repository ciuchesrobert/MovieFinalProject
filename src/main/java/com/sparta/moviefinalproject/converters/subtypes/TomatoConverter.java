package com.sparta.moviefinalproject.converters.subtypes;

import com.sparta.moviefinalproject.converters.Converter;
import com.sparta.moviefinalproject.dtos.subdtos.TomatoDTO;
import com.sparta.moviefinalproject.entities.subentities.Tomato;

public class TomatoConverter implements Converter<TomatoDTO, Tomato> {
    @Override
    public Tomato dtoToEntity(TomatoDTO tomatoDto) {
        Tomato tomato = new Tomato(tomatoDto.getConsensus(),
                null,
                tomatoDto.getDvd(),
                tomatoDto.getFresh(),
                tomatoDto.getLastUpdated(),
                tomatoDto.getProduction(),
                tomatoDto.getRotten(),
                null,
                tomatoDto.getWebsite());
        if (tomatoDto.getCritic() != null) {
            tomato.setCritic(new CriticConverter().dtoToEntity(tomatoDto.getCritic()));
        }
        if (tomatoDto.getViewer() != null) {
            tomato.setViewer(new ViewerConverter().dtoToEntity(tomatoDto.getViewer()));
        }
        return tomato;
    }

    @Override
    public TomatoDTO entityToDto(Tomato tomato) {

         TomatoDTO tomatoDTO = new TomatoDTO(tomato.getConsensus(),
                null,
                tomato.getDvd(),
                tomato.getFresh(),
                tomato.getLastUpdated(),
                tomato.getProduction(),
                tomato.getRotten(),
                null,
                tomato.getWebsite());

         if (tomato.getCritic() != null){
             tomatoDTO.setCritic(new CriticConverter().entityToDto(tomato.getCritic()));
         }
        if (tomato.getViewer() != null){
            tomatoDTO.setViewer(new ViewerConverter().entityToDto(tomato.getViewer()));
        }
        return tomatoDTO;
    }
}
