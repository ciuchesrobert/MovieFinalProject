package com.sparta.moviefinalproject.daotesting;

import com.sparta.moviefinalproject.daos.implementations.TheaterDAO;
import com.sparta.moviefinalproject.dtos.TheaterDTO;
import com.sparta.moviefinalproject.entities.subentities.Address;
import com.sparta.moviefinalproject.entities.subentities.Geo;
import com.sparta.moviefinalproject.entities.subentities.Location;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TheaterDaoTesting {

    @Autowired
    private TheaterDAO theaterDAO;


    @Test
    @DisplayName("Find theater by Id from database")
    void findByIdTest() {
        System.out.println(theaterDAO.findById(new ObjectId("59a47286cfa9a3a73e51e72d")).get());
        Optional<TheaterDTO> theaterDTO = theaterDAO.findById(new ObjectId("59a47286cfa9a3a73e51e72d"));
        Assertions.assertTrue(theaterDTO.isPresent());
    }

    @Test
    @DisplayName("Find all theaters from database")
    void findAllTest(){
        List<TheaterDTO> results = theaterDAO.findAll();
        Assertions.assertTrue(results.size() >= 1500);
    }


    @Test
    @Rollback
    @DisplayName("Delete theater from database")
    void deleteTest(){
        Optional<TheaterDTO> optTheater = theaterDAO.findById(new ObjectId("59a47286cfa9a3a73e51e72d"));
        optTheater.ifPresent(movieDTO -> theaterDAO.deleteById(movieDTO.getId()));
        Assertions.assertFalse(theaterDAO.findById(new ObjectId("59a47286cfa9a3a73e51e72d")).isPresent());
    }
}
