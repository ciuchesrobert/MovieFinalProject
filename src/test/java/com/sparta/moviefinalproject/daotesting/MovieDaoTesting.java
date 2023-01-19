package com.sparta.moviefinalproject.daotesting;


import com.sparta.moviefinalproject.daos.implementations.MovieDAO;
import com.sparta.moviefinalproject.dtos.MovieDTO;
import com.sparta.moviefinalproject.dtos.subdtos.AwardDTO;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class MovieDaoTesting {

    @Autowired
    private MovieDAO movieDAO;

    //for create get newObjectId from https://observablehq.com/@hugodf/mongodb-objectid-generator
    @Test
    @Rollback
    @DisplayName("Save an movie into the database")
    void saveTest(){
        movieDAO.create(new MovieDTO(new ObjectId("63c930033730c15fae7aceb4"), //for create get newObjectId from https://observablehq.com/@hugodf/mongodb-objectid-generator
                new AwardDTO(1, 1, "testMovieDAO"),
                new String[]{"john", "bob"},
                "man vs bee",
                new String[]{"eng", "fr"},
                0,
                "test",
                null,null,null, null, null, null,
                null, null, null, null, null, null, null, null));
        Optional<MovieDTO> result = movieDAO.findById(new ObjectId("63c930033730c15fae7aceb4"));
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    @DisplayName("Find movie by Id from database")
    void findByIdTest() {
        System.out.println(movieDAO.findById(new ObjectId("573a1390f29313caabcd5293")).get());
        Optional<MovieDTO> movieDTO = movieDAO.findById(new ObjectId("573a1390f29313caabcd5293"));
        Assertions.assertTrue(movieDTO.isPresent());
    }

    @Test
    @DisplayName("Find all movies from database")
    void findAllTest(){
        List<MovieDTO> results = movieDAO.findAll();
        Assertions.assertTrue(results.size() >= 23000);
    }

    @Test
    @Rollback
    @DisplayName("Update movie in database")
    void updateTest(){
        MovieDTO movieDTO = new MovieDTO(new ObjectId("573a1390f29313caabcd50e5"),
                new AwardDTO(1, 1, "testMovieDAO"),
                new String[]{"john", "bob"},
                "man vs bee",
                new String[]{"eng", "fr"},
                0,
                "test",
                null,null,null, null, null, null,
                null, null, null, null, null, null, null, null);
        ObjectId id = new ObjectId("573a1390f29313caabcd50e5");
        Optional<MovieDTO> optMovie = movieDAO.findById(id);
        if(optMovie.isPresent()){
            MovieDTO movie = optMovie.get();
            movieDAO.update(id, movieDTO);
        }
        Optional<MovieDTO> optMovieAfterUpdate = movieDAO.findById(id);
        if(optMovieAfterUpdate.isPresent()){
            MovieDTO movieUpdated = optMovieAfterUpdate.get();
            Assertions.assertEquals("man vs bee", movieUpdated.getFullPlot());
        }
    }

    @Test
    @Rollback
    @DisplayName("Delete movie from database")
    void deleteTest(){
        Optional<MovieDTO> optMovie = movieDAO.findById(new ObjectId("573a1390f29313caabcd4803"));
        optMovie.ifPresent(movieDTO -> movieDAO.deleteById(movieDTO.getId()));
        Assertions.assertFalse(movieDAO.findById(new ObjectId("573a1390f29313caabcd4803")).isPresent());
    }
}
