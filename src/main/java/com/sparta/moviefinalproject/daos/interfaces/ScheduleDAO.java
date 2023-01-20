package com.sparta.moviefinalproject.daos.interfaces;

import com.sparta.moviefinalproject.dtos.MovieDTO;
import com.sparta.moviefinalproject.dtos.ScheduleDTO;
import com.sparta.moviefinalproject.dtos.TheaterDTO;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleDAO extends DAO<ScheduleDTO> {
    List<MovieDTO> findAllMoviesFromSchedule(ObjectId scheduleId);

    TheaterDTO findTheaterFromSchedule(ObjectId scheduleId);

    List<LocalDateTime> findDateTimesFromSchedule(ObjectId scheduleId);
}
