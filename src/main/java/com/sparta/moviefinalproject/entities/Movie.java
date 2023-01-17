package com.sparta.moviefinalproject.entities;

import com.sparta.moviefinalproject.entities.subEntities.Award;
import com.sparta.moviefinalproject.entities.subEntities.Imdb;
import com.sparta.moviefinalproject.entities.subEntities.Tomato;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private ObjectId id;
    @Embedded
    @Column(name = "awards")
    private Award awards;
    @Column(name = "directors")
    private String[] directors;
    @Column(name = "fullplot")
    private String fullPlot;
    @Column(name = "languages")
    private String[] languages;
    @Column(name = "num_mflix_comments")
    private int numMflixComments;
    @Column(name = "plot")
    private String plot;
    @Column(name = "title")
    private String title;
    @Column(name = "writers")
    private String[] writers;
    @Column(name = "genres")
    private String[] genres;
    @Column(name = "lastupdated")
    private LocalDateTime lastUpdated;
    @Column(name = "poster")
    private String poster;
    @Embedded
    @Column(name = "toatoes")
    private Tomato tomatoes;
    @Column(name = "year")
    private String year;
    @Embedded
    @Column(name = "imdb")
    private Imdb imdb;
    @Column(name = "rated")
    private String rated;
    @Column(name = "released")
    private LocalDateTime released;
    @Column(name = "cast")
    private String[] cast;
    @Column(name = "runtime")
    private int runtime;
    @Column(name = "countries")
    private String[] countries;
    @Column(name = "type")
    private String type;

}