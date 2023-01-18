package com.sparta.moviefinalproject.controllers.web;
import com.sparta.moviefinalproject.daos.implementations.UserDAO;
import com.sparta.moviefinalproject.dtos.UserDTO;
import com.sparta.moviefinalproject.entities.User;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserWebController {

    @RequestMapping("/usersHome")
    public String usersHome(Model model){
        return "usersHome/usersHome";
    }
    private final UserDAO userDao;
    public UserWebController(UserDAO userDao) {
        this.userDao = userDao;
    }


    // ---------------- READ
    @GetMapping("/search")
    public String findUserById(Model model, ObjectId id){
        UserDTO user = new UserDTO();
        model.addAttribute("user",user);

        return "userDisplay";
    }

    @PostMapping("/search/success")
    public String findUserByIdSuccess(@ModelAttribute("user") UserDTO user, Model model){
        user = userDao.findById( user.getId() ).orElse(null);
        model.addAttribute("user",user);
        return "userDisplaySuccess";
    }

    @GetMapping
    public String getAllUsers(Model model){
        Page<User> users = userDao.findAllUsers();
        model.addAttribute("users", users);
        return "userDisplayAll";
    }

    // ------------------ CREATE
    @GetMapping("/create")
    public String createUser(Model model){
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "createUser";
    }

    @PostMapping("/create/success")
    public String creatUserSuccess(@ModelAttribute("user") UserDTO user){
        user.setId(user.getId());
        userDao.create(user);
        return "createUserSuccess";
    }


    // ------------------------ UPDATE
    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable("id") ObjectId id, Model model){
        UserDTO user = userDao.findById(id).orElse(null);
        model.addAttribute("user", user);
        return "userUpdate";

    }

    @PostMapping("/update/success")
    public String updateUserSuccess(@ModelAttribute("user") UserDTO user, Model model){

        // check if records with given ID exists
        if( user == null ){
            model.addAttribute("user", null);
            return "userUpdateSuccess";
        }

        userDao.update(user.getId(), user);
        model.addAttribute("user", user);
        return "userUpdateSuccess";
    }


    // ------------------------ DELETE
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable ObjectId id, Model model){
        UserDTO userDto = userDao.findById(id).orElse(null);
        if(userDto != null){
            userDao.deleteById(id);
        }
        model.addAttribute( "user", userDto );
        return "userDelete";
    }

    @PostMapping("/delete/success")
    public String deleteUserSuccess(@ModelAttribute("user") UserDTO user, Model model){
        user = userDao.findById( user.getId() ).get();
        userDao.deleteById(user.getId());
        model.addAttribute("user", user);
        return "userDeleteSuccess";
    }

}