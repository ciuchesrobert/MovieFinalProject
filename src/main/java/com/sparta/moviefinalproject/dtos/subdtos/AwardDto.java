package com.sparta.moviefinalproject.dtos.subdtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AwardDto {
    private int wins;
    private int nominations;
    private String text;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AwardDto awardDto = (AwardDto) o;
        return wins == awardDto.wins && nominations == awardDto.nominations && Objects.equals(text, awardDto.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wins, nominations, text);
    }
}
