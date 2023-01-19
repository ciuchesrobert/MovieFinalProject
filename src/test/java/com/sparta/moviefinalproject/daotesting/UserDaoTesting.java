package com.sparta.moviefinalproject.daotesting;

import com.sparta.moviefinalproject.daos.implementations.UserDAO;
import com.sparta.moviefinalproject.dtos.UserDTO;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserDaoTesting {

    @Autowired
    private UserDAO userDAO;


    //for create get newObjectId from https://observablehq.com/@hugodf/mongodb-objectid-generator
    @Test
    @Rollback
    @DisplayName("Save a user into the database")
    void saveTest(){
        userDAO.create(new UserDTO(new ObjectId("63c93512b1165cd12c06f3c1"),"testEmail", "testName", "testPassword"));
        Optional<UserDTO> result = userDAO.findById(new ObjectId("63c93512b1165cd12c06f3c1"));
        Assertions.assertTrue(result.isPresent());
    }
    @Test
    @DisplayName("Find user by Id from database")
    void findByIdTest() {
        System.out.println(userDAO.findById(new ObjectId("63c93473f5fa52958152991a")).get());
        Optional<UserDTO> user = userDAO.findById(new ObjectId("63c93473f5fa52958152991a"));
        Assertions.assertTrue(user.isPresent());
    }

    @Test
    @DisplayName("Find all users from database")
    void findAllTest(){
        List<UserDTO> results = userDAO.findAll();
        Assertions.assertTrue(results.size() >= 185);
    }

    @Test
    @Rollback
    @DisplayName("Update user in database")
    void updateTest(){
        UserDTO userDTO = new UserDTO(new ObjectId("63c93512b1165cd12c06f3c1"),"testEmail@email.test", "testName", "testPassword");
        Optional<UserDTO> result = userDAO.findById(new ObjectId("63c93512b1165cd12c06f3c1"));
        ObjectId id = new ObjectId("63c93512b1165cd12c06f3c1");
        Optional<UserDTO> optUser = userDAO.findById(id);
        if(optUser.isPresent()){
            UserDTO user = optUser.get();
            userDAO.update(id, userDTO);
        }
        Optional<UserDTO> optUserAfterUpdate = userDAO.findById(id);
        if(optUserAfterUpdate.isPresent()){
            UserDTO userUpdated = optUserAfterUpdate.get();
            Assertions.assertEquals("testEmail@email.test", userUpdated.getEmail());
        }
    }

    @Test
    @Rollback
    @DisplayName("Delete user from database")
    void deleteTest(){
        Optional<UserDTO> optUser = userDAO.findById(new ObjectId("63c93512b1165cd12c06f3c1"));
        optUser.ifPresent(userDTO -> userDAO.deleteById(userDTO.getId()));
        Assertions.assertFalse(userDAO.findById(new ObjectId("63c93512b1165cd12c06f3c1")).isPresent());
    }
}
