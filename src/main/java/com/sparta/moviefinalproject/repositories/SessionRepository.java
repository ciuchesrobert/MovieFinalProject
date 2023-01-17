package com.sparta.moviefinalproject.repositories;
import com.sparta.moviefinalproject.entities.Movie;
import com.sparta.moviefinalproject.entities.Session;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SessionRepository extends MongoRepository<Session, ObjectId> {

}