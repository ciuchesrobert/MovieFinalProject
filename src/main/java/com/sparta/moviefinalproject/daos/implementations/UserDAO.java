package com.sparta.moviefinalproject.daos.implementations;

import com.sparta.moviefinalproject.converters.UserConverter;
import com.sparta.moviefinalproject.dtos.UserDTO;
import com.sparta.moviefinalproject.entities.User;
import com.sparta.moviefinalproject.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDAO implements com.sparta.moviefinalproject.daos.interfaces.UserDAO {

    private final UserRepository userRepo;

    public UserDAO(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void create(UserDTO user) {
        userRepo.insert(new UserConverter().dtoToEntity(user));
    }

    @Override
    public Optional<UserDTO> findById(ObjectId id) {
        if(userRepo.findById(id).isPresent()) {
            User user = userRepo.findById(id).get();
            return Optional.of(new UserConverter().entityToDto(user));
        }
        return Optional.empty();
    }

    public Optional<UserDTO> findByEmail(String email) {
        if(userRepo.findByEmail(email).isPresent()) {
            User user = userRepo.findByEmail(email).get();
            return Optional.of(new UserConverter().entityToDto(user));
        }
        return Optional.empty();
    }

    @Override
    public void update(ObjectId id, UserDTO updatedUser) {
            User user = new UserConverter().dtoToEntity(updatedUser);
            user.setId(id);
            userRepo.save(user);
    }

    @Override
    public void deleteById(ObjectId id) {
        if(userRepo.findById(id).isPresent()) {
            userRepo.deleteById(id);
        }
    }

    public void deleteByEmail(String email) {
        if(userRepo.findByEmail(email).isPresent()) {
            userRepo.deleteByEmail(email);
        }
    }

    @Override
    public List<UserDTO> findAll()
    {
        List<User> users = userRepo.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for(User user : users)
        {
            userDTOs.add(new UserConverter().entityToDto(user));
        }
        return userDTOs;
    }

    public Page<User> findAllUsers(int pageNum){
        return userPage(PageRequest.of(pageNum, 10));
    }

    public Page<User> userPage(Pageable pageable){
        return userRepo.findAll(pageable);
    }
}
