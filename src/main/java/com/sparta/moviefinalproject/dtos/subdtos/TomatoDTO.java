package com.sparta.moviefinalproject.dtos.subdtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Objects;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TomatoDTO {
    private String consensus;
    private CriticDTO critic;
    private LocalDateTime dvd;
    private int fresh;
    private LocalDateTime lastUpdated;
    private String production;
    private int rotten;
    private ViewerDTO viewer;
    private String website;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TomatoDTO tomatoDto = (TomatoDTO) o;
        return fresh == tomatoDto.fresh && rotten == tomatoDto.rotten && Objects.equals(consensus, tomatoDto.consensus) && Objects.equals(critic, tomatoDto.critic) && Objects.equals(dvd, tomatoDto.dvd) && Objects.equals(lastUpdated, tomatoDto.lastUpdated) && Objects.equals(production, tomatoDto.production) && Objects.equals(viewer, tomatoDto.viewer) && Objects.equals(website, tomatoDto.website);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consensus, critic, dvd, fresh, lastUpdated, production, rotten, viewer, website);
    }
}
