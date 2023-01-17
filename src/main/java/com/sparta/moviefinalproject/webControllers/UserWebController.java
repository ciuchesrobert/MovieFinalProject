package com.sparta.moviefinalproject.webControllers;
import com.sparta.moviefinalproject.DAO.UsersDAO;
import com.sparta.moviefinalproject.dtos.UserDto;
import com.sparta.moviefinalproject.daos.implementations.UserDao;

import com.sparta.moviefinalproject.entities.Theater;
import com.sparta.moviefinalproject.entities.User;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import com.sparta.moviefinalproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserWebController {

    final UserDao userDao;


    public UserWebController(UserDao userDao) {
        this.userDao = userDao;
    }


    // ---------------- READ
    @GetMapping("/search")
    public String findUserById(Model model, ObjectId id){
        UserDto user = new UserDto();
        model.addAttribute("user",user);

        return "userDisplay";
    }

    @PostMapping("/search/success")
    public String findUserByIdSuccess(@ModelAttribute("user") UserDto user, Model model){
        user = userDao.findById( user.getId() ).orElse(null);
        model.addAttribute("user",user);
        return "userDisplaySuccess";
    }

    @GetMapping("/all")
    public String getAllUsers(Model model){
        Page<User> users = userDao.findAllUsers();
        model.addAttribute("users", users);
        return "userDisplayAll";
    }

    // ------------------ CREATE
    @GetMapping("/create")
    public String createUser(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "createUser";
    }

    @PostMapping("/create/success")
    public String creatUserSuccess(@ModelAttribute("user")UserDto user){
        user.setId(user.getId());
        userDao.create(user);
        return "createUserSuccess";
    }


    // ------------------------ UPDATE
    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable("id") ObjectId id, Model model){
        UserDto user = userDao.findById(id).orElse(null);
        model.addAttribute("user", user);
        return "userUpdate";

    }

    @PostMapping("/update/success")
    public String updateUserSuccess(@ModelAttribute("user")UserDto user, Model model){

        // check if records with given ID exists
        if( user == null ){
            model.addAttribute("user", null);
            return "userUpdateSuccess";
        }
        userDao.create(user);
        model.addAttribute("user", user);
        return "userUpdateSuccess";
    }


    // ------------------------ DELETE
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable ObjectId id, Model model){
        UserDto userDto = userDao.findById(id).orElse(null);
        if(userDto != null){
            userDao.deleteById(id);
        }
        model.addAttribute( "user", userDto );
        return "userDelete";
    }

    @PostMapping("/delete/success")
    public String deleteUserSuccess(@ModelAttribute("user")UserDto user, Model model){
        user = userDao.findById( user.getId() ).get();
        userDao.deleteById(user.getId());
        model.addAttribute("user", user);
        return "userDeleteSuccess";
    }

}
