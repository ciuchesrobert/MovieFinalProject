package com.sparta.moviefinalproject.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sparta.moviefinalproject.daos.implementations.TheaterDAO;
import com.sparta.moviefinalproject.dtos.TheaterDTO;
import com.sparta.moviefinalproject.dtos.UserDTO;
import com.sparta.moviefinalproject.entities.Apikey;
import com.sparta.moviefinalproject.entities.Theater;
import com.sparta.moviefinalproject.repositories.ApikeyRepository;
import com.sparta.moviefinalproject.repositories.TheaterRepository;
import org.bson.types.ObjectId;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/theaters")
public class TheaterController {
    private final TheaterDAO theaterDAO;

    private final ApikeyRepository apikeyRepository;

    public TheaterController(TheaterDAO theaterDAO, ApikeyRepository apikeyRepository) {
        this.theaterDAO = theaterDAO;
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
        String theaterString = null;
        if(apikeyOptional.isPresent()){
            key = apikeyOptional.get();
            TheaterDTO theater = theaterDAO.findById(new ObjectId(id)).get();
            theaterString = mapper.writeValueAsString(theater);
            System.out.println(key);
            responseEntity = new ResponseEntity<>(theaterString, httpHeaders, HttpStatus.OK);
        }else {
            responseEntity = new ResponseEntity<>("{\"message\":\"API key " + apikey + " not valid\"}", httpHeaders, HttpStatus.NOT_FOUND);
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
        String theatersString = null;
        if(apikeyOptional.isPresent()){
            key = apikeyOptional.get();
            List<TheaterDTO> users = theaterDAO.findAll();
            theatersString = mapper.writeValueAsString(users);
            System.out.println(key);
            responseEntity = new ResponseEntity<>(theatersString, httpHeaders, HttpStatus.OK);
        }else {
            responseEntity = new ResponseEntity<>("{\"message\":\"API key " + apikey + " not valid\"}", httpHeaders, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TheaterDTO create(@RequestBody TheaterDTO theater){
        this.theaterDAO.create(theater);
        return theater;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id){
        this.theaterDAO.deleteById(new ObjectId(id));
    }

    @PutMapping("/{id}")
    public TheaterDTO update(@RequestBody TheaterDTO theater, @PathVariable("id") String id) {

        theaterDAO.update(new ObjectId(id), theater);
        return theater;

    }

}