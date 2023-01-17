package com.sparta.moviefinalproject.restControllers;

import com.sparta.moviefinalproject.repositories.UserRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}