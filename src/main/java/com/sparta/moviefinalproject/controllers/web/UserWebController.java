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

    private final UserDAO userDao;
    public UserWebController(UserDAO userDao) {
        this.userDao = userDao;
    }
    
    @RequestMapping("/usersHome")
    public String usersHome(Model model){
        return "usersHome/usersHome";
    }
   
    // ---------------- READ
    @GetMapping("/basic/search")
    public String findUserById(Model model, ObjectId id){
        UserDTO user = new UserDTO();
        model.addAttribute("user",user);

        return "userDisplay";
    }

    @PostMapping("/basic/search/success")
    public String findUserByIdSuccess(@ModelAttribute("user") UserDTO user, Model model){
        user = userDAO.findById( user.getId() ).orElse(null);
        model.addAttribute("user",user);
        return "userDisplaySuccess";
    }

    @GetMapping("/basic")
    public String getAllUsers(Model model){
        Page<User> users = userDAO.findAllUsers();
        model.addAttribute("users", users);
        return "userDisplayAll";
    }

    // ------------------ CREATE
    @GetMapping("/admin/create")
    public String createUser(Model model){
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "createUser";
    }

    @PostMapping("/admin/create/success")
    public String creatUserSuccess(@ModelAttribute("user") UserDTO user){
        user.setId(user.getId());
        userDAO.create(user);
        return "createUserSuccess";
    }


    // ------------------------ UPDATE
    @GetMapping("/admin/update/{id}")
    public String updateUser(@PathVariable("id") ObjectId id, Model model){
        UserDTO user = userDAO.findById(id).orElse(null);
        model.addAttribute("user", user);
        return "userUpdate";

    }

    @PostMapping("/admin/update/success")
    public String updateUserSuccess(@ModelAttribute("user") UserDTO user, Model model){

        // check if records with given ID exists
        if( user == null ){
            model.addAttribute("user", null);
            return "userUpdateSuccess";
        }

        userDAO.update(user.getId(), user);
        model.addAttribute("user", user);
        return "userUpdateSuccess";
    }


    // ------------------------ DELETE
    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable ObjectId id, Model model){
        UserDTO userDto = userDAO.findById(id).orElse(null);
        model.addAttribute( "user", userDto );
        return "userDelete";
    }

    @PostMapping("/admin/delete/success")
    public String deleteUserSuccess(@ModelAttribute("user") UserDTO user, Model model){

        // check if records with given ID exists
        if( user == null){
            model.addAttribute("user", null);
            return "userDeleteSuccess";
        }
        // otherwise delete from DB
        userDAO.deleteById(user.getId());
        model.addAttribute("user", user);
        return "userDeleteSuccess";
    }

}