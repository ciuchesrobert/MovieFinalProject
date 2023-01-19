package com.sparta.moviefinalproject.controllers.web;
import com.sparta.moviefinalproject.daos.implementations.UserDAO;
import com.sparta.moviefinalproject.dtos.MovieDTO;
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

    private final UserDAO userDAO;
    public UserWebController(UserDAO userDao) {
        this.userDAO = userDao;
    }
    
    @RequestMapping("/home")
    public String usersHome(Model model){
        return "usersHome/usersHome";
    }

//    This is a method for finding user by id:
    @GetMapping("/basic/searchById/{id}")
    public String getUserById(@PathVariable ObjectId id, Model model){
        Optional<UserDTO> userOptional = userDAO.findById(id);
        UserDTO user = null;
        if(userOptional.isPresent()){
            user = userOptional.get();
        }
        model.addAttribute("user",user);
        model.addAttribute("id",id);
        return "user/displayUser";
    }

    //    This is a method for finding user by email:
    @GetMapping("/basic/searchByEmail/{email}")
    public String getUserByEmail(@PathVariable String email, Model model){
        Optional<UserDTO> userOptional = userDAO.findByEmail(email);
        UserDTO user = null;
        if(userOptional.isPresent()){
            user = userOptional.get();
        }
        model.addAttribute("user",user);
        model.addAttribute("email",email);
        return "user/displayUser";
    }

    @GetMapping("/basic/{pageNum}")
    public String getAllUsers(Model model, @PathVariable int pageNum){
        Page<User> users = userDAO.findAllUsers(pageNum);
        model.addAttribute("users", users);
        return "user/displayAllUsers";
    }

    @GetMapping("/admin/create")
    public String createUser(Model model){
        UserDTO user = new UserDTO();
        user.setId(new ObjectId());
        model.addAttribute("user", user);
        return "user/createUser";
    }

    @PostMapping("/admin/createSuccess")
    public String creatUserSuccess(@ModelAttribute("user") UserDTO user, Model model){
        model.addAttribute("user", user);
        userDAO.create(user);


        return "user/createUserSuccess";
    }


    @GetMapping("/admin/update")
    public String updateUser(Model model, UserDTO user) {
        model.addAttribute("user", user);
        return "user/updateUser";
    }
    @PostMapping("/admin/updateSuccess")
    public String updateSuccess(@ModelAttribute("user") UserDTO user, Model model) {
        Optional<UserDTO> userDTOOptional = userDAO.findById(user.getId());
        if (userDTOOptional.isPresent()) {
            userDAO.update(user.getId(),user);
        }
        return "user/updateUserSuccess";
    }

    @GetMapping("/admin/delete")
    public String deleteUser(Model model, UserDTO user) {
        model.addAttribute("user", user);
        return "user/deleteUser";
    }
    @PostMapping("/admin/deleteSuccess")
    public String deleteSuccess(@ModelAttribute("user") UserDTO user, Model model) {
        Optional<UserDTO> userDTOOptional = userDAO.findByEmail(user.getEmail());
        if (userDTOOptional.isPresent()) {
            userDAO.deleteByEmail(user.getEmail());
        }
        return "user/deleteUserSuccess";
    }
}