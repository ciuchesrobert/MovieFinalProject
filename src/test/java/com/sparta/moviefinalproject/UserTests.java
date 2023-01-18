package com.sparta.moviefinalproject;

import com.sparta.moviefinalproject.daos.interfaces.UserDAO;
import com.sparta.moviefinalproject.dtos.UserDTO;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;


@SpringBootTest
public class UserTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    UserDAO userDao;

    @Test
    void createTest() {
        UserDTO user = new UserDTO(new ObjectId("507f1f77bcf86cd799439011"),
                "testEmail",
                "testName",
                "testPass");
        userDao.create(user);
        System.out.println(userDao.findById(new ObjectId("507f1f77bcf86cd799439011")));
    }

    @Test
    void readTest() {
        System.out.println(userDao.findById(new ObjectId("507f1f77bcf86cd799439011")));
    }

    @Test
    void updateTest() {
        UserDTO user = new UserDTO("testEmailUpdated",
                "testNameUpdated",
                "testPassUpdated");
        userDao.update(new ObjectId("507f1f77bcf86cd799439011"), user);
        System.out.println(userDao.findById(new ObjectId("507f1f77bcf86cd799439011")));
    }

    @Test
    void deleteTest() {
        userDao.deleteById(new ObjectId("507f1f77bcf86cd799439011"));
        System.out.println(userDao.findById(new ObjectId("507f1f77bcf86cd799439011")));
    }

    @Test
    void miscTesting() {
        String dateTimeStr = "2002-08-18T04:56:07.000Z";
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, ISO_DATE_TIME);
        System.out.println(dateTime);
    }

}
