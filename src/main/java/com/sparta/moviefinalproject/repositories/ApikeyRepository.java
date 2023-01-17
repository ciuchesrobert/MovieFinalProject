package com.sparta.moviefinalproject.repositories;

import com.sparta.moviefinalproject.entities.Apikey;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApikeyRepository extends MongoRepository<Apikey, ObjectId> {
    Optional<Apikey> findByKey(String key);
}
