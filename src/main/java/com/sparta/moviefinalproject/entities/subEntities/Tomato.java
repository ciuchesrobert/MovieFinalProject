package com.sparta.moviefinalproject.entities.subEntities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tomato {
    private String consensus;
    private Critic critic;
    private LocalDateTime dvd;
    private int fresh;
    private LocalDateTime lastUpdated;
    private String production;
    private int rotten;
    private Viewer viewer;
    private String website;
}
