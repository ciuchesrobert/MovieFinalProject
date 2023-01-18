package com.sparta.moviefinalproject.controllers.web;

import com.sparta.moviefinalproject.daos.implementations.MovieDAO;
import com.sparta.moviefinalproject.dtos.MovieDTO;
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
    private final MovieDAO movieDAO;

    public MovieWebController(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }

    @RequestMapping("/moviesHome")
    public String moviesHome(Model model){
        return "moviesHome/moviesHome";
    }

    // ------------- READ
    @GetMapping("/basic/search/{id}")
    public String findMovieById(Model model, String id){
        ObjectId objectId = new ObjectId(id);
        MovieDTO movie = movieDAO.findById(objectId).orElse(null);
        System.out.println(movie);
        model.addAttribute("movie", movie);
        return "movie/displayMovie";
    }

    @PostMapping("/basic/search/success")
    public String findMoviesByIdSuccess(@ModelAttribute("movie") MovieDTO movie, Model model){
        movie = movieDAO.findById( movie.getId() ).orElse(null);
        model.addAttribute("movie", movie);
        return "movieDisplaySuccess";
    }

    @GetMapping("/basic/search/all")
    public String getAllMovies(Model model){
        List<MovieDTO> movies = movieDAO.findAll();
        model.addAttribute("movies", movies);
        return "movie/displayAllMovies";
    }


    // ------------------ CREATE
    @GetMapping("/admin/create")
    public String createMovie(Model model){
        MovieDTO movie = new MovieDTO();
        ObjectId objectId = new ObjectId();
        movie.setId(objectId);
        model.addAttribute("movie", movie);
        return "movie/createMovie";
    }

    @PostMapping("/admin/create/success")
    public String createMovieSuccess(@ModelAttribute("movie")MovieDTO movie){
        System.out.println(movie);
        movieDAO.create(movie);
        return "movie/createMovieSuccess";
    }

    // ------------------------ UPDATE
    @GetMapping("/admin/update/{id}")
    public String updateMovie(@PathVariable("id")String id, Model model){
        ObjectId objectId = new ObjectId(id);
        MovieDTO movie = movieDAO.findById(objectId).orElse(null);
        model.addAttribute( "movie", movie);
        return "movie/updateMovie";
    }

    @PostMapping("/admin/update/success")
    public String updateMovieSuccess(@ModelAttribute("movie")MovieDTO movie, Model model){
        movieDAO.update(movie.getId(), movie); // - needs updating
        return "movie/updateMovieSuccess";
    }

    // ------------------------ DELETE
    @DeleteMapping("/admin/delete/{id}")
    public String deleteMovie(@PathVariable String id, Model model){
        ObjectId objectId = new ObjectId(id);
        MovieDTO movie = movieDAO.findById(objectId).orElse(null);
        if (movie != null){
            movieDAO.deleteById(movie.getId());
        }
        model.addAttribute("movie", movie);
        return "movie/deleteMovie";
    }
    @PostMapping("/admin/delete/success")
    public String deleteMovieSuccess(@ModelAttribute("movie")MovieDTO movie, Model model){
        movie = movieDAO.findById(movie.getId()).get();
        movieDAO.deleteById(movie.getId());
        model.addAttribute("movie", model);
        return "movieDeleteSuccess";
    }



}