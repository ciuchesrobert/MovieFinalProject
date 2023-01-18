package com.sparta.moviefinalproject.controllers.rest;

import com.sparta.moviefinalproject.daos.implementations.UserDAO;
import com.sparta.moviefinalproject.dtos.UserDTO;
import com.sparta.moviefinalproject.entities.User;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    final UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/{id}")
    public Optional<UserDTO> findById(@PathVariable("id") String id) {
        return userDAO.findById(new ObjectId(id));
    }

    @GetMapping
    public List<UserDTO> findAll() {
        return userDAO.findAll();
    }

    @PostMapping
    public UserDTO create(@RequestBody UserDTO user){

        this.userDAO.create(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        this.userDAO.deleteById(new ObjectId(id));
    }

    @PutMapping("/{id}")
    public UserDTO update(@RequestBody UserDTO user, @PathVariable("id") String id) {
        userDAO.update(new ObjectId(id), user);
        return user;

    }
}