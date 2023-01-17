package com.sparta.moviefinalproject.restcontrollers;

import com.sparta.moviefinalproject.entities.Theater;
import com.sparta.moviefinalproject.repositories.TheaterRepository;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/theaters")
public class TheaterController {
    private final TheaterRepository theaterRepository;

    public TheaterController(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    @GetMapping
    public List<Theater> findAll() {
        return theaterRepository.findAll();
    }


    @GetMapping("/{id}")
    public Theater findById(@PathVariable("id") String id) {
        return theaterRepository.findById(new ObjectId(id)).get();
    }

}
