package com.sparta.moviefinalproject.daos.implementations;

import com.sparta.moviefinalproject.converters.MovieConverter;
import com.sparta.moviefinalproject.dtos.MovieDto;
import com.sparta.moviefinalproject.entities.Movie;
import com.sparta.moviefinalproject.repositories.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieDao implements com.sparta.moviefinalproject.daos.interfaces.MovieDao {

    private final MovieRepository movieRepo;

    public MovieDao(MovieRepository movieRepo) {
        this.movieRepo = movieRepo;
    }

    @Override
    public void create(MovieDto movieDto) {
        movieRepo.insert(new MovieConverter().dtoToEntity(movieDto));
    }

    @Override
    public Optional<MovieDto> findById(ObjectId id) {
        if(movieRepo.findById(id).isPresent()) {
            Movie movie = movieRepo.findById(id).get();
            return Optional.of(new MovieConverter().entityToDto(movie));
        }
        return Optional.empty();
    }

    @Override
    public void update(ObjectId id, MovieDto updatedMovie) {
        Movie movie = new MovieConverter().dtoToEntity(updatedMovie);
        movie.setId(id);
        movieRepo.save(movie);
    }

    @Override
    public void deleteById(ObjectId id) {
        if(movieRepo.findById(id).isPresent()) {
            movieRepo.deleteById(id);
        }
    }

    @Override
    public List<MovieDto> findAll() {
        List<Movie> movies = movieRepo.findAll();
        List<MovieDto> movieDtos = new ArrayList<>();
        for(Movie movie : movies) {
            movieDtos.add(new MovieConverter().entityToDto(movie));
        }
        return movieDtos;
    }
}
