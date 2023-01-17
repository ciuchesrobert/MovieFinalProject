package com.sparta.moviefinalproject.daos.implementations;

import com.sparta.moviefinalproject.converters.UserConverter;
import com.sparta.moviefinalproject.dtos.UserDto;
import com.sparta.moviefinalproject.entities.User;
import com.sparta.moviefinalproject.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
public class UserDao implements com.sparta.moviefinalproject.daos.interfaces.UserDao {

    private final UserRepository userRepo;

    public UserDao(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void create(UserDto user) {
        userRepo.insert(new UserConverter().dtoToEntity(user));
    }

    @Override
    public UserDto findById(ObjectId id) {
        if(userRepo.findById(id).isPresent()) {
            User user = userRepo.findById(id).get();
            return new UserConverter().entityToDto(user);
        }
        return null;
    }

    @Override
    public void update(ObjectId id, UserDto updatedUser) {
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
}
