package com.sparta.moviefinalproject.dtos.subdtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriticDTO {
    private int meter;
    private int numReviews;
    private double rating;
}
