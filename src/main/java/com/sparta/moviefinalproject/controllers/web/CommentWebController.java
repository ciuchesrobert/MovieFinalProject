package com.sparta.moviefinalproject.controllers.web;

import com.sparta.moviefinalproject.daos.implementations.CommentDAO;
import com.sparta.moviefinalproject.dtos.CommentDTO;
import com.sparta.moviefinalproject.entities.Comment;
import com.sparta.moviefinalproject.entities.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentWebController {

    final CommentDAO commentDAO;


    public CommentWebController(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    @RequestMapping("/home")
    public String commentsHome(Model model){
        return "commentsHome/commentsHome";
    }

    // ------------- READ

    @GetMapping("/basic/search")
    public String findCommentById(Model model, ObjectId id){
        CommentDTO comment = new CommentDTO();
        model.addAttribute("comment", comment);
        return "comment/displayComment";
    }

//    @GetMapping("/search/{id}")
//    public String findCommentById(Model model, @PathVariable("id") String id){
//        CommentDTO comment = commentDAO.findById( new ObjectId(id)).orElse(null);
//        System.out.println( comment );
//        model.addAttribute("comment", comment);
//        return "comment/displayComment";
//    }

    @PostMapping("/basic/search/success")
    public String findCommentByIdSuccess(@ModelAttribute("comment") CommentDTO comment, Model model){
        comment = commentDAO.findById( comment.getId() ).orElse(null);
        model.addAttribute("comment", comment);
        return "comment/displayCommentSuccess";
    }
//    @PostMapping("/search/success")
//    public String findCommentByIdSuccess(@ModelAttribute("comment") CommentDTO comment, Model model){
//
//        model.addAttribute("comment", comment);
//        return "comment/displayCommentSuccess";
//    }

    @GetMapping("/basic")
    public String getAllComments(Model model){
        List<CommentDTO> comments = commentDAO.findAll();
        model.addAttribute("comments", comments);
        return "comment/displayAllComments";
    }

    @GetMapping("/basic/search/all/{pageNum}")
    public String getAllComments(Model model, @PathVariable int pageNum){
        Page<Comment> comments = commentDAO.findAllCommentsPagination(pageNum);
        model.addAttribute("comments", comments);
        return "comment/displayAllComments";
    }

    @GetMapping("/admin/create")
    public String createComment(Model model){
        CommentDTO comment = new CommentDTO();
        model.addAttribute("comment", comment);
        return "comment/createComment";
    }

    @PostMapping("/admin/create/success")
    public String createCommentSuccess(@ModelAttribute("comment") CommentDTO comment){
        commentDAO.create(comment);
        return "commentCreateSuccess";
    }

//    @PostMapping("/create/success")
//    public String createCommentSuccess(@ModelAttribute("comment") CommentDTO comment, Model model){
//
//        // Generate new ID for the comment and save to DB
//        ObjectId id = new ObjectId();
//        comment.setId(id);
//
//        // check if ID is used or any fields missing
//        if( commentDAO.findById( comment.getId() ).isPresent()
//                || comment.getName() == ""
//                || comment.getEmail() == ""
//                || comment.getMovieId().toString() == ""
//                || comment.getText() == ""){
//            model.addAttribute("comment", null);
//            return "comment/createCommentSuccess";
//        }
//
//        commentDAO.create(comment);
//
//        // will always be not empty, as just created
//        comment = commentDAO.findById( comment.getId() ).get() ;
//        model.addAttribute("comment",comment);
//
//        return "comment/createCommentSuccess";
//    }

    @GetMapping("/admin/update{id}")
    public String updateComment(@PathVariable("id")ObjectId id, Model model){
        CommentDTO comment = commentDAO.findById(id).orElse(null);
        model.addAttribute("comment", comment);
        return "commentUpdate";
    }
//    @GetMapping("/update/{id}")
//    public String updateComment(@PathVariable("id")String id, Model model){
//        CommentDTO commentDto = commentDAO.findById(new ObjectId(id)).orElse(null);
//        commentDto.setDate( LocalDateTime.now() );
//
//        model.addAttribute("comment", commentDto);
//        return "comment/updateComment";
//    }

    @PostMapping("/admin/update/success")
    public String updateCommentSuccess(@ModelAttribute("comment")CommentDTO comment, Model model){
        commentDAO.update(comment.getId(), comment); // - needs updating
        return "commentUpdateSuccess";
    }
//    @PostMapping("/update/success")
//    public String updateCommentSuccess(@ModelAttribute("comment")CommentDTO comment, Model model){
//        comment.setId(new ObjectId());
//        commentDAO.update(comment.getId(), comment); // - needs updating
//        model.addAttribute("comment", comment);
//        return "comment/updateCommentSuccess";
//    }

    @DeleteMapping("/admin/delete/{id}")
    public String deleteComment(@PathVariable ObjectId id, Model model){
        CommentDTO comment = commentDAO.findById(id).orElse(null);
        model.addAttribute("comment", comment);
        return "commentDelete";
        //return "comment/deleteComment";
    }

    @PostMapping("/admin/delete/success")
    public String deleteCommentSuccess(@ModelAttribute("comment")CommentDTO comment, Model model){
        // check if records with given ID exists
        if( comment == null){
            model.addAttribute("comment", null);
            return "commentDeleteSuccess";
            // return comment/deleteCommentSuccess
        }
        // otherwise delete from DB
        commentDAO.deleteById(comment.getId());
        model.addAttribute("comment", comment);
        return "commentDeleteSuccess";
        // return "comment/deleteCommentSuccess
    }

}