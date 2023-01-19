package com.sparta.moviefinalproject.repositories;

import com.sparta.moviefinalproject.entities.Movie;
import com.sparta.moviefinalproject.entities.Schedule;
import com.sparta.moviefinalproject.entities.Theater;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ScheduleRepository extends MongoRepository<Schedule ,ObjectId> {
}
