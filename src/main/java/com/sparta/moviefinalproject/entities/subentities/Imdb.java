package com.sparta.moviefinalproject.entities.subentities;

import com.mongodb.lang.Nullable;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Objects;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Imdb {
    private Integer id;
    private Double rating;
    private Integer votes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Imdb imdb = (Imdb) o;
        return Objects.equals(id, imdb.id) && Objects.equals(rating, imdb.rating) && Objects.equals(votes, imdb.votes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rating, votes);
    }
}
