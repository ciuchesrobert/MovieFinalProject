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
import org.springframework.web.server.ResponseStatusException;

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
    public Optional<UserDTO> findById(@PathVariable("id") String id, String apikey) throws JsonProcessingException {
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        if(!apikeyOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "API key is not authorized!");
        }
        return userDAO.findById(new ObjectId(id));
    }

    @GetMapping
    public List<UserDTO> findAll(String apikey) throws JsonProcessingException {
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        if(!apikeyOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "API key is not authorized!");
        }
        return userDAO.findAll();
    }

    @PostMapping
    public void create(@RequestBody UserDTO user, String apikey) throws JsonProcessingException {
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        if(!apikeyOptional.isPresent() && !"admin".equals(apikeyOptional.get().getRole())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "API key is not authorized!");
        }
        user.setId(new ObjectId());
        this.userDAO.create(user);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id, String apikey) {
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        if(!apikeyOptional.isPresent() && !"admin".equals(apikeyOptional.get().getRole())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "API key is not authorized!");
        }
        this.userDAO.deleteById(new ObjectId(id));
    }

    @PutMapping("/{id}")
    public void update(@RequestBody UserDTO user, @PathVariable("id") String id, String apikey) {
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        if(!apikeyOptional.isPresent() && !"admin".equals(apikeyOptional.get().getRole())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "API key is not authorized!");
        }
        user.setId(new ObjectId(id));
        userDAO.update(new ObjectId(id), user);
    }
}