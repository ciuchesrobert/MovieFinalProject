package com.sparta.moviefinalproject.webcontrollers;

import com.sparta.moviefinalproject.repositories.TheaterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/theaters")
public class TheaterWebController {
    private final TheaterRepository theaterRepository;

    public TheaterWebController(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }
}
