package com.sparta.moviefinalproject.entities.subentities;

import com.mongodb.lang.NonNull;
import com.mongodb.lang.Nullable;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Critic {
    private Integer meter;
    private Integer numReviews;
    private Double rating;
}
