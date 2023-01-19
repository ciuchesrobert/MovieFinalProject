package com.sparta.moviefinalproject.daotesting;

import com.sparta.moviefinalproject.daos.implementations.CommentDAO;
import com.sparta.moviefinalproject.dtos.CommentDTO;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CommentDaoTesting {

    @Autowired
    private CommentDAO commentDAO;

    //for create get newObjectId from https://observablehq.com/@hugodf/mongodb-objectid-generator
    @Test
    @Rollback
    @DisplayName("Save an comment into the database")
    void saveTest(){
        commentDAO.create(new CommentDTO(new ObjectId("63c92431be9794c0ca55b036"), "Brad Pit",
                "brad@email.com", new ObjectId("573a1390f29313caabcd4135"),
                "Iure laboriosam quo et necessitatibus sed. Id iure delectus soluta.", LocalDateTime.now()));
        Optional<CommentDTO> result = commentDAO.findById(new ObjectId("63c92431be9794c0ca55b036"));
        Assertions.assertTrue(result.isPresent());
    }
    @Test
    @DisplayName("Find comment by Id from database")
    void findByIdTest() {
        System.out.println(commentDAO.findById(new ObjectId("5a9427648b0beebeb6957ba5")).get());
        Optional<CommentDTO> employee = commentDAO.findById(new ObjectId("5a9427648b0beebeb6957ba5"));
        Assertions.assertTrue(employee.isPresent());
    }

    @Test
    @DisplayName("Find all comments from database")
    void findAllTest(){
        List<CommentDTO> results = commentDAO.findAll();
        Assertions.assertTrue(results.size() >= 41000);
    }

    @Test
    @Rollback
    @DisplayName("Update comment in database")
    void updateTest(){
        CommentDTO commentDTO = new CommentDTO( new ObjectId("5a9427648b0beebeb69579e7"), "Mercedes Tyler",
                "bmw_tyler@fakegmail.com", new ObjectId("573a1390f29313caabcd4323"),
                "Eius veritatis vero", LocalDateTime.now());
        ObjectId id = new ObjectId("5a9427648b0beebeb6957ba5");
        Optional<CommentDTO> optComm = commentDAO.findById(id);
        if(optComm.isPresent()){
            CommentDTO comm = optComm.get();
            commentDAO.update(id, commentDTO);
        }
        Optional<CommentDTO> optCommAfterUpdate = commentDAO.findById(id);
        if(optCommAfterUpdate.isPresent()){
            CommentDTO commUpdated = optCommAfterUpdate.get();
            Assertions.assertEquals("bmw_tyler@fakegmail.com", commUpdated.getEmail());
        }
    }

    @Test
    @Rollback
    @DisplayName("Delete comment from database")
    void deleteTest(){
        Optional<CommentDTO> optCom = commentDAO.findById(new ObjectId("5a9427648b0beebeb6957ba5"));
        optCom.ifPresent(comDTO -> commentDAO.deleteById(comDTO.getId()));
        Assertions.assertFalse(commentDAO.findById(new ObjectId("5a9427648b0beebeb6957ba5")).isPresent());
    }
}
