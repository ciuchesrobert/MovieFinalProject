package com.sparta.moviefinalproject.converters.subTypeConverters;

import com.sparta.moviefinalproject.converters.Converter;
import com.sparta.moviefinalproject.dtos.subDtos.AwardDto;
import com.sparta.moviefinalproject.entities.subEntities.Award;

public class AwardConverter implements Converter<AwardDto, Award> {
    @Override
    public Award dtoToEntity(AwardDto awardDto) {
        return new Award(awardDto.getNominations(),
                awardDto.getText(),
                awardDto.getWins());
    }

    @Override
    public AwardDto entityToDto(Award award) {
        return new AwardDto(award.getNominations(),
                award.getText(),
                award.getWins());
    }
}
