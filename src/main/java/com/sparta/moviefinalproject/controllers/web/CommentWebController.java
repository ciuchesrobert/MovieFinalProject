package com.sparta.moviefinalproject.controllers.web;

import com.sparta.moviefinalproject.daos.implementations.CommentDAO;
import com.sparta.moviefinalproject.dtos.CommentDTO;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comments/")
public class CommentWebController {

    final CommentDAO commentDAO;

    public CommentWebController(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }


    // ------------- READ
    @GetMapping("basic/search")
    public String findCommentById(Model model, ObjectId id){
        CommentDTO comment = new CommentDTO();
        model.addAttribute("comment", comment);
        return "comment/displayComment";
    }

    @PostMapping("basic/search/success")
    public String findCommentByIdSuccess(@ModelAttribute("comment") CommentDTO comment, Model model){
        comment = commentDAO.findById( comment.getId() ).orElse(null);
        model.addAttribute("comment", comment);
        return "comment/displayCommentSuccess";
    }

    @GetMapping("basic")
    public String getAllComments(Model model){
        List<CommentDTO> comments = commentDAO.findAll();
        model.addAttribute("comments", comments);
        return "comment/displayAllComments";
    }

    @GetMapping("admin/create")
    public String createComment(Model model){
        CommentDTO comment = new CommentDTO();
        model.addAttribute("comment", comment);
        return "commentCreate";
    }

    @PostMapping("admin/create/success")
    public String createCommentSuccess(@ModelAttribute("comment") CommentDTO comment){
        commentDAO.create(comment);
        return "commentCreateSuccess";
    }

    @GetMapping("admin/update{id}")
    public String updateComment(@PathVariable("id")ObjectId id, Model model){
        CommentDTO comment = commentDAO.findById(id).orElse(null);
        model.addAttribute("comment", comment);
        return "commentUpdate";
    }

    @PostMapping("admin/update/success")
    public String updateCommentSuccess(@ModelAttribute("comment")CommentDTO comment, Model model){
        commentDAO.update(comment.getId(), comment); // - needs updating
        return "commentUpdateSuccess";
    }

    @DeleteMapping("admin/delete/{id}")
    public String deleteComment(@PathVariable ObjectId id, Model model){
        CommentDTO comment = commentDAO.findById(id).orElse(null);
        model.addAttribute("comment", comment);
        return "commentDelete";
    }

    @PostMapping("admin/delete/success")
    public String deleteCommentSuccess(@ModelAttribute("comment")CommentDTO comment, Model model){
        // check if records with given ID exists
        if( comment == null){
            model.addAttribute("comment", null);
            return "commentDeleteSuccess";
        }
        // otherwise delete from DB
        commentDAO.deleteById(comment.getId());
        model.addAttribute("comment", comment);
        return "commentDeleteSuccess";
    }

}