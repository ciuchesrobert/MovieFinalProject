package com.sparta.moviefinalproject.webControllers;

import com.sparta.moviefinalproject.repositories.MovieRepository;
import org.springframework.stereotype.Controller;

@Controller
public class MovieWebController {
    final MovieRepository movieRepository;

    public MovieWebController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

}
