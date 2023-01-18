package com.sparta.moviefinalproject.controllers.rest;

import com.sparta.moviefinalproject.daos.implementations.TheaterDAO;
import com.sparta.moviefinalproject.dtos.TheaterDTO;
import com.sparta.moviefinalproject.entities.Theater;
import com.sparta.moviefinalproject.repositories.TheaterRepository;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/theaters")
public class TheaterController {
    private final TheaterDAO theaterDAO;

    public TheaterController(TheaterDAO theaterDAO) {
        this.theaterDAO = theaterDAO;
    }

    @GetMapping("/{id}")
    public Optional<TheaterDTO> findById(@PathVariable("id") String id) {
        return theaterDAO.findById(new ObjectId(id));
    }

    @GetMapping
    public List<TheaterDTO> findAll() {
        return theaterDAO.findAll();
    }

    @PostMapping
    public TheaterDTO create(@RequestBody TheaterDTO theater){

        this.theaterDAO.create(theater);
        return theater;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        this.theaterDAO.deleteById(new ObjectId(id));
    }

    @PutMapping("/{id}")
    public TheaterDTO update(@RequestBody TheaterDTO theater, @PathVariable("id") String id) {

        theaterDAO.update(new ObjectId(id), theater);
        return theater;

    }

}