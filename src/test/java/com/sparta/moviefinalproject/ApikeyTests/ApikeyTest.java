package com.sparta.moviefinalproject.ApikeyTests;

import com.sparta.moviefinalproject.entities.Apikey;
import com.sparta.moviefinalproject.repositories.ApikeyRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ApikeyTest {
    @Autowired
    ApikeyRepository apikeyRepository;

    @Test
    void getAll(){
        List<Apikey> apikey = apikeyRepository.findAll();
        System.out.println(apikey);
    }
    @Test
    void findByApikey(){
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey("ndlkjdlksajkdsajkaksdlksaj2132");
        Apikey apikey = null;
        if(apikeyOptional.isPresent()){
            apikey = apikeyOptional.get();
            System.out.println(apikey);
            Assertions.assertEquals("ndlkjdlksajkdsajkaksdlksaj2132", apikey.getKey());
        }else{
            System.out.println("key not found");
            Assertions.fail();
        }
    }
    @Test
    void saveApikey(){
        Apikey apikey = new Apikey();
        apikey.setId(new ObjectId());
        apikey.setEmail("hb123@hotmail.com");
        apikey.setKey("laieemdlksadsadasjflksalll4");
        apikey.setRole("admin");

        apikeyRepository.save(apikey);

        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey("laieemdlksadsadasjflksalll4");
        Apikey apikeySave = null;
        if(apikeyOptional.isPresent()){
            apikeySave = apikeyOptional.get();
            System.out.println(apikeySave);
        }else{
            System.out.println("key not found");
        }
        Assertions.assertEquals(apikey, apikeySave);
    }
    @Test
    void createId(){
        ObjectId id = new ObjectId();
        System.out.println(id);

//// or this
//        ObjectId id = ObjectId.get();

    }
}
