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

@SpringBootTest
public class TheaterDaoTesting {

    @Autowired
    private TheaterDAO theaterDAO;

    //TODO check failed test save and update
    //for create get newObjectId from https://observablehq.com/@hugodf/mongodb-objectid-generator
    @Test
    @Rollback
    @DisplayName("Save an theater into the database")
    void saveTest(){
        theaterDAO.create(new TheaterDTO(new ObjectId("63c930bc0de05c2731038730"),
                new Location(new Address("city", "state", "street", "zipcode"), new Geo("geo", new Double[12])),
                "testId"));
        Optional<TheaterDTO> result = theaterDAO.findById(new ObjectId("63c930bc0de05c2731038730"));
        Assertions.assertTrue(result.isPresent());
    }

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
    @DisplayName("Update theater in database")
    void updateTest(){
        TheaterDTO theaterDTO = new TheaterDTO(new ObjectId("59a47286cfa9a3a73e51e72e"),
                new Location(new Address("city", "state", "street", "zipcode"), new Geo("geo", new Double[12])),
                "testId");
        ObjectId id = new ObjectId("59a47286cfa9a3a73e51e72e");
        Optional<TheaterDTO> optTheater = theaterDAO.findById(id);
        if(optTheater.isPresent()){
            TheaterDTO theater = optTheater.get();
            theaterDAO.update(id, theaterDTO);
        }
        Optional<TheaterDTO> optTheaterAfterUpdate = theaterDAO.findById(id);
        if(optTheaterAfterUpdate.isPresent()){
            TheaterDTO theaterUpdated = optTheaterAfterUpdate.get();
            Assertions.assertEquals("testId", theaterUpdated.getTheaterId());
        }
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
