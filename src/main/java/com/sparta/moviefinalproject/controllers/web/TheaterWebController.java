package com.sparta.moviefinalproject.controllers.web;

import com.sparta.moviefinalproject.daos.implementations.TheaterDAO;
import com.sparta.moviefinalproject.dtos.TheaterDTO;
import com.sparta.moviefinalproject.entities.Theater;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/theaters")
public class TheaterWebController {

    private final TheaterDAO theaterDAO;

    public TheaterWebController(TheaterDAO theaterDAO) {
        this.theaterDAO = theaterDAO;
    }

    @RequestMapping("/home")
    public String theatersHome(Model model){
        return "theatersHome/theatersHome";
    }

    // ------------- READ
    @GetMapping("/basic/search")
    public String findTheaterById(Model model){
        TheaterDTO theater = new TheaterDTO();
        model.addAttribute("theater",theater);
        return "theaterDisplay";
    }


   @PostMapping("/basic/search/success")
    public String findTheaterByIdSuccess(@ModelAttribute("theater") TheaterDTO theater, Model model){
        theater = theaterDAO.findById( theater.getId() ).orElse(null);
        return "theaterDisplaySuccess";
    }

    @GetMapping("/basic")
    public String getAllTheaters(Model model){
        List<TheaterDTO> theaters = theaterDAO.findAll();
        model.addAttribute("theaters", theaters);
        return "theaterDisplayAll";
    }

    @GetMapping("/basic/all/{pageNum}")
    public String getAllTheaters(Model model, @PathVariable int pageNum){
        Page<Theater> theaters = theaterDAO.findAllTheatersPagination(pageNum);
        model.addAttribute("theaters", theaters);
        return "theater/displayAllTheaters";
    }

    // ------------------ CREATE
    @GetMapping("/admin/create")
    public String createTheater(Model model){
        TheaterDTO theater = new TheaterDTO();
        model.addAttribute("theater", theater);
        return "createTheater";
    }

    @PostMapping("/admin/create/success")
    public String createTheaterSuccess(@ModelAttribute("theater")TheaterDTO theater){
        theater.setId(theater.getId());
        theaterDAO.create(theater);
        return "createTheaterSuccess";
    }

    // ------------------------ UPDATE
    @GetMapping("/admin/update/{id}")
    public String updateTheater(@PathVariable("id") ObjectId id, Model model){
        TheaterDTO theater = theaterDAO.findById(id).orElse(null);
        model.addAttribute("theater", theater);
        return "theaterUpdate";
    }

    @PostMapping("/admin/update/success")
    public String updateTheaterSuccess(@ModelAttribute("theater")TheaterDTO theater, Model model){
        // check if records with given ID exists
        if( theater == null ){
            model.addAttribute("theater", null);
            return "theaterUpdateSuccess";
        }
        theaterDAO.update(theater.getId(), theater);
        model.addAttribute("theater", theater);
        return "userUpdateSuccess";
    }

    // ------------------------ DELETE
    @GetMapping("/admin/delete/{id}")
    public String deleteTheater(@PathVariable ObjectId id, Model model){
        TheaterDTO theater = theaterDAO.findById(id).orElse(null);
        model.addAttribute( "theater", theater );
        return "theaterDelete";
    }

    @PostMapping("/admin/delete/success")
    public String deleteTheaterSuccess(@ModelAttribute("theater")TheaterDTO theater, Model model){
        // check if records with given ID exists
        if( theater == null){
            model.addAttribute("theater", null);
            return "theaterDeleteSuccess";
        }
        // otherwise delete from DB
        theaterDAO.deleteById(theater.getId());
        model.addAttribute("theater", theater);
        return "theaterDeleteSuccess";
    }
}