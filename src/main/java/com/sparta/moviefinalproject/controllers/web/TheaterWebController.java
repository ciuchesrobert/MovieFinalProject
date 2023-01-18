package com.sparta.moviefinalproject.controllers.web;

import com.sparta.moviefinalproject.entities.Theater;
import com.sparta.moviefinalproject.repositories.TheaterRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/theaters")
public class TheaterWebController {
    final TheaterRepository theaterRepository;

    public TheaterWebController(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    // ------------- READ
    @GetMapping("/search")
    public String findTheaterById(Model model){
        Theater theater = new Theater();
        model.addAttribute("theater",theater);
        return "theaterDisplay";
    }

    @PostMapping("/search/success")
    public String findTheaterByIdSuccess(@ModelAttribute("theater") Theater theater, Model model){
        theater = theaterRepository.findById( theater.getId() ).orElse(null);
        model.addAttribute("theater",theater);
        return "theaterDisplaySuccess";
    }

    @GetMapping
    public String getAllTheaters(Model model){
        List<Theater> theaters = theaterRepository.findAll();
        model.addAttribute("theaters", theaters);
        return "theater/displayAllTheaters";
    }

    // ------------------ CREATE
    @GetMapping("/create")
    public String createTheater(Model model){
        Theater theater = new Theater();
        model.addAttribute("theater", theater);
        return "createTheater";
    }

    @PostMapping("/create/success")
    public String createTheaterSuccess(@ModelAttribute("theater")Theater theater){
        theater.setId(theater.getId());
        theaterRepository.save(theater);
        return "createTheaterSuccess";
    }

    // ------------------------ UPDATE
    @GetMapping("/update/{id}")
    public String updateTheater(@PathVariable("id") ObjectId id, Model model){
        Theater theater = theaterRepository.findById(id).orElse(null);
        model.addAttribute("theater", theater);
        return "theaterUpdate";

    }

    @PostMapping("/update/success")
    public String updateTheaterSuccess(@ModelAttribute("theater")Theater theater, Model model){

        // check if records with given ID exists
        if( theater == null ){
            model.addAttribute("theater", null);
            return "theaterUpdateSuccess";
        }
        theaterRepository.save(theater);    // - needs updating
        model.addAttribute("theater", theater);
        return "userUpdateSuccess";
    }


    // ------------------------ DELETE
    @GetMapping("/delete/{id}")
    public String deleteTheater(@PathVariable ObjectId id, Model model){
        Theater theater = theaterRepository.findById(id).orElse(null);
        if(theater != null){
            theaterRepository.deleteById(id);
        }
        model.addAttribute( "theater", theater );
        return "theaterDelete";
    }

    @PostMapping("/delete/success")
    public String deleteTheaterSuccess(@ModelAttribute("theater")Theater theater, Model model){
        theater = theaterRepository.findById( theater.getId() ).get();
        theaterRepository.deleteById(theater.getId());
        model.addAttribute("theater", theater);
        return "theaterDeleteSuccess";
    }




}