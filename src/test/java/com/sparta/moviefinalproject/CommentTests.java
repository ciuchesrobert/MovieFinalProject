package com.sparta.moviefinalproject;

import com.sparta.moviefinalproject.daos.implementations.CommentDAO;
import com.sparta.moviefinalproject.dtos.CommentDTO;
import com.sparta.moviefinalproject.dtos.TheaterDTO;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
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

        CommentDTO comment = new CommentDTO(new ObjectId(), "Killer Bean Forever 2",
                "KillrBean@bestmovie.com", new ObjectId("573a1390f29313caabcd587d"), "Best Movie", now());
        commentDao.create(comment);
        Optional<CommentDTO> found = commentDao.findById(comment.getId());
        CommentDTO foundComment = found.get();
        // check that we got back what we created
        assertEquals("KillrBean@bestmovie.com", foundComment.getEmail());
    }

    @Test
    void findCommentByID() {
        Optional<CommentDTO> comment = commentDao.findById(new ObjectId("63c9168450909b3adf3e6017"));
        if (comment.isPresent()) {
            CommentDTO Sherman = comment.get();
            System.out.println(Sherman);
            assertEquals("Best Movie", Sherman.getText());
        } else {
            fail();
        }
    }

    @Test
    void findCommentByEmail() {
        List<CommentDTO> comments = commentDao.findAllByEmail("KillrBean@bestmovie.com");
        if (comments != null) {
            CommentDTO Sherman = comments.get(0);
            System.out.println(Sherman);
            assertEquals("KillrBean@bestmovie.com", Sherman.getEmail());
        } else {
            fail();
        }
    }

    @Test
    void deleteComment(){

        Optional<CommentDTO> resultBeforeDelete = commentDao.findById(new ObjectId("63c913dccd44932f53fcccc6"));
        if(resultBeforeDelete.isEmpty()) fail();
        commentDao.deleteById(new ObjectId("63c913dccd44932f53fcccc6"));
        Optional<CommentDTO> resultAfterDelete = commentDao.findById(new ObjectId("63c913dccd44932f53fcccc6"));
        assertFalse(resultAfterDelete.isPresent());
    }
}
