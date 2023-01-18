package com.sparta.moviefinalproject.controllers.rest;

import com.sparta.moviefinalproject.entities.Theater;
import com.sparta.moviefinalproject.repositories.TheaterRepository;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/theaters")
public class TheaterController {
    private final TheaterRepository theaterRepository;

    public TheaterController(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    @GetMapping("/{id}")
    public Optional<Theater> findById(@PathVariable("id") String id) {
        return theaterRepository.findById(new ObjectId(id));
    }

    @GetMapping
    public List<Theater> findAll() {
        return theaterRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Theater create(@RequestBody Theater theater){

        return this.theaterRepository.save(theater);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id){
        this.theaterRepository.deleteById(new ObjectId(id));
    }

    @PutMapping("/{id}")
    public Theater update(@RequestBody Theater theater, @PathVariable("id") String id) {
        Optional<Theater> theaterOptional = this.theaterRepository.findById(new ObjectId(id));

        if (theaterOptional.isPresent()) {
            Theater original = theaterOptional.get();
            if (theater.getLocation() != null) {
                original.setLocation(theater.getLocation());
            }
            return this.theaterRepository.save(original);
        }

        return new Theater();
    }

}