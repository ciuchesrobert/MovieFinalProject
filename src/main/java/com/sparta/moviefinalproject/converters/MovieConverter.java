package com.sparta.moviefinalproject.converters;


import com.sparta.moviefinalproject.converters.subtypes.*;
import com.sparta.moviefinalproject.dtos.MovieDTO;
import com.sparta.moviefinalproject.entities.Movie;

public class MovieConverter implements Converter<MovieDTO, Movie> {

    @Override
    public Movie dtoToEntity(MovieDTO movieDto) {
        return new Movie(movieDto.getId(),
                new AwardConverter().dtoToEntity(movieDto.getAwards()),
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
                new TomatoConverter().dtoToEntity(movieDto.getTomatoes()),
                movieDto.getYear(),
                movieDto.getImdb(),
                movieDto.getRated(),
                movieDto.getReleased(),
                movieDto.getCast(),
                movieDto.getRuntime(),
                movieDto.getCountries(),
                movieDto.getType());
    }

    @Override
    public MovieDTO entityToDto(Movie movie) {
        return new MovieDTO(movie.getId(),
                new AwardConverter().entityToDto(movie.getAwards()),
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
                new TomatoConverter().entityToDto(movie.getTomatoes()),
                movie.getYear(),
                movie.getImdb(),
                movie.getRated(),
                movie.getReleased(),
                movie.getCast(),
                movie.getRuntime(),
                movie.getCountries(),
                movie.getType());
    }
}
