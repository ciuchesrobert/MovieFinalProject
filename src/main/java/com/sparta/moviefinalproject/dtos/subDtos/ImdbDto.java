package com.sparta.moviefinalproject.dtos.subDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImdbDto {
    private int id;
    private double rating;
    private int votes;
}
