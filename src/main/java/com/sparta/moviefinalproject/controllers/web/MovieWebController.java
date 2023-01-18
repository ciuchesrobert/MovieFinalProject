package com.sparta.moviefinalproject.controllers.web;

import com.sparta.moviefinalproject.daos.implementations.MovieDAO;
import com.sparta.moviefinalproject.dtos.MovieDTO;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieWebController {
    final MovieDAO movieDao;

    public MovieWebController(MovieDAO movieDao) {
        this.movieDao = movieDao;
    }

    // ------------- READ
    @GetMapping("/search")
    public String findMovieById(Model model, ObjectId id){
        MovieDTO movie = new MovieDTO();
        model.addAttribute("movie", movie);
        return "movieDisplay";
    }

    @PostMapping("/search/success")
    public String findMoviesByIdSuccess(@ModelAttribute("movie") MovieDTO movie, Model model){
        movie = movieDao.findById( movie.getId() ).orElse(null);
        model.addAttribute("movie", movie);
        return "movieDisplaySuccess";
    }

    @GetMapping
    public String getAllMovies(Model model){
        List<MovieDTO> movies = movieDao.findAll();
        model.addAttribute("movies", movies);
        return "displayAll";
    }


    // ------------------ CREATE
    @GetMapping("/create")
    public String createMovie(Model model){
        MovieDTO movie = new MovieDTO();
        model.addAttribute("movie", movie);
        return "movieCreate";
    }

    @PostMapping("/create/success")
    public String createMovieSuccess(@ModelAttribute("movie")MovieDTO movie){
        movieDao.create(movie);
        return "movieCreateSuccess";
    }

    // ------------------------ UPDATE
    @GetMapping("/update/{id}")
    public String updateMovie(@PathVariable("id")ObjectId id, Model model){
        MovieDTO movie = movieDao.findById(id).orElse(null);
        model.addAttribute( "movie", movie);
        return "movieUpdate";
    }

    @PostMapping("/update/success")
    public String updateMovieSuccess(@ModelAttribute("movie")MovieDTO movie, Model model){
        movieDao.update(movie.getId(), movie);
        return "movieUpdateSucces";
    }

    // ------------------------ DELETE
    @DeleteMapping("/delete/{id}")
    public String deleteMovie(@PathVariable ObjectId id, Model model){
        MovieDTO movie = movieDao.findById(id).orElse(null);
        model.addAttribute("movie", movie);
        return "movieDelete";
    }
    @PostMapping("/delete/success")
    public String deleteMovieSuccess(@ModelAttribute("movie")MovieDTO movie, Model model){

        // check if records with given ID exists
        if( movie == null){
            model.addAttribute("movie", null);
            return "movieDeleteSuccess";
        }
        // otherwise delete from DB
        movieDao.deleteById(movie.getId());
        model.addAttribute("movie", model);
        return "movieDeleteSuccess";
    }


}