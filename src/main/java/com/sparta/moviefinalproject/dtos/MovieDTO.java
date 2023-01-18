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
import com.sparta.moviefinalproject.converters.subtypes.AwardConverter;
import com.sparta.moviefinalproject.converters.subtypes.ImdbConverter;
import com.sparta.moviefinalproject.converters.subtypes.TomatoConverter;
import java.time.LocalDateTime;
import com.sparta.moviefinalproject.entities.Movie;
import com.sparta.moviefinalproject.entities.subentities.Award;
import com.sparta.moviefinalproject.entities.subentities.Imdb;
import com.sparta.moviefinalproject.entities.subentities.Tomato;

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

    public boolean dtoEqualsEntity(Movie obj) {
        AwardDTO awardConverted = new AwardConverter().entityToDto(obj.getAwards());
        TomatoDTO tomatoConverted = new TomatoConverter().entityToDto(obj.getTomatoes());
        ImdbDTO imdbConverted = new ImdbConverter().entityToDto(obj.getImdb());
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
                this.getImdb().equals(imdbConverted) &&
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
