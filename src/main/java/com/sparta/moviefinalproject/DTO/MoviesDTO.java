package com.sparta.moviefinalproject.DTO;

import com.sparta.moviefinalproject.DBObjects.Award;
import com.sparta.moviefinalproject.DBObjects.IMDB;
import com.sparta.moviefinalproject.DBObjects.Tomato;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class MoviesDTO {
    private String _id;
    private Award awards;

    private ArrayList<String> directors;

    private String fullplot;

    private ArrayList<String> languages;
    private Integer num_mflix_comments;

    private String plot;
    private String title;

    private ArrayList<String> writers;

    private ArrayList<String> genres;
    private String lastupdated;

    private String poster;

    private Tomato tomatoes;

    private String year;

    private IMDB imdb;
    private String rated;

    private LocalDateTime released;

    private ArrayList<String> cast;

    private Integer runtime;

    private ArrayList<String> countries;

    private String type;
}
