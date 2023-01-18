package com.sparta.moviefinalproject.dtos.subdtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Objects;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImdbDTO {
    private Integer id;
    private Double rating;
    private Integer votes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImdbDTO imdbDto = (ImdbDTO) o;
        return id == imdbDto.id && Double.compare(imdbDto.rating, rating) == 0 && votes == imdbDto.votes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rating, votes);
    }

}
