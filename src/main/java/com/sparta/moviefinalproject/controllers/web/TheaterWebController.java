package com.sparta.moviefinalproject.controllers.web;

import com.sparta.moviefinalproject.daos.implementations.TheaterDAO;
import com.sparta.moviefinalproject.dtos.TheaterDTO;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/theaters")
public class TheaterWebController {

    private final TheaterDAO theaterDao;

    public TheaterWebController(TheaterDAO theaterDao) {
        this.theaterDao = theaterDao;
    }

    // ------------- READ
    @GetMapping("/search")
    public String findTheaterById(Model model){
        TheaterDTO theater = new TheaterDTO();
        model.addAttribute("theater",theater);
        return "theaterDisplay";
    }

    @PostMapping("/search/success")
    public String findTheaterByIdSuccess(@ModelAttribute("theater") TheaterDTO theater, Model model){
        theater = theaterDao.findById( theater.getId() ).orElse(null);
        model.addAttribute("theater",theater);
        return "theaterDisplaySuccess";
    }

    @GetMapping
    public String getAllTheaters(Model model){
        List<TheaterDTO> users = theaterDao.findAll();
        model.addAttribute("users", users);
        return "theaterDisplayAll";
    }

    // ------------------ CREATE
    @GetMapping("/create")
    public String createTheater(Model model){
        TheaterDTO theater = new TheaterDTO();
        model.addAttribute("theater", theater);
        return "createTheater";
    }

    @PostMapping("/create/success")
    public String createTheaterSuccess(@ModelAttribute("theater")TheaterDTO theater){
        theater.setId(theater.getId());
        theaterDao.create(theater);
        return "createTheaterSuccess";
    }

    // ------------------------ UPDATE
    @GetMapping("/update/{id}")
    public String updateTheater(@PathVariable("id") ObjectId id, Model model){
        TheaterDTO theater = theaterDao.findById(id).orElse(null);
        model.addAttribute("theater", theater);
        return "theaterUpdate";

    }

    @PostMapping("/update/success")
    public String updateTheaterSuccess(@ModelAttribute("theater")TheaterDTO theater, Model model){

        // check if records with given ID exists
        if( theater == null ){
            model.addAttribute("theater", null);
            return "theaterUpdateSuccess";
        }
        theaterDao.update(theater.getId(), theater);
        model.addAttribute("theater", theater);
        return "userUpdateSuccess";
    }


    // ------------------------ DELETE
    @GetMapping("/delete/{id}")
    public String deleteTheater(@PathVariable ObjectId id, Model model){
        TheaterDTO theater = theaterDao.findById(id).orElse(null);
        model.addAttribute( "theater", theater );
        return "theaterDelete";
    }

    @PostMapping("/delete/success")
    public String deleteTheaterSuccess(@ModelAttribute("theater")TheaterDTO theater, Model model){

        // check if records with given ID exists
        if( theater == null){
            model.addAttribute("theater", null);
            return "theaterDeleteSuccess";
        }
        // otherwise delete from DB
        theaterDao.deleteById(theater.getId());
        model.addAttribute("theater", theater);
        return "theaterDeleteSuccess";
    }
}