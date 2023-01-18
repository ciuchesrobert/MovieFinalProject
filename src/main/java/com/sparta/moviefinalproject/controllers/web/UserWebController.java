package com.sparta.moviefinalproject.controllers.web;
import com.sparta.moviefinalproject.daos.implementations.UserDao;
import com.sparta.moviefinalproject.dtos.UserDto;
import com.sparta.moviefinalproject.entities.User;
import com.sparta.moviefinalproject.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserWebController {
    private final UserDao userDao;
    private final UserRepository userRepository;

    public UserWebController(UserDao userDao,
                             UserRepository userRepository) {
        this.userDao = userDao;
        this.userRepository = userRepository;
    }


    // ---------------- READ
//    @GetMapping("/search")
//    public String findUserById(Model model, ObjectId id){
//        UserDto user = new UserDto();
//        model.addAttribute("user",user);
//        return "userDisplay";
//    }
//
//    @PostMapping("/search/success")
//    public String findUserByIdSuccess(@ModelAttribute("user") UserDto user, Model model){
//        user = userDao.findById( user.getId() ).orElse(null);
//        model.addAttribute("user",user);
//        return "user/displayUser";
//    }
    @GetMapping("/{id}")
    public String displayUser(@PathVariable ObjectId id, Model model) {
        Optional<UserDto> userOptional = userDao.findById(id);
        UserDto user = null;
        if(userOptional.isPresent()) {
            user =userOptional.get();
        }
        model.addAttribute("user", user);
        return "user/displayUser";
    }

    @GetMapping
    public String getAllUsers(Model model){
        Page<User> users = userDao.findAllUsers();
        model.addAttribute("users", users);
        return "user/displayAllUsers";
    }

    // ------------------ CREATE
    @GetMapping("/create")
    public String createUser(Model model){
        UserDto user = new UserDto();
        user.setId(new ObjectId());
        model.addAttribute("user", user);
        return "user/createUser";
    }

    @PostMapping("/createUserSuccess")
    public String creatUserSuccess(@ModelAttribute("user")UserDto user){
//        user.setId(new ObjectId());
        userDao.create(user);
        return "user/createUserSuccess";
    }


    // ------------------------ UPDATE
//    @GetMapping("/update/{id}")
//    public String updateUser(@PathVariable("id") ObjectId id, Model model){
//        UserDto user = userDao.findById(id).orElse(null);
//        model.addAttribute("user", user);
//        return "user/updateUser";
//
//    }
    @GetMapping("/update/{id}")
    public String updateUser(Model model, @PathVariable ObjectId id) {
        Optional<UserDto> userDtoOptional = userDao.findById(id);
        UserDto userDto = null;
        if (userDtoOptional.isPresent()) {
            userDto = userDtoOptional.get();
//            userDao.update(id, userDto);
            model.addAttribute("user", userDto);
            model.addAttribute("id", id);
        }
//        if (userDto != null)

        return "user/updateUser";
    }

    @PostMapping("/updateSuccess")
    public String updateSuccess(@ModelAttribute("user") UserDto user,@ModelAttribute("id") ObjectId id, Model model) {
        userDao.update(id, user);
//        userDao.upda(user);
        return "user/updateUserSuccess";
    }

//    @PostMapping("/update/success")
//    public String updateUserSuccess(@ModelAttribute("user")UserDto user, Model model){
//
//        // check if records with given ID exists
//        if( user == null ){
//            model.addAttribute("user", null);
//            return "userUpdateSuccess";
//        }
//
//        userDao.update(user.getId(), user);
//        model.addAttribute("user", user);
//        return "userUpdateSuccess";
//    }


    // ------------------------ DELETE
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable ObjectId id, Model model){
        UserDto userDto = userDao.findById(id).orElse(null);
        if(userDto != null){
            userDao.deleteById(id);
        }
        model.addAttribute( "user", userDto );
        return "user/deleteUser";
    }

    @PostMapping("/deleteSuccess")
    public String deleteUserSuccess(@ModelAttribute("user")UserDto user, Model model){
//        user = userDao.findById( user.getId() ).get();
//        userDao.deleteById(user.getId());
//        model.addAttribute("user", user);
        return "userDeleteSuccess";
    }

}