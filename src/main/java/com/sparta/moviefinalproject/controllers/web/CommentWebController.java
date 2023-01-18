package com.sparta.moviefinalproject.controllers.web;

import com.sparta.moviefinalproject.daos.implementations.CommentDAO;
import com.sparta.moviefinalproject.dtos.CommentDTO;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentWebController {

    final CommentDAO commentDao;

    public CommentWebController(CommentDAO commentDao) {
        this.commentDao = commentDao;
    }


    // ------------- READ
    @GetMapping("/search")
    public String findCommentById(Model model, ObjectId id){
        CommentDTO comment = new CommentDTO();
        model.addAttribute("comment", comment);
        return "comment/displayComment";
    }

    @PostMapping("/search/success")
    public String findCommentByIdSuccess(@ModelAttribute("comment") CommentDTO comment, Model model){
        comment = commentDao.findById( comment.getId() ).orElse(null);
        model.addAttribute("comment", comment);
        return "comment/displayCommentSuccess";
    }

    @GetMapping
    public String getAllComments(Model model){
        List<CommentDTO> comments = commentDao.findAll();
        model.addAttribute("comments", comments);
        return "comment/displayAllComments";
    }

    // ------------------ CREATE
    @GetMapping("/create")
    public String createComment(Model model){
        CommentDTO comment = new CommentDTO();
        model.addAttribute("comment", comment);
        return "commentCreate";
    }

    @PostMapping("/create/success")
    public String createCommentSuccess(@ModelAttribute("comment") CommentDTO comment){
        commentDao.create(comment);
        return "commentCreateSuccess";
    }

    // ------------------------ UPDATE
    @GetMapping("/update{id}")
    public String updateComment(@PathVariable("id")ObjectId id, Model model){
        CommentDTO comment = commentDao.findById(id).orElse(null);
        model.addAttribute("comment", comment);
        return "commentUpdate";
    }

    @PostMapping("/update/success")
    public String updateCommentSuccess(@ModelAttribute("comment")CommentDTO comment, Model model){
        commentDao.update(comment.getId(), comment); // - needs updating
        return "commentUpdateSuccess";
    }

    // ------------------------ DELETE
    @DeleteMapping("/delete/{id}")
    public String deleteComment(@PathVariable ObjectId id, Model model){
        CommentDTO comment = commentDao.findById(id).orElse(null);
        model.addAttribute("comment", comment);
        return "commentDelete";
    }

    @PostMapping("/delete/success")
    public String deleteCommentSuccess(@ModelAttribute("comment")CommentDTO comment, Model model){

        // check if records with given ID exists
        if( comment == null){
            model.addAttribute("comment", null);
            return "commentDeleteSuccess";
        }
        // otherwise delete from DB
        commentDao.deleteById(comment.getId());
        model.addAttribute("comment", comment);
        return "commentDeleteSuccess";
    }

}