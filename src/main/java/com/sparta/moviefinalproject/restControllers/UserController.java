package com.sparta.moviefinalproject.restControllers;

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

//    @GetMapping("/test")
//    public Optional<User> test() {
//        return userRepository.findById(new ObjectId("573a1390f29313caabcd4135"));
//    }

    @GetMapping("/")
    public Optional<User> findById(@RequestParam String id) {
        return userRepository.findById(new ObjectId(id));
    }

    @GetMapping("/all")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @PostMapping("/create")
    public User create(@RequestBody User user){

        return this.userRepository.save(user);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam String id){
        this.userRepository.deleteById(new ObjectId(id));
    }

    @PutMapping("/update")
    public User update(@RequestBody User user, @RequestParam String id) {
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
