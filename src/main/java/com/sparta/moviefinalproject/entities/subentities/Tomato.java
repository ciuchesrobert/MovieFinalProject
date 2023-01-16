package com.sparta.moviefinalproject.entities.subentities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tomato {
    private String consensus;
    private Critic critic;
    private String dvd;
    private int fresh;
    private String lastUpdated;
    private String production;
    private int rotten;
    private Viewer viewer;
    private String website;
}
