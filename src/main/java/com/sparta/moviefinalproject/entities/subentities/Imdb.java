package com.sparta.moviefinalproject.entities.subentities;

import com.mongodb.lang.Nullable;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Imdb {
    @Nullable
    private Integer id;
    @Nullable
    private Double rating;
    @Nullable
    private Integer votes;
}
