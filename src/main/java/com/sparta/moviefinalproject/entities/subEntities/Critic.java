package com.sparta.moviefinalproject.entities.subEntities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Critic {
    private int meter;
    private int numReviews;
    private double rating;
}