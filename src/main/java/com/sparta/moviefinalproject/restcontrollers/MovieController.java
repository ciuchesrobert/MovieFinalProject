package com.sparta.moviefinalproject.restControllers;


import com.sparta.moviefinalproject.entities.Movie;
import com.sparta.moviefinalproject.repositories.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/movies/")
public class MovieController {
    final MovieRepository movieRepository;
    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/")
    public Optional<Movie> findById(@RequestParam String id) {
        return movieRepository.findById(new ObjectId(id));
    }

//    @GetMapping("/all")
//    public List<Movie> findAll() {
//        return movieRepository.findAll();
//    }

    @PostMapping("/create")
    public Movie create(@RequestBody Movie movie){

        return this.movieRepository.save(movie);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam String id){
        this.movieRepository.deleteById(new ObjectId(id));
    }

    @PutMapping("/update")
    public Movie update(@RequestBody Movie movie, @RequestParam String id) {
        Optional<Movie> movieOptional = this.movieRepository.findById(new ObjectId(id));

        if (movieOptional.isPresent()) {
            Movie original = movieOptional.get();
            if (movie.getYear() != null) {
                original.setYear(movie.getYear());
            }
            if(movie.getRated() != null){
                original.setRated(movie.getRated());
            }
            if(movie.getTitle() != null){
                original.setTitle(movie.getTitle());
            }
            if(movie.getCast() != null){
                original.setCast(movie.getCast());
            }
            return this.movieRepository.save(original);
        }
        return new Movie();
    }

}
