package com.sparta.moviefinalproject;

import com.sparta.moviefinalproject.repositories.MovieRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MovieFinalProjectApplicationTests {
    @Test
    void contextLoads() {
    }

    @Autowired
    MovieRepository movieRepository;
    @Test
    void test(){
        movieRepository.findById(new ObjectId("573a1390f29313caabcd4135")).get().getAwards();
        System.out.println(movieRepository.findById(new ObjectId("573a1390f29313caabcd4135")).get().getAwards());
    }
}
