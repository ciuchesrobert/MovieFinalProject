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
    public ResponseEntity<String> findById(@PathVariable("id") String id, String apikey) throws JsonProcessingException {
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        Apikey key = null;
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        ResponseEntity<String> responseEntity = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        String commentString = null;
        if(apikeyOptional.isPresent()){
            key = apikeyOptional.get();
            System.out.println(id);
            MovieDTO movie = movieDAO.findById(new ObjectId(id)).get();
            System.out.println(movie);
            commentString = mapper.writeValueAsString(movie);
            System.out.println(key);
            responseEntity = new ResponseEntity<>(commentString, httpHeaders, HttpStatus.OK);
        }else {
            responseEntity = new ResponseEntity<>("{\"message\":\"API key " + apikey + " not valid\"}", httpHeaders, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
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