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
    @GetMapping("/basic/search/{id}")
    public String findMovieById(Model model, ObjectId id){
        ObjectId objectId = new ObjectId(id);
        Movie movie = movieRepository.findById(objectId).orElse(null);
        System.out.println(movie);
        model.addAttribute("movie", movie);
        return "movie/displayMovie";
    }

    @PostMapping("/basic/search/success")
    public String findMoviesByIdSuccess(@ModelAttribute("movie") Movie movie, Model model){
        movie = movieRepository.findById( movie.getId() ).orElse(null);
        model.addAttribute("movie", movie);
        return "movieDisplaySuccess";
    }

    @GetMapping("/basic/search/all")
    public String getAllMovies(Model model){
        List<Movie> movies = movieRepository.findAll();
        model.addAttribute("movies", movies);
        return "movie/displayAllMovies";
    }


    // ------------------ CREATE
    @GetMapping("/admin/create")
    public String createMovie(Model model){
        Movie movie = new Movie();
        ObjectId objectId = new ObjectId();
        movie.setId(objectId);
        model.addAttribute("movie", movie);
        return "movie/createMovie";
    }

    @PostMapping("/admin/create/success")
    public String createMovieSuccess(@ModelAttribute("movie")Movie movie){
        System.out.println(movie);
        movieRepository.save(movie);
        return "movie/createMovieSuccess";
    }

    // ------------------------ UPDATE
    @GetMapping("/admin/update/{id}")
    public String updateMovie(@PathVariable("id")String id, Model model){
        ObjectId objectId = new ObjectId(id);
        Movie movie = movieRepository.findById(objectId).orElse(null);
        model.addAttribute( "movie", movie);
        return "movie/updateMovie";
    }

    @PostMapping("/admin/update/success")
    public String updateMovieSuccess(@ModelAttribute("movie")Movie movie, Model model){
        movieRepository.save(movie); // - needs updating
        return "movie/updateMovieSuccess";
    }

    // ------------------------ DELETE
    @DeleteMapping("/admin/delete/{id}")
    public String deleteMovie(@PathVariable String id, Model model){
        ObjectId objectId = new ObjectId(id);
        Movie movie = movieRepository.findById(objectId).orElse(null);
        if (movie != null){
            movieRepository.delete(movie);
        }
        model.addAttribute("movie", movie);
        return "movie/deleteMovie";
    }
    @PostMapping("/admin/delete/success")
    public String deleteMovieSuccess(@ModelAttribute("movie")Movie movie, Model model){
        movie = movieRepository.findById(movie.getId()).get();
        movieRepository.delete(movie);
        model.addAttribute("movie", model);
        return "movieDeleteSuccess";
    }



}