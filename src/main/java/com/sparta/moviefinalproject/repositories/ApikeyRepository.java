package com.sparta.moviefinalproject.repositories;

import com.sparta.moviefinalproject.entities.Apikey;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApikeyRepository extends MongoRepository<Apikey, ObjectId> {
}
