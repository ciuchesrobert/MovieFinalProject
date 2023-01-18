package com.sparta.moviefinalproject.controllers.web;

import com.sparta.moviefinalproject.entities.Movie;
import com.sparta.moviefinalproject.repositories.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieWebController {
    final MovieRepository movieRepository;

    public MovieWebController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // ------------- READ
    @GetMapping("/basic/search")
    public String findMovieById(Model model, ObjectId id){
        Movie movie = new Movie();
        model.addAttribute("movie", movie);
        return "movieDisplay";
    }

    @PostMapping("/basic/search/success")
    public String findMoviesByIdSuccess(@ModelAttribute("movie") Movie movie, Model model){
        movie = movieRepository.findById( movie.getId() ).orElse(null);
        model.addAttribute("movie", movie);
        return "movieDisplaySuccess";
    }

    @GetMapping("/basic")
    public String getAllMovies(Model model){
        List<Movie> movies = movieRepository.findAll();
        model.addAttribute("movies", movies);
        return "displayAll";
    }


    // ------------------ CREATE
    @GetMapping("/admin/create")
    public String createMovie(Model model){
        Movie movie = new Movie();
        model.addAttribute("movie", movie);
        return "movieCreate";
    }

    @PostMapping("/admin/create/success")
    public String createMovieSuccess(@ModelAttribute("movie")Movie movie){
        movieRepository.save(movie);
        return "movieCreateSuccess";
    }

    // ------------------------ UPDATE
    @GetMapping("/admin/update/{id}")
    public String updateMovie(@PathVariable("id")ObjectId id, Model model){
        Movie movie = movieRepository.findById(id).orElse(null);
        model.addAttribute( "movie", movie);
        return "movieUpdate";
    }

    @PostMapping("/admin/update/success")
    public String updateMovieSuccess(@ModelAttribute("movie")Movie movie, Model model){
        movieRepository.save(movie); // - needs updating
        return "movieUpdateSucces";
    }

    // ------------------------ DELETE
    @DeleteMapping("/admin/delete/{id}")
    public String deleteMovie(@PathVariable ObjectId id, Model model){
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie != null){
            movieRepository.delete(movie);
        }
        model.addAttribute("movie", movie);
        return "movieDelete";
    }
    @PostMapping("/admin/delete/success")
    public String deleteMovieSuccess(@ModelAttribute("movie")Movie movie, Model model){
        movie = movieRepository.findById(movie.getId()).get();
        movieRepository.delete(movie);
        model.addAttribute("movie", model);
        return "movieDeleteSuccess";
    }



}