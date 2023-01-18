package com.sparta.moviefinalproject.controllers.rest;

import com.sparta.moviefinalproject.daos.implementations.UserDAO;
import com.sparta.moviefinalproject.dtos.UserDTO;
import com.sparta.moviefinalproject.entities.User;
import com.sparta.moviefinalproject.repositories.UserDAO;
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
        Optional<UserDTO> userOptional = this.userDAO.findById(new ObjectId(id));

        if (userOptional.isPresent()) {
            UserDTO original = userOptional.get();
            if (user.getEmail() != null) {
                original.setEmail(user.getEmail());
            }
            if (user.getName() != null){
                original.setName(user.getName());
            }
            if (user.getPassword() != null){
                original.setPassword(user.getPassword());
            }
            this.userDAO.create(original);
            return user;
        }

        return new UserDTO();
    }
}