package com.sparta.moviefinalproject.dtos;

import com.sparta.moviefinalproject.converters.subtypes.AwardConverter;
import com.sparta.moviefinalproject.converters.subtypes.ImdbConverter;
import com.sparta.moviefinalproject.converters.subtypes.TomatoConverter;
import com.sparta.moviefinalproject.dtos.subdtos.AwardDTO;
import com.sparta.moviefinalproject.dtos.subdtos.ImdbDTO;
import com.sparta.moviefinalproject.dtos.subdtos.TomatoDTO;
import com.sparta.moviefinalproject.entities.Movie;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

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
    private Integer numMflixComments;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDTO movieDTO = (MovieDTO) o;
        return Objects.equals(id, movieDTO.id) && Objects.equals(awards, movieDTO.awards) && Arrays.equals(directors, movieDTO.directors) && Objects.equals(fullPlot, movieDTO.fullPlot) && Arrays.equals(languages, movieDTO.languages) && Objects.equals(numMflixComments, movieDTO.numMflixComments) && Objects.equals(plot, movieDTO.plot) && Objects.equals(title, movieDTO.title) && Arrays.equals(writers, movieDTO.writers) && Arrays.equals(genres, movieDTO.genres) && Objects.equals(lastUpdated, movieDTO.lastUpdated) && Objects.equals(poster, movieDTO.poster) && Objects.equals(tomatoes, movieDTO.tomatoes) && Objects.equals(year, movieDTO.year) && Objects.equals(imdb, movieDTO.imdb) && Objects.equals(rated, movieDTO.rated) && Objects.equals(released, movieDTO.released) && Arrays.equals(cast, movieDTO.cast) && Objects.equals(runtime, movieDTO.runtime) && Arrays.equals(countries, movieDTO.countries) && Objects.equals(type, movieDTO.type);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, awards, fullPlot, numMflixComments, plot, title, lastUpdated, poster, tomatoes, year, imdb, rated, released, runtime, type);
        result = 31 * result + Arrays.hashCode(directors);
        result = 31 * result + Arrays.hashCode(languages);
        result = 31 * result + Arrays.hashCode(writers);
        result = 31 * result + Arrays.hashCode(genres);
        result = 31 * result + Arrays.hashCode(cast);
        result = 31 * result + Arrays.hashCode(countries);
        return result;
    }
}
