package com.sparta.moviefinalproject.controllers.rest;

import com.sparta.moviefinalproject.entities.User;
import com.sparta.moviefinalproject.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable("id") String id) {
        return userRepository.findById(new ObjectId(id));
    }

    @GetMapping
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @PostMapping
    public User create(@RequestBody User user){

        return this.userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        this.userRepository.deleteById(new ObjectId(id));
    }

    @PutMapping("/{id}")
    public User update(@RequestBody User user, @PathVariable("id") String id) {
        Optional<User> userOptional = this.userRepository.findById(new ObjectId(id));

        if (userOptional.isPresent()) {
            User original = userOptional.get();
            if (user.getEmail() != null) {
                original.setEmail(user.getEmail());
            }
            if (user.getName() != null){
                original.setName(user.getName());
            }
            if (user.getPassword() != null){
                original.setPassword(user.getPassword());
            }
            return this.userRepository.save(original);
        }

        return new User();
    }
}