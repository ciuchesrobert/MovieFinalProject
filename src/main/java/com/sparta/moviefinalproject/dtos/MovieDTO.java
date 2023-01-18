package com.sparta.moviefinalproject.dtos;

import com.mongodb.lang.NonNull;
import com.mongodb.lang.Nullable;
import com.sparta.moviefinalproject.dtos.subdtos.AwardDTO;
import com.sparta.moviefinalproject.dtos.subdtos.ImdbDTO;
import com.sparta.moviefinalproject.dtos.subdtos.TomatoDTO;
import com.sparta.moviefinalproject.entities.subentities.Award;
import com.sparta.moviefinalproject.entities.subentities.Imdb;
import com.sparta.moviefinalproject.entities.subentities.Tomato;
import jakarta.persistence.*;
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
    @Embedded
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
    @Embedded
    private TomatoDTO tomatoes;
    private String year;
    @Embedded
    private ImdbDTO imdb;
    private String rated;
    private LocalDateTime released;
    private String[] cast;
    private Integer runtime;
    private String[] countries;
    private String type;


}
