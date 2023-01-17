package com.sparta.moviefinalproject.repositories;
import com.sparta.moviefinalproject.entities.Movie;
import com.sparta.moviefinalproject.entities.Theater;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends MongoRepository<Theater, ObjectId> {

}