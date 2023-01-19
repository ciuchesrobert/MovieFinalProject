package com.sparta.moviefinalproject;

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
    private ScheduleRepository scheduleRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TheaterRepository theaterRepository;

    @Test
    void test() {
        Schedule schedule = scheduleRepository.findById(new ObjectId("63c91764fe6b781af879a9db")).get();
        List<ObjectId> movieIds = schedule.getMovieId();
        movieIds.add(new ObjectId("573a1390f29313caabcd548c"));

        LocalDateTime someDate = LocalDateTime.parse("2015-08-26 00:03:50.133000000", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
        scheduleRepository.save(new Schedule(new ObjectId(), movieIds, new ObjectId("59a47286cfa9a3a73e51e72c"), someDate));

        //  get movies that are scheduled
        List<String> movies = new ArrayList<>();
        for(ObjectId id : movieIds) {
            System.out.println(id);
            movies.add(movieRepository.findById(id).get().getTitle());
        }
        System.out.println(movies);
    }
}
