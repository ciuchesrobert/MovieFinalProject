package com.sparta.moviefinalproject;

import com.sparta.moviefinalproject.daos.implementations.MovieDAO;
import com.sparta.moviefinalproject.dtos.MovieDTO;
import com.sparta.moviefinalproject.dtos.subdtos.*;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
public class MovieTests {
    @Test
    void contextLoads() {
    }

    @Autowired
    MovieDAO movieDao;

    @Test
    void createTest() {
        AwardDTO awards = new AwardDTO(0, 0, "no wins L.");
        CriticDTO critic = new CriticDTO(2,1,1.69);
        ViewerDTO viewer = new ViewerDTO(1, 2, 1.72);
        LocalDateTime someDate = LocalDateTime.parse("2015-08-26 00:03:50.133000000", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
        ImdbDTO imdb = new ImdbDTO(1, 1.5, 10000);
        TomatoDTO tomatoes = new TomatoDTO("movie was complete garbage ngl.",
                critic,
                someDate,
                0,
                someDate,
                "omegaProduction",
                100,
                viewer,
                "crazy.com");
        MovieDTO movie = new MovieDTO(new ObjectId("507f1f77bcf86cd799439011"),
                awards,
                new String[]{"me"},
                "full plot of this movie is kinda insane",
                new String[]{"idk spanish or something"},
                5,
                "wicked plot",
                "unhinged title",
                new String[]{"i wrote this"},
                new String[]{"what is genre"},
                someDate,
                "cool poster :)",
                tomatoes,
                "2049",
                imdb,
                "M for movie",
                someDate,
                new String[]{"i played in this"},
                2000,
                new String[]{"BRITAIN"},
                "what type man");
        movieDao.create(movie);
        System.out.println(movieDao.findById(new ObjectId("507f1f77bcf86cd799439011")));
    }
}
