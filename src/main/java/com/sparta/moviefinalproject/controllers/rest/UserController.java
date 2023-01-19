package com.sparta.moviefinalproject.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sparta.moviefinalproject.daos.implementations.UserDAO;
import com.sparta.moviefinalproject.dtos.CommentDTO;
import com.sparta.moviefinalproject.dtos.UserDTO;
import com.sparta.moviefinalproject.entities.Apikey;
import com.sparta.moviefinalproject.entities.User;
import com.sparta.moviefinalproject.repositories.ApikeyRepository;
import org.bson.types.ObjectId;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    final UserDAO userDAO;

    private final ApikeyRepository apikeyRepository;

    public UserController(UserDAO userDAO, ApikeyRepository apikeyRepository) {
        this.userDAO = userDAO;
        this.apikeyRepository = apikeyRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> findById(@PathVariable("id") String id, String apikey) throws JsonProcessingException {
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        Apikey key = null;
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        ResponseEntity<String> responseEntity = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        String userString = null;
        if(apikeyOptional.isPresent()){
            key = apikeyOptional.get();
            UserDTO user = userDAO.findById(new ObjectId(id)).get();
            userString = mapper.writeValueAsString(user);
            System.out.println(key);
            responseEntity = new ResponseEntity<>(userString, httpHeaders, HttpStatus.OK);
        }else {
            responseEntity = new ResponseEntity<>("{\"message\":\"API key " + apikey + " not valid\"}", httpHeaders, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<String> findAll(String apikey) throws JsonProcessingException {
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        Apikey key = null;
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        ResponseEntity<String> responseEntity = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        String usersString = null;
        if(apikeyOptional.isPresent()){
            key = apikeyOptional.get();
            List<UserDTO> users = userDAO.findAll();
            usersString = mapper.writeValueAsString(users);
            System.out.println(key);
            responseEntity = new ResponseEntity<>(usersString, httpHeaders, HttpStatus.OK);
        }else {
            responseEntity = new ResponseEntity<>("{\"message\":\"API key " + apikey + " not valid\"}", httpHeaders, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@RequestBody UserDTO user){
        this.userDAO.create(user);
        return user;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        this.userDAO.deleteById(new ObjectId(id));
    }

    @PutMapping("/{id}")
    public UserDTO update(@RequestBody UserDTO user, @PathVariable("id") String id) {
        userDAO.update(new ObjectId(id), user);
        return user;

    }
}