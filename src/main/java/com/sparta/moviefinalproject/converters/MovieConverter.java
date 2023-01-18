package com.sparta.moviefinalproject.converters;


import com.sparta.moviefinalproject.converters.subtypes.*;
import com.sparta.moviefinalproject.dtos.MovieDTO;
import com.sparta.moviefinalproject.entities.Movie;

public class MovieConverter implements Converter<MovieDTO, Movie> {

    @Override
    public Movie dtoToEntity(MovieDTO movieDto) {
        Movie movie = new Movie(movieDto.getId(),
                null,
                movieDto.getDirectors(),
                movieDto.getFullPlot(),
                movieDto.getLanguages(),
                movieDto.getNumMflixComments(),
                movieDto.getPlot(),
                movieDto.getTitle(),
                movieDto.getWriters(),
                movieDto.getGenres(),
                movieDto.getLastUpdated(),
                movieDto.getPoster(),
                null,
                movieDto.getYear(),
                null,
                movieDto.getRated(),
                movieDto.getReleased(),
                movieDto.getCast(),
                movieDto.getRuntime(),
                movieDto.getCountries(),
                movieDto.getType());

        if(movieDto.getAwards() != null) {
            movie.setAwards(new AwardConverter().dtoToEntity(movieDto.getAwards()));
        }
        if(movieDto.getTomatoes() != null) {
            movie.setTomatoes(new TomatoConverter().dtoToEntity(movieDto.getTomatoes()));
        }
        if(movieDto.getImdb() != null) {
            movie.setImdb(new ImdbConverter().dtoToEntity(movieDto.getImdb()));
        }

        return movie;
    }

    @Override
    public MovieDTO entityToDto(Movie movie) {
        MovieDTO movieDto = new MovieDTO(movie.getId(),
                null,
                movie.getDirectors(),
                movie.getFullPlot(),
                movie.getLanguages(),
                movie.getNumMflixComments(),
                movie.getPlot(),
                movie.getTitle(),
                movie.getWriters(),
                movie.getGenres(),
                movie.getLastUpdated(),
                movie.getPoster(),
                null,
                movie.getYear(),
                null,
                movie.getRated(),
                movie.getReleased(),
                movie.getCast(),
                movie.getRuntime(),
                movie.getCountries(),
                movie.getType());

        if(movie.getAwards() != null) {
            movieDto.setAwards(new AwardConverter().entityToDto(movie.getAwards()));
        }
        if(movie.getTomatoes() != null) {
            movieDto.setTomatoes(new TomatoConverter().entityToDto(movie.getTomatoes()));
        }
        if(movie.getImdb() != null) {
            movieDto.setImdb(new ImdbConverter().entityToDto(movie.getImdb()));
        }

        return movieDto;
    }
}
