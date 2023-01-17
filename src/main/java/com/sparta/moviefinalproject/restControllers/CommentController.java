package com.sparta.moviefinalproject.restControllers;

import com.sparta.moviefinalproject.repositories.CommentRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/comments/")
public class CommentController {
    final CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
}
