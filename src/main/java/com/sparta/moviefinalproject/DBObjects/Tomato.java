package com.sparta.moviefinalproject.DBObjects;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Tomato {
    private String consensus;
    private Critic critic;
    private LocalDate dvd;
    private Integer fresh;
    private LocalDate lastUpdated;
    private String production;
    private Integer rotten;
    private Critic viewer;
    private String website;

}
