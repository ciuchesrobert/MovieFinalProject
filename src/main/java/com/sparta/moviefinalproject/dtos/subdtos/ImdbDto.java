package com.sparta.moviefinalproject.dtos.subdtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImdbDto {
    private int id;
    private double rating;
    private int votes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImdbDto imdbDto = (ImdbDto) o;
        return id == imdbDto.id && Double.compare(imdbDto.rating, rating) == 0 && votes == imdbDto.votes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rating, votes);
    }
}
