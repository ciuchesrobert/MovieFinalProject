package com.sparta.moviefinalproject.controllers;

import com.sparta.moviefinalproject.repositories.TheaterRepository;
import org.springframework.stereotype.Controller;

@Controller
public class TheaterWebController {
    final TheaterRepository theaterRepository;

    public TheaterWebController(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }
}
