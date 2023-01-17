package com.sparta.moviefinalproject.restcontrollers;

import com.sparta.moviefinalproject.entities.Comment;
import com.sparta.moviefinalproject.repositories.CommentRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    @GetMapping
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }


    @GetMapping("/{id}")
    public Comment findById(@PathVariable("id") String id) {
        return commentRepository.findById(new ObjectId(id)).get();
    }
}
