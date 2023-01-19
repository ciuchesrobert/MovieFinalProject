package com.sparta.moviefinalproject.controllers.web;

import com.sparta.moviefinalproject.daos.implementations.CommentDAO;
import com.sparta.moviefinalproject.daos.implementations.MovieDAO;
import com.sparta.moviefinalproject.dtos.CommentDTO;
import com.sparta.moviefinalproject.dtos.MovieDTO;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieWebController {
    private final MovieDAO movieDAO;

    private final CommentDAO commentDAO;

    public MovieWebController(MovieDAO movieDAO, CommentDAO commentDAO) {
        this.movieDAO = movieDAO;
        this.commentDAO = commentDAO;
    }



    @RequestMapping("/home")
    public String moviesHome(Model model){
        return "moviesHome/moviesHome";
    }

    // ------------- READ
    @GetMapping("/basic/search/{id}")
    public String findMovieById(Model model,@PathVariable String id){
        ObjectId objectId = new ObjectId(id);
        MovieDTO movie = movieDAO.findById(objectId).orElse(null);
        List<CommentDTO> comments = null;
        if (movie != null){
        comments = commentDAO.findAllCommentsByMovieId(movie.getId());
        if (comments.isEmpty()){
            comments = null;
        }
        }
        System.out.println(movie);
        model.addAttribute("movie", movie);
        model.addAttribute("comments", comments);
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

    @GetMapping("/basic/search/all/{pageNum}")
    public String getAllUsers(Model model, @PathVariable int pageNum){
        Page<MovieDTO> movies = movieDAO.findAllMoviesPagination(pageNum);
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
        System.out.println(movie);
        movieDAO.update(movie.getId(), movie); // - needs updating
        return "movie/updateMovieSuccess";
    }

    // ------------------------ DELETE
    @GetMapping("/admin/delete/{id}")
    public String deleteMovie(@PathVariable String id, Model model){
        ObjectId objectId = new ObjectId(id);
        MovieDTO movie = movieDAO.findById(objectId).orElse(null);
        if (movie != null){
            model.addAttribute("movie", movie);
        }
        else {
        model.addAttribute("movie", null);
        }
        return "movie/deleteMovie";
    }
    @PostMapping("/admin/delete/success")
    public String deleteMovieSuccess(@ModelAttribute("id") String id, Model model){
        System.out.println("The value of id is: " + id);
        MovieDTO movieDto = movieDAO.findById(new ObjectId(id)).get();
        System.out.println(movieDto);
        movieDAO.deleteById(movieDto.getId());
        model.addAttribute("movie", movieDto);
        return "movie/deleteMovieSuccess";
    }
}