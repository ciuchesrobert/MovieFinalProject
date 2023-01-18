package com.sparta.moviefinalproject.controllers.rest;

import com.sparta.moviefinalproject.entities.Comment;
import com.sparta.moviefinalproject.repositories.CommentRepository;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/comments")
public class CommentController {
    private final CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping("/{id}")
    public Optional<Comment> findById(@PathVariable("id") String id) {
        return commentRepository.findById(new ObjectId(id));
    }

    @GetMapping
    public List<Comment> findAll() {
        return commentRepository.findAll();
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