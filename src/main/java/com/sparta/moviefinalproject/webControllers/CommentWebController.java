package com.sparta.moviefinalproject.webControllers;

import com.sparta.moviefinalproject.repositories.CommentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comments")
public class CommentWebController {
    final CommentRepository commentRepository;

    public CommentWebController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
}
