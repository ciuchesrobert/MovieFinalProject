package com.sparta.moviefinalproject.daos.implementations;

import com.sparta.moviefinalproject.converters.MovieConverter;
import com.sparta.moviefinalproject.dtos.MovieDTO;
import com.sparta.moviefinalproject.entities.Movie;
import com.sparta.moviefinalproject.entities.User;
import com.sparta.moviefinalproject.repositories.CommentRepository;
import com.sparta.moviefinalproject.repositories.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieDAO implements com.sparta.moviefinalproject.daos.interfaces.MovieDAO {

    private final MovieRepository movieRepo;
    public MovieDAO(MovieRepository movieRepo) {
        this.movieRepo = movieRepo;
    }

    @Override
    public MovieDTO create(MovieDTO movieDto) {
        return new MovieConverter().entityToDto(movieRepo.save(new MovieConverter().dtoToEntity(movieDto)));
    }

    @Override
    public Optional<MovieDTO> findById(ObjectId id) {
        if(movieRepo.findById(id).isPresent()) {
            Movie movie = movieRepo.findById(id).get();
            return Optional.of(new MovieConverter().entityToDto(movie));
        }
        return Optional.empty();
    }

    @Override
    public MovieDTO update(ObjectId id, MovieDTO updatedMovie) {
        Movie movie = new MovieConverter().dtoToEntity(updatedMovie);
        movie.setId(id);
        movieRepo.save(movie);
        return new MovieConverter().entityToDto(movie);
    }

    @Override
    public void deleteById(ObjectId id) {
        if(movieRepo.findById(id).isPresent()) {
            movieRepo.deleteById(id);
        }
    }

    @Override
    public List<MovieDTO> findAll() {
        List<Movie> movies = movieRepo.findAll().stream()
                    .filter(movie -> movie.getYear().matches("^[0-9]{4}$")).toList();
        List<MovieDTO> movieDTOs = new ArrayList<>();
        for(Movie movie : movies) {
            movieDTOs.add(new MovieConverter().entityToDto(movie));
        }
        return movieDTOs;
    }

    public Page<Movie> findAllMoviesPagination(int pageNum){
        return moviesPage(PageRequest.of(pageNum, 10));
    }

    public Page<Movie> moviesPage(Pageable pageable){
        return movieRepo.findAll(pageable);
    }

    @Override
    public List<MovieDTO> findAllMoviesByTitleContainingIgnoreCase(String name) {
        List<MovieDTO> movieDTOS = new ArrayList<>();
        List<Movie> movies = movieRepo.findAllMoviesByTitleContainingIgnoreCase(name);
        for(Movie movie : movies) {
            movieDTOS.add(new MovieConverter().entityToDto(movie));
        }
        return movieDTOS;
    }
}
