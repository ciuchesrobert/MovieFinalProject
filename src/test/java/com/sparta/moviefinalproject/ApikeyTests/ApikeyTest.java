package com.sparta.moviefinalproject.ApikeyTests;

import com.sparta.moviefinalproject.entities.Apikey;
import com.sparta.moviefinalproject.repositories.ApikeyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ApikeyTest {
    @Autowired
    ApikeyRepository apikeyRepository;

    @Test
    void getAll(){
        List<Apikey> apikey = apikeyRepository.findAll();
        System.out.println(apikey);
    }

}
