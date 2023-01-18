package com.sparta.moviefinalproject.controllers.rest;


import com.sparta.moviefinalproject.daos.implementations.MovieDAO;
import com.sparta.moviefinalproject.dtos.MovieDTO;
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


    private final MovieDAO movieDAO;

    public MovieController(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }

    @GetMapping("/{id}")
    public Optional<MovieDTO> findById(@PathVariable("id") String id) {
        return movieDAO.findById(new ObjectId(id));
    }

    @GetMapping
    public List<MovieDTO> findAll() {
        return movieDAO.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody MovieDTO movieDTO){

        this.movieDAO.create(movieDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id){
        this.movieDAO.deleteById(new ObjectId(id));
    }

    @PutMapping("/{id}")
    public void update(@RequestBody MovieDTO movieDTO, @PathVariable("id") String id) {
        this.movieDAO.update(new ObjectId(id), movieDTO);
    }


}