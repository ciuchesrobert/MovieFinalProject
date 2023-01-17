package com.sparta.moviefinalproject.webControllers;

import com.sparta.moviefinalproject.repositories.UserRepository;
import org.springframework.stereotype.Controller;

@Controller
public class UserWebController {

    final UserDao userDao;


    public UserWebController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
