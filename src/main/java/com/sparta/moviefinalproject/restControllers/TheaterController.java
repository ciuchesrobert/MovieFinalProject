package com.sparta.moviefinalproject.restControllers;

import com.sparta.moviefinalproject.repositories.TheaterRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TheaterController {
    final TheaterRepository theaterRepository;

    public TheaterController(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

}
