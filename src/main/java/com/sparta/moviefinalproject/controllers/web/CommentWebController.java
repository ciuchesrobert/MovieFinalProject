package com.sparta.moviefinalproject.controllers.web;

import com.sparta.moviefinalproject.daos.implementations.CommentDAO;
import com.sparta.moviefinalproject.dtos.CommentDTO;
import com.sparta.moviefinalproject.entities.Comment;
import com.sparta.moviefinalproject.repositories.CommentRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/comments")
public class CommentWebController {

    final CommentDAO commentDAO;

    public CommentWebController(CommentRepository commentRepository, CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    // ------------- READ
    @GetMapping("/search/{id}")
    public String findCommentById(Model model, @PathVariable("id") String id){
        Optional<CommentDTO> comment = commentDAO.findById(new ObjectId(id));
        model.addAttribute("comment", comment);
        return "comment/displayComment";
    }

    @PostMapping("/search/success")
    public String findCommentByIdSuccess(@ModelAttribute("comment") Optional<CommentDTO> commentOpt, Model model){
        // if doesn't exists return null
        if( commentOpt.isEmpty() ){
            model.addAttribute("comment", null);
            return "comment/displayCommentSuccess";
        }
        // otherwise return result
        model.addAttribute("comment", commentOpt.get() );
        return "comment/displayCommentSuccess";
    }

    @GetMapping
    public String getAllComments(Model model){
        List<CommentDTO> comments = commentDAO.findAll();
        model.addAttribute("comments", comments);
        return "comment/displayAllComments";
    }

    // ------------------ CREATE
    @GetMapping("/create")
    public String createComment(Model model){
        CommentDTO comment = new CommentDTO();
        model.addAttribute("comment", comment);
        return "comment/createComment";
    }



    @PostMapping("/create/success")
    public String createCommentSuccess(@ModelAttribute("comment") CommentDTO comment, Model model){

        // Generate new ID for the comment and save to DB
        ObjectId id = new ObjectId();
        comment.setId(id);

        // check if ID is used or any fields missing
        if( commentDAO.findById( comment.getId() ).isPresent()
                || comment.getName() == ""
                || comment.getEmail() == ""
                || comment.getMovieId().toString() == ""
                || comment.getText() == ""){
            model.addAttribute("comment", null);
            return "comment/createCommentSuccess";
        }

        commentDAO.create(comment);

        // will always be not empty, as just created
        comment = commentDAO.findById( comment.getId() ).get() ;
        model.addAttribute("comment",comment);

        return "comment/createCommentSuccess";
    }

    // ------------------------ UPDATE
    @GetMapping("/update/{id}")
    public String updateComment(@PathVariable("id")String id, Model model){
        CommentDTO commentDto = commentDAO.findById(new ObjectId(id)).orElse(null);
        commentDto.setDate( LocalDateTime.now() );
        
        model.addAttribute("comment", commentDto);
        return "comment/updateComment";
    }

    @PostMapping("/update/success")
    public String updateCommentSuccess(@ModelAttribute("comment")CommentDTO comment, Model model){
        comment.setId(new ObjectId());
        commentDAO.update(comment.getId(), comment); // - needs updating
        model.addAttribute("comment", comment);
        return "comment/updateCommentSuccess";
    }

    // ----------------- DELETE

    @GetMapping("/delete/{id}")
    public String deleteComment(@PathVariable ObjectId id, Model model){
        CommentDTO comment = commentDAO.findById(id).orElse(null);
        model.addAttribute("comment", comment);
        return "comment/deleteComment";
    }

    @PostMapping("/delete/success")
    public String deleteCommentSuccess(@ModelAttribute("comment")CommentDTO comment, Model model){

        // check if records with given ID exists
        if( comment == null){
            model.addAttribute("comment", null);
            return "comment/deleteCommentSuccess";
        }
        // otherwise delete from DB
        commentDAO.deleteById(comment.getId());
        model.addAttribute("comment", comment);
        return "comment/deleteCommentSuccess";
    }

}