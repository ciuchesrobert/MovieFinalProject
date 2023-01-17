package com.sparta.moviefinalproject;

import com.sparta.moviefinalproject.daos.interfaces.TheaterDao;
import com.sparta.moviefinalproject.dtos.TheaterDto;
import static org.junit.jupiter.api.Assertions.*;

import com.sparta.moviefinalproject.entities.subentities.Address;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class TheatreTests {

    @Autowired
    TheaterDao theaterDao;

    @Autowired
    Address address;

    @Test
    void contextLoads(){

    }

    @Test
    void findTheatreById(){
        Optional<TheaterDto> theater = theaterDao.findById(new ObjectId("59a47286cfa9a3a73e51e73f"));
        if(theater.isPresent()){
            TheaterDto Sherman = theater.get();
            System.out.println(Sherman);
            assertEquals("1023", Sherman.getTheaterId());
//            assertEquals({"address":
//                        {"street1": "823 N Creek Dr", "city": "Sherman", "state": "TX", "zipcode": "75092"},
//            "geo": {"type": "Point", "coordinates": [-96.608055, 33.685692]}}. Sherman.getLocation());
        } else{
            fail();
        }
    }


}
