package com.sparta.moviefinalproject.ApikeyTests;

import com.sparta.moviefinalproject.entities.Apikey;
import com.sparta.moviefinalproject.repositories.ApikeyRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
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
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey("L9nHIUhi8ArrrJnc4JJJhr3o7vut8dbuXNYNugYK4fCOxXYiKsoflEWqCuzDsMzk");
        Apikey apikey = null;
        if(apikeyOptional.isPresent()){
            apikey = apikeyOptional.get();
            System.out.println(apikey);
            Assertions.assertEquals("L9nHIUhi8ArrrJnc4JJJhr3o7vut8dbuXNYNugYK4fCOxXYiKsoflEWqCuzDsMzk", apikey.getKey());
        }else{
            System.out.println("key not found");
            Assertions.fail();
        }
    }
    @Test
    @Disabled
    void saveApikey(){
        Apikey apikey = new Apikey();
        apikey.setId(new ObjectId());
        apikey.setEmail("alfie_allen@gameofthron.es");
        apikey.setKey("O5zBeCddAXHChYdHlswyb6OrC8dTz3Zfj52cHNtECszfcsWkuQO7DJTtp6FXfbGw");
        apikey.setRole("basic");

        apikeyRepository.save(apikey);

        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey("O5zBeCddAXHChYdHlswyb6OrC8dTz3Zfj52cHNtECszfcsWkuQO7DJTtp6FXfbGw");
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
