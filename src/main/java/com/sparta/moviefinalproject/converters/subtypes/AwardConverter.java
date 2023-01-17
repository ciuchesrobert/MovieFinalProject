package com.sparta.moviefinalproject.converters.subtypes;

import com.sparta.moviefinalproject.converters.Converter;
import com.sparta.moviefinalproject.dtos.subdtos.AwardDto;
import com.sparta.moviefinalproject.entities.subentities.Award;

public class AwardConverter implements Converter<AwardDto, Award> {
    @Override
    public Award dtoToEntity(AwardDto awardDto) {
        return new Award(awardDto.getWins(),
                awardDto.getNominations(),
                awardDto.getText());
    }

    @Override
    public AwardDto entityToDto(Award award) {
        return new AwardDto(award.getWins(),
                award.getNominations(),
                award.getText());
    }
}
