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

    @NonNull
    private ObjectId id;
    @Embedded
    @NonNull
    private AwardDTO awards;
    @Nullable
    private String[] directors;
    @Nullable
    private String fullPlot;
    @Nullable
    private String[] languages;
    @Nullable
    private Integer numMflixComments;
    @Nullable
    private String plot;
    @NonNull
    private String title;
    @Nullable
    private String[] writers;
    @Nullable
    private String[] genres;
    @Nullable
    private LocalDateTime lastUpdated;
    @Nullable
    private String poster;
    @Embedded
    @Nullable
    private TomatoDTO tomatoes;
    @NonNull
    private String year;
    @Embedded
    @NonNull
    private Imdb imdb;
    @Nullable
    private String rated;
    @Nullable
    private LocalDateTime released;
    @Nullable
    private String[] cast;
    @Nullable
    private Integer runtime;
    @Nullable
    private String[] countries;
    @NonNull
    private String type;


}
