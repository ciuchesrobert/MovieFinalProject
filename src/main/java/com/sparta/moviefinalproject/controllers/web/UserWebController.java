package com.sparta.moviefinalproject.controllers.web;
import com.sparta.moviefinalproject.daos.implementations.UserDAO;
import com.sparta.moviefinalproject.dtos.UserDTO;
import com.sparta.moviefinalproject.entities.User;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserWebController {
    private final UserDAO userDao;
    public UserWebController(UserDAO userDao) {
        this.userDao = userDao;
    }


    // ---------------- READ
//    @GetMapping("/basic/search")
//    public String findUserById(Model model, ObjectId id){
//        UserDTO user = new UserDTO();
//        model.addAttribute("user",user);
//
//        return "user/displayUser";
//    }
    @GetMapping("/basic/search/{id}")
    public String getUserById(@PathVariable ObjectId id, Model model){
        Optional<UserDTO> userOptional = userDao.findById(id);
        UserDTO user = null;
        if(userOptional.isPresent()){
            user = userOptional.get();
        }
        model.addAttribute("user",user);
        model.addAttribute("id",id);
        return "user/displayUser";
    }

//    @PostMapping("/basic/search/success")
//    public String findUserByIdSuccess(@ModelAttribute("user") UserDTO user, Model model){
//        user = userDao.findById( user.getId() ).orElse(null);
//        model.addAttribute("user",user);
//        return "userDisplaySuccess";
//    }

    @GetMapping("/basic")
    public String getAllUsers(Model model){
        Page<User> users = userDao.findAllUsers();
        model.addAttribute("users", users);
        return "user/displayAllUsers";
    }

    // ------------------ CREATE
    @GetMapping("/admin/create")
    public String createUser(Model model){
        UserDTO user = new UserDTO();
        user.setId(new ObjectId());
        model.addAttribute("user", user);
        return "user/createUser";
    }

    @PostMapping("/admin/createSuccess")
    public String creatUserSuccess(@ModelAttribute("user") UserDTO user, Model model){
//        user.setId(new ObjectId());
        model.addAttribute("user", user);
        userDao.create(user);


        return "user/createUserSuccess";
    }


    // ------------------------ UPDATE
    @GetMapping("/admin/update/{id}")
    public String updateUser(@PathVariable("id") ObjectId id, Model model){
        UserDTO user = userDao.findById(id).orElse(null);
        model.addAttribute("user", user);
        return "user/updateUser";

    }

    @PostMapping("/admin/updateSuccess")
    public String updateUserSuccess(@ModelAttribute("user") UserDTO user, Model model){

        // check if records with given ID exists
        if( user == null ){
            model.addAttribute("user", null);
            return "user/updateUsersSuccess";
        }

        userDao.update(user.getId(), user);
        model.addAttribute("user", user);
        return "user/updateUsersSuccess";
    }


    // ------------------------ DELETE
    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable ObjectId id, Model model){
        UserDTO userDto = userDao.findById(id).orElse(null);
//        if(userDto != null){
//            userDao.deleteById(id);
//        }
        model.addAttribute( "user", userDto );
        return "user/deleteUser";
    }

    @PostMapping("/admin/deleteSuccess")
    public String deleteUserSuccess(@ModelAttribute("user") UserDTO user, Model model){
        user = userDao.findById( user.getId() ).get();
        userDao.deleteById(user.getId());
        model.addAttribute("user", user);
        return "user/deleteUserSuccess";
    }

}