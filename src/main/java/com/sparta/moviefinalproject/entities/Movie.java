package com.sparta.moviefinalproject.entities;

import com.mongodb.lang.NonNull;
import com.mongodb.lang.Nullable;
import com.sparta.moviefinalproject.entities.subentities.Award;
import com.sparta.moviefinalproject.entities.subentities.Imdb;
import com.sparta.moviefinalproject.entities.subentities.Tomato;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import com.sparta.moviefinalproject.converters.subtypes.AwardConverter;
import com.sparta.moviefinalproject.converters.subtypes.ImdbConverter;
import com.sparta.moviefinalproject.converters.subtypes.TomatoConverter;
import com.sparta.moviefinalproject.dtos.MovieDTO;
import com.sparta.moviefinalproject.dtos.UserDTO;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
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
    private String year;
    @Embedded
    @Column(name = "imdb")
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
    private String type;

    public boolean entityEqualsDto(MovieDTO obj) {
        Award awardConverted = new AwardConverter().dtoToEntity(obj.getAwards());
        Tomato tomatoConverted = new TomatoConverter().dtoToEntity(obj.getTomatoes());
        //Imdb imdbConverted = new ImdbConverter().dtoToEntity(obj.getImdb());
        if (this.getId() == obj.getId() &&
                this.getAwards().equals(awardConverted) &&
                this.getDirectors() == obj.getDirectors() &&
                this.getFullPlot() == obj.getFullPlot() &&
                this.getLanguages() == obj.getLanguages() &&
                this.getNumMflixComments() == obj.getNumMflixComments() &&
                this.getPlot() == obj.getPlot() &&
                this.getTitle() == obj.getTitle() &&
                this.getWriters() == obj.getWriters() &&
                this.getGenres() == obj.getGenres() &&
                this.getLastUpdated() == obj.getLastUpdated() &&
                this.getPoster() == obj.getPoster() &&
                this.getTomatoes().equals(tomatoConverted) &&
                this.getYear() == obj.getYear() &&
                this.getImdb().equals(new ImdbConverter().dtoToEntity(obj.getImdb())) &&
                this.getRated() == obj.getRated() &&
                this.getReleased() == obj.getReleased() &&
                this.getCast() == obj.getCast() &&
                this.getRuntime() == obj.getRuntime() &&
                this.getCountries() == obj.getCountries() &&
                this.getType() == obj.getType()){
            return true;
        } else {
            return false;
        }
    }

}