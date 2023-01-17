//package com.sparta.moviefinalproject.daos.implementations;
//
//import com.sparta.moviefinalproject.converters.MovieConverter;
//import com.sparta.moviefinalproject.dtos.MovieDto;
//import com.sparta.moviefinalproject.entities.Movie;
//import com.sparta.moviefinalproject.repositories.MovieRepository;
//import org.bson.types.ObjectId;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MovieDao implements com.sparta.moviefinalproject.daos.interfaces.MovieDao {
//
//    private final MovieRepository movieRepo;
//
//    public MovieDao(MovieRepository movieRepo) {
//        this.movieRepo = movieRepo;
//    }
//
//    @Override
//    public void create(MovieDto movieDto) {
//        movieRepo.insert(new MovieConverter().dtoToEntity(movieDto));
//    }
//
//    @Override
//    public MovieDto findById(ObjectId id) {
//        if(movieRepo.findById(id).isPresent()) {
//            Movie movie = movieRepo.findById(id).get();
//            return new MovieConverter().entityToDto(movie);
//        }
//        return null;
//    }
//
//    @Override
//    public void update(ObjectId id, MovieDto updatedMovie) {
//        Movie movie = new MovieConverter().dtoToEntity(updatedMovie);
//        movie.setId(id);
//        movieRepo.save(movie);
//    }
//
//    @Override
//    public void deleteById(ObjectId id) {
//        if(movieRepo.findById(id).isPresent()) {
//            movieRepo.deleteById(id);
//        }
//    }
//}
