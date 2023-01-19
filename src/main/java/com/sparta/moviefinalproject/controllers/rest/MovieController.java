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
            responseEntity = new ResponseEntity<>("{\"message\":\"API key " + apikey + " not valid\"}", httpHeaders, HttpStatus.UNAUTHORIZED);
        }
        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<String> findAll(String apikey) throws JsonProcessingException {
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        Apikey key = null;
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        ResponseEntity<String> responseEntity = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        String movieString = null;
        if(apikeyOptional.isPresent()){
            key = apikeyOptional.get();
            List<MovieDTO> movie = movieDAO.findAll();
            movieString = mapper.writeValueAsString(movie);
            System.out.println(key);
            responseEntity = new ResponseEntity<>(movieString, httpHeaders, HttpStatus.OK);
        }else {
            responseEntity = new ResponseEntity<>("{\"message\":\"API key " + apikey + " not valid\"}", httpHeaders, HttpStatus.UNAUTHORIZED);
        }
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody MovieDTO movieDTO, String apikey){
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        Apikey key = null;
        ResponseEntity<String> responseEntity = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if(apikeyOptional.isPresent()){
            key = apikeyOptional.get();
            if("admin".equals(key.getRole())){
                movieDTO.setId(new ObjectId());
                movieDTO.setLastUpdated(LocalDateTime.now());
                this.movieDAO.create(movieDTO);
                responseEntity = new ResponseEntity<>("{\"message\":\"Movie with ID " + movieDTO.getId() + " has been created successfully!\"}", httpHeaders, HttpStatus.CREATED);
            }else{
                responseEntity =  new ResponseEntity<>("{\"message\":\"You do not have permission to add new Movie!\"}", httpHeaders, HttpStatus.UNAUTHORIZED);
            }
        }else {
            responseEntity = new ResponseEntity<>("{\"message\":\"API key " + apikey + " not valid\"}", httpHeaders, HttpStatus.UNAUTHORIZED);
        }
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id, String apikey){
        Optional<MovieDTO> movieDTOOptional = movieDAO.findById(new ObjectId(id));
        ResponseEntity<String> responseEntity = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if(movieDTOOptional.isPresent()){
            Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
            Apikey key = null;
            if(apikeyOptional.isPresent()){
                key = apikeyOptional.get();
                if("admin".equals(key.getRole())){
                    this.movieDAO.deleteById(new ObjectId(id));
                    responseEntity = new ResponseEntity<>("{\"message\":\"Movie with ID " + id + " has been deleted successfully!\"}", httpHeaders, HttpStatus.OK);
                }else{
                    responseEntity =  new ResponseEntity<>("{\"message\":\"You do not have permission to add new movie!\"}", httpHeaders, HttpStatus.UNAUTHORIZED);
                }
            }else {
                responseEntity = new ResponseEntity<>("{\"message\":\"API key " + apikey + " not valid\"}", httpHeaders, HttpStatus.UNAUTHORIZED);
            }
        }else{
            responseEntity = new ResponseEntity<>("{\"message\":\"The Movie with ID " + id + " does not exist\"}", httpHeaders, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody MovieDTO movieDTO, @PathVariable("id") String id, String apikey) {
        Optional<MovieDTO> movieDTOOptional = movieDAO.findById(new ObjectId(id));
        ResponseEntity<String> responseEntity = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if(movieDTOOptional.isPresent()){
            Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
            Apikey key = null;
            if(apikeyOptional.isPresent()){
                key = apikeyOptional.get();
                if("admin".equals(key.getRole())){
                    movieDTO.setId(new ObjectId(id));
                    this.movieDAO.update(new ObjectId(id), movieDTO);
                    responseEntity = new ResponseEntity<>("{\"message\":\"Movie with ID " + id + " has been updated successfully!\"}", httpHeaders, HttpStatus.OK);
                }else{
                    responseEntity =  new ResponseEntity<>("{\"message\":\"You do not have permission to update movie!\"}", httpHeaders, HttpStatus.UNAUTHORIZED);
                }
            }else {
                responseEntity = new ResponseEntity<>("{\"message\":\"API key " + apikey + " not valid\"}", httpHeaders, HttpStatus.UNAUTHORIZED);
            }
        }else{
            responseEntity = new ResponseEntity<>("{\"message\":\"The Movie with ID " + id + " does not exist\"}", httpHeaders, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }


}