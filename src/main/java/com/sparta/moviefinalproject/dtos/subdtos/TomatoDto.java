package com.sparta.moviefinalproject.dtos.subdtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TomatoDto {
    private String consensus;
    private CriticDto critic;
    private LocalDateTime dvd;
    private int fresh;
    private LocalDateTime lastUpdated;
    private String production;
    private int rotten;
    private ViewerDto viewer;
    private String website;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TomatoDto tomatoDto = (TomatoDto) o;
        return fresh == tomatoDto.fresh && rotten == tomatoDto.rotten && Objects.equals(consensus, tomatoDto.consensus) && Objects.equals(critic, tomatoDto.critic) && Objects.equals(dvd, tomatoDto.dvd) && Objects.equals(lastUpdated, tomatoDto.lastUpdated) && Objects.equals(production, tomatoDto.production) && Objects.equals(viewer, tomatoDto.viewer) && Objects.equals(website, tomatoDto.website);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consensus, critic, dvd, fresh, lastUpdated, production, rotten, viewer, website);
    }
}
