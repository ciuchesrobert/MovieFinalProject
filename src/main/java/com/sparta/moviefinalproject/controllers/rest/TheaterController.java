package com.sparta.moviefinalproject.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.moviefinalproject.daos.implementations.TheaterDAO;
import com.sparta.moviefinalproject.dtos.TheaterDTO;
import com.sparta.moviefinalproject.entities.Apikey;
import com.sparta.moviefinalproject.repositories.ApikeyRepository;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
    public Optional<TheaterDTO> findById(@PathVariable("id") String id, String apikey) throws JsonProcessingException {
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        if(!apikeyOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "API key is not authorized!");
        }
        return theaterDAO.findById(new ObjectId(id));
    }

    @GetMapping("/search")
    public List<TheaterDTO> findByAddress(@RequestParam("address") String address) {
        List<TheaterDTO> theaters = new ArrayList<>();
        theaters.addAll(theaterDAO.findAllTheatersByLocation_Address_Street1ContainingIgnoreCase(address));
        theaters.addAll(theaterDAO.findAllTheatersByLocation_Address_CityContainingIgnoreCase(address));
        theaters.addAll(theaterDAO.findAllTheatersByLocation_Address_StateContainingIgnoreCase(address));
        theaters.addAll(theaterDAO.findAllTheatersByLocation_Address_ZipcodeContainingIgnoreCase(address));
        return theaters.stream().distinct().toList();
    }

    @GetMapping
    public List<TheaterDTO> findAll(String apikey) throws JsonProcessingException {
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        if(!apikeyOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "API key is not authorized!");
        }
        return theaterDAO.findAll();
    }

    @PostMapping
    public void create(@RequestBody TheaterDTO theater, String apikey){
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        if(!apikeyOptional.isPresent() && !"admin".equals(apikeyOptional.get().getRole())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "API key is not authorized!");
        }
        theater.setId(new ObjectId());
        this.theaterDAO.create(theater);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id, String apikey){
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        if(!apikeyOptional.isPresent() && !"admin".equals(apikeyOptional.get().getRole())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "API key is not authorized!");
        }
        this.theaterDAO.deleteById(new ObjectId(id));
    }

    @PutMapping("/{id}")
    public TheaterDTO update(@RequestBody TheaterDTO theater, @PathVariable("id") String id, String apikey) {
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        if(!apikeyOptional.isPresent() && !"admin".equals(apikeyOptional.get().getRole())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "API key is not authorized!");
        }
        return theaterDAO.update(new ObjectId(id), theater);
    }

}