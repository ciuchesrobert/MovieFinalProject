package com.sparta.moviefinalproject.dtos;

import com.sparta.moviefinalproject.dtos.subdtos.AwardDTO;
import com.sparta.moviefinalproject.dtos.subdtos.ImdbDTO;
import com.sparta.moviefinalproject.dtos.subdtos.TomatoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    private ObjectId id;
    private AwardDTO awards;
    private String[] directors;
    private String fullPlot;
    private String[] languages;
    private int numMflixComments;
    private String plot;
    private String title;
    private String[] writers;
    private String[] genres;
    private LocalDateTime lastUpdated;
    private String poster;
    private TomatoDTO tomatoes;
    private String year;
    private ImdbDTO imdb;
    private String rated;
    private LocalDateTime released;
    private String[] cast;
    private int runtime;
    private String[] countries;
    private String type;


}