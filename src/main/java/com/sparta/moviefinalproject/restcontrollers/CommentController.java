package com.sparta.moviefinalproject.restControllers;

import com.sparta.moviefinalproject.entities.Comment;
import com.sparta.moviefinalproject.entities.Theater;
import com.sparta.moviefinalproject.repositories.CommentRepository;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/comments/")
public class CommentController {
    final CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

//
//    @GetMapping("/test")
//    public Optional<Comment> test() {
//        return commentRepository.findById(new ObjectId("573a1390f29313caabcd4135"));
//    }

    @GetMapping("/")
    public Optional<Comment> findById(@RequestParam String id) {
        return commentRepository.findById(new ObjectId(id));
    }

    @GetMapping("/all")
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @PostMapping("/create")
    public Comment create(@RequestBody Comment comment){

        return this.commentRepository.save(comment);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam String id){
        this.commentRepository.deleteById(new ObjectId(id));
    }

    @PutMapping("/update")
    public Comment update(@RequestBody Comment comment, @RequestParam String id) {
        Optional<Comment> commentOptional = this.commentRepository.findById(new ObjectId(id));

        if (commentOptional.isPresent()) {
            Comment original = commentOptional.get();
            if (comment.getText() != null) {
                original.setText(comment.getText());
            }
            return this.commentRepository.save(original);
        }

        return new Comment();
    }

}
