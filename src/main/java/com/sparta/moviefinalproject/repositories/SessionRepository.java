package com.sparta.moviefinalproject.repositories;
import com.sparta.moviefinalproject.entities.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SessionRepository extends MongoRepository<Movie, ObjectId> {

}