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
        String theatersString = null;
        if(apikeyOptional.isPresent()){
            key = apikeyOptional.get();
            List<TheaterDTO> users = theaterDAO.findAll();
            theatersString = mapper.writeValueAsString(users);
            System.out.println(key);
            responseEntity = new ResponseEntity<>(theatersString, httpHeaders, HttpStatus.OK);
        }else {
            responseEntity = new ResponseEntity<>("{\"message\":\"API key " + apikey + " not valid\"}", httpHeaders, HttpStatus.UNAUTHORIZED);
        }
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody TheaterDTO theater, String apikey){
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        Apikey key = null;
        ResponseEntity<String> responseEntity = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if(apikeyOptional.isPresent()){
            key = apikeyOptional.get();
            if ("admin".equals(key.getRole())) {
                theater.setId(new ObjectId());
                this.theaterDAO.create(theater);
                responseEntity = new ResponseEntity<>("{\"message\":\"Theater with ID " + theater.getId() + " has been created successfully!\"}", httpHeaders, HttpStatus.CREATED);
            } else {
                responseEntity =  new ResponseEntity<>("{\"message\":\"You do not have permission to add new theater!\"}", httpHeaders, HttpStatus.UNAUTHORIZED);
            }
        }else {
            responseEntity = new ResponseEntity<>("{\"message\":\"API key " + apikey + " not valid\"}", httpHeaders, HttpStatus.UNAUTHORIZED);
        }
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id, String apikey){
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        Apikey key = null;
        ResponseEntity<String> responseEntity = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if(!theaterDAO.findById(new ObjectId(id)).isPresent()) {
            responseEntity = new ResponseEntity<>("{\"message\":\"Theater with ID " + id + " not found!\"}", httpHeaders, HttpStatus.NOT_FOUND);
        } else {
            if(apikeyOptional.isPresent()){
                key = apikeyOptional.get();
                if ("admin".equals(key.getRole())) {
                    this.theaterDAO.deleteById(new ObjectId(id));
                    responseEntity = new ResponseEntity<>("{\"message\":\"Theater with ID " + id + " has been successfully deleted!\"}", httpHeaders, HttpStatus.OK);
                } else {
                    responseEntity =  new ResponseEntity<>("{\"message\":\"You do not have permission to delete a theater!\"}", httpHeaders, HttpStatus.UNAUTHORIZED);
                }
            }else {
                responseEntity = new ResponseEntity<>("{\"message\":\"API key " + apikey + " not valid\"}", httpHeaders, HttpStatus.UNAUTHORIZED);
            }
        }
        return responseEntity;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody TheaterDTO theater, @PathVariable("id") String id, String apikey) {
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        Apikey key = null;
        ResponseEntity<String> responseEntity = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if(!theaterDAO.findById(new ObjectId(id)).isPresent()) {
            responseEntity = new ResponseEntity<>("{\"message\":\"Theater with ID " + id + " not found!\"}", httpHeaders, HttpStatus.NOT_FOUND);
        } else {
            if(apikeyOptional.isPresent()){
                key = apikeyOptional.get();
                if ("admin".equals(key.getRole())) {
                    theater.setId(new ObjectId(id));
                    theaterDAO.update(new ObjectId(id), theater);
                    responseEntity = new ResponseEntity<>("{\"message\":\"Theater with ID " + id + " has been successfully updated!\"}", httpHeaders, HttpStatus.OK);
                } else {
                    responseEntity =  new ResponseEntity<>("{\"message\":\"You do not have permission to update a theater!\"}", httpHeaders, HttpStatus.UNAUTHORIZED);
                }
            }else {
                responseEntity = new ResponseEntity<>("{\"message\":\"API key " + apikey + " not valid\"}", httpHeaders, HttpStatus.UNAUTHORIZED);
            }
        }
        return responseEntity;
    }

}