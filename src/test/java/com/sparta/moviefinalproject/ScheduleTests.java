package com.sparta.moviefinalproject;

import com.sparta.moviefinalproject.daos.implementations.ScheduleDAO;
import com.sparta.moviefinalproject.entities.Movie;
import com.sparta.moviefinalproject.entities.Schedule;
import com.sparta.moviefinalproject.entities.Theater;
import com.sparta.moviefinalproject.repositories.MovieRepository;
import com.sparta.moviefinalproject.repositories.ScheduleRepository;
import com.sparta.moviefinalproject.repositories.TheaterRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ScheduleTests {
    @Autowired
    private ScheduleDAO scheduleDAO;

    @Test
    void test() {
        System.out.println(scheduleDAO.findAllMoviesFromSchedule(new ObjectId("63c93173668089148f4cdcb3")));
        System.out.println(scheduleDAO.findTheaterFromSchedule(new ObjectId("63c93173668089148f4cdcb3")));
    }
}
