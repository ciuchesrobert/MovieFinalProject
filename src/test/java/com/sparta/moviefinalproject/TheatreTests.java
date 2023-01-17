package com.sparta.moviefinalproject;

import com.sparta.moviefinalproject.repositories.TheaterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TheatreTests {

    @Autowired
    private TheaterRepository theatreRepo;

    @Test
    void contextLoads(){
    }



}
