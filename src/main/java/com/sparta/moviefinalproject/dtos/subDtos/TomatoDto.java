package com.sparta.moviefinalproject.dtos.subDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
}
