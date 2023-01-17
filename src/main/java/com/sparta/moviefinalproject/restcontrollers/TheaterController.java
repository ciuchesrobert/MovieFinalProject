package com.sparta.moviefinalproject.restControllers;

import com.sparta.moviefinalproject.entities.Movie;
import com.sparta.moviefinalproject.entities.Theater;
import com.sparta.moviefinalproject.repositories.TheaterRepository;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/theaters")
public class TheaterController {
    final TheaterRepository theaterRepository;

    public TheaterController(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    @GetMapping("/test")
    public Optional<Theater> test() {
        return theaterRepository.findById(new ObjectId("573a1390f29313caabcd4135"));
    }

    @GetMapping("/")
    public Optional<Theater> findById(@RequestParam String id) {
        return theaterRepository.findById(new ObjectId(id));
    }

    @GetMapping("/all")
    public List<Theater> findAll() {
        return theaterRepository.findAll();
    }

    @PostMapping("/create")
    public Theater create(@RequestBody Theater theater){

        return this.theaterRepository.save(theater);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam String id){
        this.theaterRepository.deleteById(new ObjectId(id));
    }

    @PutMapping("/update")
    public Theater update(@RequestBody Theater theater, @RequestParam String id) {
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
