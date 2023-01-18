package com.sparta.moviefinalproject.dtos;

import com.sparta.moviefinalproject.converters.subtypeconverters.AwardConverter;
import com.sparta.moviefinalproject.converters.subtypeconverters.ImdbConverter;
import com.sparta.moviefinalproject.converters.subtypeconverters.TomatoConverter;
import com.sparta.moviefinalproject.dtos.subdtos.AwardDto;
import com.sparta.moviefinalproject.dtos.subdtos.ImdbDto;
import com.sparta.moviefinalproject.dtos.subdtos.TomatoDto;
import com.sparta.moviefinalproject.entities.Movie;
import com.sparta.moviefinalproject.entities.subentities.Award;
import com.sparta.moviefinalproject.entities.subentities.Imdb;
import com.sparta.moviefinalproject.entities.subentities.Tomato;
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
    private String year;
    private ImdbDto imdb;
    private String rated;
    private LocalDateTime released;
    private String[] cast;
    private int runtime;
    private String[] countries;
    private String type;

    public boolean dtoEqualsEntity(Movie obj) {
        AwardDto awardConverted = new AwardConverter().entityToDto(obj.getAwards());
        TomatoDto tomatoConverted = new TomatoConverter().entityToDto(obj.getTomatoes());
        ImdbDto imdbConverted = new ImdbConverter().entityToDto(obj.getImdb());
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
