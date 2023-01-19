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
            responseEntity = new ResponseEntity<>("{\"message\":\"API key " + apikey + " not valid\"}", httpHeaders, HttpStatus.UNAUTHORIZED);
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
            responseEntity = new ResponseEntity<>("{\"message\":\"API key " + apikey + " not valid\"}", httpHeaders, HttpStatus.UNAUTHORIZED);
        }
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody UserDTO user, String apikey) throws JsonProcessingException {
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        Apikey key = null;
        ResponseEntity<String> responseEntity = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if(apikeyOptional.isPresent()){
            key = apikeyOptional.get();
            if ("admin".equals(key.getRole())) {
                user.setId(new ObjectId());
                this.userDAO.create(user);
                responseEntity = new ResponseEntity<>("{\"message\":\"User with ID " + user.getId() + " has been created successfully!\"}", httpHeaders, HttpStatus.CREATED);
            } else {
                responseEntity =  new ResponseEntity<>("{\"message\":\"You do not have permission to add new user!\"}", httpHeaders, HttpStatus.UNAUTHORIZED);
            }
        }else {
            responseEntity = new ResponseEntity<>("{\"message\":\"API key " + apikey + " not valid\"}", httpHeaders, HttpStatus.UNAUTHORIZED);
        }
        return responseEntity;
    }
    @PutMapping("/create")
    public String testPost(){
        return "hello";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id, String apikey) {
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        Apikey key = null;
        ResponseEntity<String> responseEntity = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if(!userDAO.findById(new ObjectId(id)).isPresent()) {
            responseEntity = new ResponseEntity<>("{\"message\":\"User with ID " + id + " not found!\"}", httpHeaders, HttpStatus.NOT_FOUND);
        } else {
            if(apikeyOptional.isPresent()){
                key = apikeyOptional.get();
                if ("admin".equals(key.getRole())) {
                    this.userDAO.deleteById(new ObjectId(id));
                    responseEntity = new ResponseEntity<>("{\"message\":\"User with ID " + id + " has been successfully deleted!\"}", httpHeaders, HttpStatus.OK);
                } else {
                    responseEntity =  new ResponseEntity<>("{\"message\":\"You do not have permission to delete a user!\"}", httpHeaders, HttpStatus.UNAUTHORIZED);
                }
            }else {
                responseEntity = new ResponseEntity<>("{\"message\":\"API key " + apikey + " not valid\"}", httpHeaders, HttpStatus.UNAUTHORIZED);
            }
        }
        return responseEntity;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody UserDTO user, @PathVariable("id") String id, String apikey) {
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        Apikey key = null;
        ResponseEntity<String> responseEntity = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if(!userDAO.findById(new ObjectId(id)).isPresent()) {
            responseEntity = new ResponseEntity<>("{\"message\":\"User with ID " + id + " not found!\"}", httpHeaders, HttpStatus.NOT_FOUND);
        } else {
            if(apikeyOptional.isPresent()){
                key = apikeyOptional.get();
                if ("admin".equals(key.getRole())) {
                    user.setId(new ObjectId(id));
                    userDAO.update(new ObjectId(id), user);
                    responseEntity = new ResponseEntity<>("{\"message\":\"User with ID " + id + " has been successfully updated!\"}", httpHeaders, HttpStatus.OK);
                } else {
                    responseEntity =  new ResponseEntity<>("{\"message\":\"You do not have permission to update a user!\"}", httpHeaders, HttpStatus.UNAUTHORIZED);
                }
            }else {
                responseEntity = new ResponseEntity<>("{\"message\":\"API key " + apikey + " not valid\"}", httpHeaders, HttpStatus.UNAUTHORIZED);
            }
        }
        return responseEntity;
    }
}