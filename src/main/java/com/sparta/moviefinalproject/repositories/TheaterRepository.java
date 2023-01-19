package com.sparta.moviefinalproject.repositories;
import com.sparta.moviefinalproject.entities.Movie;
import com.sparta.moviefinalproject.entities.Theater;
import com.sparta.moviefinalproject.entities.subentities.Address;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepository extends MongoRepository<Theater, ObjectId> {
    List<Theater> findAllTheatersByLocation_Address_ZipcodeContainingIgnoreCase(String name);
    List<Theater> findAllTheatersByLocation_AddressContaining(Address name);
    List<Theater> findAllTheatersByLocation_Address_CityContainingIgnoreCase(String name);
    List<Theater> findAllTheatersByLocation_Address_StateContainingIgnoreCase(String name);
    List<Theater> findAllTheatersByLocation_Address_Street1ContainingIgnoreCase(String name);

}