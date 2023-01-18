package com.sparta.moviefinalproject.controllers.rest;

import com.sparta.moviefinalproject.daos.implementations.CommentDAO;
import com.sparta.moviefinalproject.dtos.CommentDTO;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/comments")
public class CommentController {
    private final CommentDAO commentDAO;

    public CommentController(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    @GetMapping("/{id}")
    public Optional<CommentDTO> findById(@PathVariable("id") String id) {
        return commentDAO.findById(new ObjectId(id));
    }

    @GetMapping
    public List<CommentDTO> findAll() {
        return commentDAO.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CommentDTO commentDTO){

        this.commentDAO.create(commentDTO);
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