package com.sparta.moviefinalproject.entities;import com.sparta.moviefinalproject.entities.subentities.Award;
import com.sparta.moviefinalproject.entities.subentities.Imdb;
import com.sparta.moviefinalproject.entities.subentities.Tomato;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private Award awards;
    private String[] directors;
    private String fullplot;
    private String[] languages;
    private int num_mflix_comments;
    private String plot;
    private String title;
    private String[] writers;
    private String[] genres;
    private String lastupdated;
    private String poster;
    @Embedded
    private Tomato tomatoes;
    private int year;
    @Embedded
    private Imdb imdb;
    private String rated;
    private String released;
    private String[] cast;
    private int runtime;
    private String[] countries;
    private String type;

}