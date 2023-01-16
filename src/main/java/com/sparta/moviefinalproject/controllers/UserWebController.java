package com.sparta.moviefinalproject.controllers;

import com.sparta.moviefinalproject.repositories.UserRepository;
import org.springframework.stereotype.Controller;

@Controller
public class UserWebController {
    UserRepository userRepository;

    public UserWebController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
