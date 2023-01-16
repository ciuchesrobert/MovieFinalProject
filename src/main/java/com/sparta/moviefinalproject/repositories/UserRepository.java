package com.sparta.moviefinalproject.repositories;

import com.sparta.moviefinalproject.entities.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
}
