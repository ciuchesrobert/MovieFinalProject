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
    public ResponseEntity<String> findById(@PathVariable("id") String id, String apikey) throws JsonProcessingException {
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        Apikey key = null;
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        ResponseEntity<String> responseEntity = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        String commentString = null;
        if(apikeyOptional.isPresent()){
            key = apikeyOptional.get();
            CommentDTO comment = commentDAO.findById(new ObjectId(id)).get();
            commentString = mapper.writeValueAsString(comment);
            System.out.println(key);
            responseEntity = new ResponseEntity<>(commentString, httpHeaders, HttpStatus.OK);
        }else {
            responseEntity = new ResponseEntity<>("{\"message\":\"API key " + apikey + " not valid\"}", httpHeaders, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @GetMapping("/")
    public ResponseEntity<String> findAll(@RequestParam String apikey) throws JsonProcessingException {
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        Apikey key = null;
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        ResponseEntity<String> responseEntity = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        String commentString = null;
        if(apikeyOptional.isPresent()){
            key = apikeyOptional.get();
            List<CommentDTO> comments = commentDAO.findAll();
            commentString = mapper.writeValueAsString(comments);
            System.out.println(key);
            responseEntity = new ResponseEntity<>(commentString, httpHeaders, HttpStatus.OK);
        }else {
            responseEntity = new ResponseEntity<>("{\"message\":\"API key " + apikey + " not valid\"}", httpHeaders, HttpStatus.UNAUTHORIZED);
        }
        return responseEntity;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> create(@RequestBody CommentDTO commentDTO, String apikey) throws JsonProcessingException {
        Optional<Apikey> apikeyOptional = apikeyRepository.findByKey(apikey);
        Apikey key = null;
        ResponseEntity<String> responseEntity = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if(apikeyOptional.isPresent()){
            key = apikeyOptional.get();
            if("admin".equals(key.getRole())){
                commentDTO.setId(new ObjectId());
                this.commentDAO.create(commentDTO);
                responseEntity = new ResponseEntity<>("{\"message\":\"Comment with ID " + commentDTO.getId() + " has been created successfully!\"}", httpHeaders, HttpStatus.CREATED);
            }else{
                responseEntity =  new ResponseEntity<>("{\"message\":\"You do not have permission to add new comment!\"}", httpHeaders, HttpStatus.UNAUTHORIZED);
            }
        }else {
            responseEntity = new ResponseEntity<>("{\"message\":\"API key " + apikey + " not valid\"}", httpHeaders, HttpStatus.UNAUTHORIZED);
        }
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id){
        this.commentDAO.deleteById(new ObjectId(id));
    }

    @PutMapping("/{id}")
    public void update(@RequestBody CommentDTO commentDTO, @PathVariable("id") String id) {
        this.commentDAO.update(new ObjectId(id), commentDTO);

    }

}