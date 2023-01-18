package com.sparta.moviefinalproject.converters.subtypes;

import com.sparta.moviefinalproject.converters.Converter;
import com.sparta.moviefinalproject.dtos.subdtos.AwardDTO;
import com.sparta.moviefinalproject.entities.subentities.Award;

public class AwardConverter implements Converter<AwardDTO, Award> {
    @Override
    public Award dtoToEntity(AwardDTO awardDto) {
        return new Award(awardDto.getWins(),
                awardDto.getNominations(),
                awardDto.getText());
    }

    @Override
    public AwardDTO entityToDto(Award award) {
        return new AwardDTO(award.getWins(),
                award.getNominations(),
                award.getText());
    }
}
