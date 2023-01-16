package com.sparta.moviefinalproject.controllers;

import com.sparta.moviefinalproject.repositories.CommentRepository;
import org.springframework.stereotype.Controller;

@Controller
public class CommentWebController {
    final CommentRepository commentRepository;

    public CommentWebController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
}
