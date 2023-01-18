package com.sparta.moviefinalproject.controllers.web;

import com.sparta.moviefinalproject.entities.Movie;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping()
    public String getHomePage(Model model){
        return "home/home";
    }

}
