package com.sparta.moviefinalproject;

import static org.junit.jupiter.api.Assertions.*;

import com.sparta.moviefinalproject.daos.implementations.TheaterDAO;
import com.sparta.moviefinalproject.dtos.TheaterDTO;
import com.sparta.moviefinalproject.entities.subentities.Address;
import com.sparta.moviefinalproject.entities.subentities.Geo;
import com.sparta.moviefinalproject.entities.subentities.Location;
import com.sparta.moviefinalproject.repositories.TheaterRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;


import java.util.Optional;


@SpringBootTest
public class TheatreTests {

    Address wimbledonAddress = new Address("London", "Merton", "39 The Broadway", "SW19 1QB");
    Double[] wimCoord = {51.419911, -0.204837};
    Geo wimbledonGeo = new Geo("Point", wimCoord);
    Location wimLocation = new Location(wimbledonAddress, wimbledonGeo);

    Address streathamAddress = new Address("London", "Streatham", "47-49 Streatham High Rd", "SW16 1PW");
    Double[] stretCoord = {51.419911, -0.204837};
    Geo streathamGeo = new Geo("Point", stretCoord);
    Location stretLocation = new Location(streathamAddress, streathamGeo);


    @Autowired
    TheaterDAO theaterDao;

    @Test
    void contextLoads(){
    }

    @Test
    void findTheatreById() {
        Optional<TheaterDTO> theater = theaterDao.findById(new ObjectId("59a47286cfa9a3a73e51e73f"));
        if (theater.isPresent()) {
            TheaterDTO Sherman = theater.get();
            System.out.println(Sherman);
            assertEquals("1023", Sherman.getTheaterId());
        } else {
            fail();
        }
    }

    @Test
    void createNewTheatre(){

        TheaterDTO theatre = new TheaterDTO(new ObjectId("63c9134757ca840c6332de36"),
                wimLocation,
                "2022");
        theaterDao.create(theatre);
        Optional<TheaterDTO> found = theaterDao.findById(theatre.getId());
        TheaterDTO foundTheatre = found.get();
        // check that we got back what we created
        assertEquals("2022", foundTheatre.getTheaterId());
    }

    @Test
    void updateTheatre(){
        TheaterDTO theatre = new TheaterDTO(new ObjectId("63c9134757ca840c6332de36"),
                stretLocation,
                "3017");
        theaterDao.update(new ObjectId("63c9134757ca840c6332de36"), theatre);
        assertEquals("3017", theatre.getTheaterId());
        }


    @Test
    void deleteTheatre(){

        Optional<TheaterDTO> resultBeforeDelete = theaterDao.findById(new ObjectId("63c9134757ca840c6332de36"));
        if(resultBeforeDelete.isEmpty()) fail();
        theaterDao.deleteById(new ObjectId("63c9134757ca840c6332de36"));
        Optional<TheaterDTO> resultAfterDelete = theaterDao.findById(new ObjectId("63c9134757ca840c6332de36"));
        assertFalse(resultAfterDelete.isPresent());
    }
}
