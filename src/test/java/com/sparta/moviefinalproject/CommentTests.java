package com.sparta.moviefinalproject;

import com.sparta.moviefinalproject.daos.implementations.CommentDAO;
import com.sparta.moviefinalproject.dtos.CommentDTO;
import com.sparta.moviefinalproject.dtos.TheaterDTO;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CommentTests {
    @Autowired
    CommentDAO commentDao;

    @Test
    void contextLoads(){
    }

    @Test
    void createNewComment(){

        CommentDTO comment = new CommentDTO(new ObjectId("432qas1iv7dw985pgkincsvu"), "Killer Bean Forever",
                "KillrBean@bestmovie.com", null, "Best Movie", now());
        commentDao.create(comment);
        Optional<CommentDTO> found = commentDao.findById(comment.getId());
        CommentDTO foundComment = found.get();
        // check that we got back what we created
        assertEquals("KillrBean@bestmovie.com", foundComment.getEmail());
    }

    @Test
    void findCOmmentByID() {
        Optional<CommentDTO> comment = commentDao.findById(new ObjectId("59a47286cfa9a3a73e51e73f"));
        if (comment.isPresent()) {
            CommentDTO Sherman = comment.get();
            System.out.println(Sherman);
            assertEquals("1023", Sherman.getId());
        } else {
            fail();
        }
    }

    @Test
    void findTheatreByEmail() {
        Optional<CommentDTO> comment = commentDao.findById(new ObjectId("59a47286cfa9a3a73e51e73f"));
        if (comment.isPresent()) {
            CommentDTO Sherman = comment.get();
            System.out.println(Sherman);
            assertEquals("KillrBean@bestmovie.com", Sherman.getEmail());
        } else {
            fail();
        }
    }

    @Test
    void deleteComment(){

        Optional<CommentDTO> resultBeforeDelete = commentDao.findById(new ObjectId("432qas1iv7dw985pgkincsvu"));
        if(resultBeforeDelete.isEmpty()) fail();
        commentDao.deleteById(new ObjectId("432qas1iv7dw985pgkincsvu"));
        Optional<CommentDTO> resultAfterDelete = commentDao.findById(new ObjectId("432qas1iv7dw985pgkincsvu"));
        assertFalse(resultAfterDelete.isPresent());
    }
}
