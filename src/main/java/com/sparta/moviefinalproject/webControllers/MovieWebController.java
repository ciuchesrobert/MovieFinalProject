package com.sparta.moviefinalproject.webControllers;

import com.sparta.moviefinalproject.repositories.MovieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movies")
public class MovieWebController {
    final MovieRepository movieRepository;

    public MovieWebController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


}
