package com.sparta.moviefinalproject.webControllers;

import com.sparta.moviefinalproject.DTO.CommentsDTO;
import com.sparta.moviefinalproject.entities.Comment;
import com.sparta.moviefinalproject.entities.Theater;
import com.sparta.moviefinalproject.repositories.CommentRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/comments")
public class CommentWebController {

    final CommentRepository commentRepository;

    public CommentWebController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    // ------------- READ
    @GetMapping("/search")
    public String findCommentById(Model model, ObjectId id){
        Comment comment = new Comment();
        model.addAttribute("comment", comment);
        return "commentDisplay";
    }

    @PostMapping("/search/success")
    public String findCommentByIdSuccess(@ModelAttribute("comment") Comment comment, Model model){
        comment = commentRepository.findById( comment.getId() ).orElse(null);
        model.addAttribute("comment", comment);
        return "commentDisplaySuccess";
    }

    @GetMapping("/all")
    public String getAllComments(Model model){
        List<Comment> comments = commentRepository.findAll();
        model.addAttribute("comments", comments);
        return "displayAll";
    }

    // ------------------ CREATE
    @GetMapping("/create")
    public String createComment(Model model){
        Comment comment = new Comment();
        model.addAttribute("comment", comment);
        return "commentCreate";
    }

    @PostMapping("/create/success")
    public String createCommentSuccess(@ModelAttribute("comment") Comment comment){
        commentRepository.save(comment);
        return "commentCreateSuccess";
    }

    // ------------------------ UPDATE
    @GetMapping("/update{id}")
    public String updateComment(@PathVariable("id")ObjectId id, Model model){
        Comment comment = commentRepository.findById(id).orElse(null);
        model.addAttribute("comment", comment);
        return "commentUpdate";
    }

    @PostMapping("/update/success")
    public String updateCommentSuccess(@ModelAttribute("comment")Comment comment, Model model){
        commentRepository.save(comment);
        return "commentUpdateSuccess";
    }

    // ------------------------ DELETE
    @DeleteMapping("/delete/{id}")
    public String deleteComment(@PathVariable ObjectId id, Model model){
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment != null){
            commentRepository.delete(comment);
        }
        model.addAttribute("comment", comment);
        return "commentDelete";
    }

    @PostMapping("/delete/success")
    public String deleteCommentSuccess(@ModelAttribute("comment")Comment comment, Model model){
        comment = commentRepository.findById(comment.getId()).get();
        commentRepository.deleteById(comment.getId());
        model.addAttribute("comment", comment);
        return "commentDeleteSuccess";
    }
}
