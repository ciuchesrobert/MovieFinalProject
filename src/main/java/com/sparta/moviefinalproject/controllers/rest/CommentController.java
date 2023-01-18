package com.sparta.moviefinalproject.controllers.rest;

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
    private final CommentRepository commentRepository;
    private final ApikeyRepository apikeyRepository;

    public CommentController(CommentRepository commentRepository, ApikeyRepository apikeyRepository) {
        this.commentRepository = commentRepository;
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
            Comment comment = commentRepository.findById(new ObjectId(id)).get();
            commentString = mapper.writeValueAsString(comment);
            System.out.println(key);
            responseEntity = new ResponseEntity<>(commentString, httpHeaders, HttpStatus.OK);
        }else {
            responseEntity = new ResponseEntity<>("{\"message\":\"API key " + apikey + " not valid\"}", httpHeaders, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @GetMapping
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
            List<Comment> comments = commentRepository.findAll();
            commentString = mapper.writeValueAsString(comments);
            System.out.println(key);
            responseEntity = new ResponseEntity<>(commentString, httpHeaders, HttpStatus.OK);
        }else {
            responseEntity = new ResponseEntity<>("{\"message\":\"API key " + apikey + " not valid\"}", httpHeaders, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment create(@RequestBody Comment comment){

        return this.commentRepository.save(comment);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id){
        this.commentRepository.deleteById(new ObjectId(id));
    }

    @PutMapping("/{id}")
    public Comment update(@RequestBody Comment comment, @PathVariable("id") String id) {
        Optional<Comment> commentOptional = this.commentRepository.findById(new ObjectId(id));

        if (commentOptional.isPresent()) {
            Comment original = commentOptional.get();
            if (comment.getText() != null) {
                original.setText(comment.getText());
            }
            return this.commentRepository.save(original);
        }

        return new Comment();
    }

}