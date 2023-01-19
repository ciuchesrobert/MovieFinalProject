package com.sparta.moviefinalproject.controllers.rest;


import com.sparta.moviefinalproject.daos.implementations.CommentDAO;
import com.sparta.moviefinalproject.dtos.CommentDTO;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sparta.moviefinalproject.entities.Apikey;
import com.sparta.moviefinalproject.entities.Comment;
import com.sparta.moviefinalproject.repositories.ApikeyRepository;
import com.sparta.moviefinalproject.repositories.CommentRepository;

import org.bson.types.ObjectId;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/comments")
public class CommentController {
    private final CommentDAO commentDAO;
    private final ApikeyRepository apikeyRepository;

    public CommentController(CommentDAO commentDAO, ApikeyRepository apikeyRepository) {
        this.commentDAO = commentDAO;
        this.apikeyRepository = apikeyRepository;
    }

    @GetMapping("/{id}")
    public Optional<CommentDTO> findById(@PathVariable("id") String id, String apikey) throws JsonProcessingException {
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        if(!apikeyOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "API key is not authorized!");
        }
        return commentDAO.findById(new ObjectId(id));
    }

    @GetMapping
    public List<CommentDTO> findAll(@RequestParam String apikey) throws JsonProcessingException {
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        if(!apikeyOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "API key is not authorized!");
        }
        return commentDAO.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CommentDTO commentDTO, String apikey) throws JsonProcessingException {
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        if(!apikeyOptional.isPresent() && !"admin".equals(apikeyOptional.get().getRole())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "API key is not authorized!");
        }
        commentDTO.setId(new ObjectId());
        this.commentDAO.create(commentDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id, String apikey){
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        if(!apikeyOptional.isPresent() && !"admin".equals(apikeyOptional.get().getRole())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "API key is not authorized!");
        }
        this.commentDAO.deleteById(new ObjectId(id));
    }

    @PutMapping("/{id}")
    public void update(@RequestBody CommentDTO commentDTO, @PathVariable("id") String id, String apikey) {
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        if(!apikeyOptional.isPresent() && !"admin".equals(apikeyOptional.get().getRole())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "API key is not authorized!");
        }
        commentDTO.setId(new ObjectId(id));
        commentDTO.setDate(LocalDateTime.now());
        this.commentDAO.update(new ObjectId(id), commentDTO);

    }

}