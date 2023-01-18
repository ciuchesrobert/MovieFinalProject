package com.sparta.moviefinalproject.controllers.rest;


import com.sparta.moviefinalproject.entities.Movie;
import com.sparta.moviefinalproject.repositories.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/{id}")
    public Optional<Movie> findById(@PathVariable("id") String id) {
        return movieRepository.findById(new ObjectId(id));
    }

    @GetMapping
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movie create(@RequestBody Movie movie){

        return this.movieRepository.save(movie);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id){
        this.movieRepository.deleteById(new ObjectId(id));
    }

    @PutMapping("/{id}")
    public Movie update(@RequestBody Movie movie, @PathVariable("id") String id) {
        Optional<Movie> movieOptional = this.movieRepository.findById(new ObjectId(id));

        if (movieOptional.isPresent()) {
            Movie original = movieOptional.get();
            if (movie.getYear() != null) {
                original.setYear(movie.getYear());
            }
            if(movie.getRated() != null){
                original.setRated(movie.getRated());
            }
            if(movie.getTitle() != null){
                original.setTitle(movie.getTitle());
            }
            if(movie.getCast() != null){
                original.setCast(movie.getCast());
            }
            return this.movieRepository.save(original);
        }
        return new Movie();
    }

}