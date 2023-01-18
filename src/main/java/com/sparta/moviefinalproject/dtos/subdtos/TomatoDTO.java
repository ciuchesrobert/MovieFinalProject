package com.sparta.moviefinalproject.dtos.subdtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TomatoDTO {
    private String consensus;
    private CriticDTO critic;
    private LocalDateTime dvd;
    private int fresh;
    private LocalDateTime lastUpdated;
    private String production;
    private int rotten;
    private ViewerDTO viewer;
    private String website;
}
