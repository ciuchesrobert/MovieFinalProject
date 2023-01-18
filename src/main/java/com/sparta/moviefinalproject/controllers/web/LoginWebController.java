package com.sparta.moviefinalproject.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginWebController {

    @GetMapping("/login")
    public String getLogin(){
        return "login/login";
    }
    @PostMapping("/home")
    public String postLoginSuccess(){
        return "home/home";
    }

    @GetMapping("/logout")
    public String getLogout(){
        return "logout/logout";
    }
}
