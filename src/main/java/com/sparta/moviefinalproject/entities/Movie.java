package com.sparta.moviefinalproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.mongodb.lang.NonNull;
import com.mongodb.lang.Nullable;
import com.sparta.moviefinalproject.entities.subEntities.Award;
import com.sparta.moviefinalproject.entities.subEntities.Imdb;
import com.sparta.moviefinalproject.entities.subEntities.Tomato;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @NonNull
    private ObjectId id;
    @Embedded
    @Column(name = "awards")
    @NonNull
    private Award awards;
    @Column(name = "directors")
    @Nullable
    private String[] directors;
    @Column(name = "fullplot")
    @Nullable
    private String fullPlot;
    @Column(name = "languages")
    @Nullable
    private String[] languages;
    @Column(name = "num_mflix_comments")
    @Nullable
    private Integer numMflixComments;
    @Column(name = "plot")
    @Nullable
    private String plot;
    @Column(name = "title")
    @NonNull
    private String title;
    @Column(name = "writers")
    @Nullable
    private String[] writers;
    @Column(name = "genres")
    @Nullable
    private String[] genres;
    @Column(name = "lastupdated")
    @Nullable
    private LocalDateTime lastUpdated;
    @Column(name = "poster")
    @Nullable
    private String poster;
    @Embedded
    @Column(name = "tomatoes")
    @Nullable
    private Tomato tomatoes;
    @Column(name = "year")
    @NonNull
    private Integer year;
    @Embedded
    @Column(name = "imdb")
    @NonNull
    private Imdb imdb;
    @Column(name = "rated")
    @Nullable
    private String rated;
    @Column(name = "released")
    @Nullable
    private LocalDateTime released;
    @Column(name = "cast")
    @Nullable
    private String[] cast;
    @Column(name = "runtime")
    @Nullable
    private Integer runtime;
    @Column(name = "countries")
    @Nullable
    private String[] countries;
    @Column(name = "type")
    @NonNull
    private String type;

}