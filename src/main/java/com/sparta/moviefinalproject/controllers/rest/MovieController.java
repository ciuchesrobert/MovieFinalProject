package com.sparta.moviefinalproject.controllers.rest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sparta.moviefinalproject.daos.implementations.MovieDAO;
import com.sparta.moviefinalproject.dtos.CommentDTO;
import com.sparta.moviefinalproject.dtos.MovieDTO;
import com.sparta.moviefinalproject.entities.Apikey;
import com.sparta.moviefinalproject.entities.Movie;
import com.sparta.moviefinalproject.repositories.ApikeyRepository;
import com.sparta.moviefinalproject.repositories.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {


    private final MovieDAO movieDAO;
    private final ApikeyRepository apikeyRepository;

    public MovieController(MovieDAO movieDAO, ApikeyRepository apikeyRepository) {
        this.movieDAO = movieDAO;
        this.apikeyRepository = apikeyRepository;
    }

    @GetMapping("/{id}")
    public Optional<MovieDTO> findById(@PathVariable("id") String id, String apikey) throws JsonProcessingException {
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        if(!apikeyOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "API key is not authorized!");
        }
        return this.movieDAO.findById(new ObjectId(id));
    }

    @GetMapping("/search")
    public List<MovieDTO> findByTitle(@RequestParam("title") String title, String apikey) {
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        if(!apikeyOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "API key is not authorized!");
        }
        return movieDAO.findAllMoviesByTitleContainingIgnoreCase(title);
    }

    @GetMapping
    public List<MovieDTO> findAll(String apikey) throws JsonProcessingException {
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        if(!apikeyOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "API key is not authorized!");
        }
        return movieDAO.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDTO create(@RequestBody MovieDTO movieDTO, String apikey){
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        if(!apikeyOptional.isPresent() && !"admin".equals(apikeyOptional.get().getRole())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "API key is not authorized!");
        }
        return movieDAO.create(movieDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id, String apikey){
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        if(!apikeyOptional.isPresent() && !"admin".equals(apikeyOptional.get().getRole())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "API key is not authorized!");
        }
        this.movieDAO.deleteById(new ObjectId(id));
    }

    @PutMapping("/{id}")
    public MovieDTO update(@RequestBody MovieDTO movieDTO, @PathVariable("id") String id, String apikey) {
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        if(!apikeyOptional.isPresent() && !"admin".equals(apikeyOptional.get().getRole())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "API key is not authorized!");
        }
        return this.movieDAO.update(new ObjectId(id), movieDTO);
    }

}