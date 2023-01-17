package com.sparta.moviefinalproject.dtos;

import com.sparta.moviefinalproject.dtos.subDtos.AwardDto;
import com.sparta.moviefinalproject.dtos.subDtos.ImdbDto;
import com.sparta.moviefinalproject.dtos.subDtos.TomatoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
    private ObjectId id;
    private AwardDto awards;
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
    private TomatoDto tomatoes;
    private int year;
    private ImdbDto imdb;
    private String rated;
    private LocalDateTime released;
    private String[] cast;
    private int runtime;
    private String[] countries;
    private String type;


}
