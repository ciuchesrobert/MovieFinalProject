package com.sparta.moviefinalproject.converters;

import com.sparta.moviefinalproject.dtos.UserDTO;
import com.sparta.moviefinalproject.entities.User;

public class UserConverter implements Converter<UserDTO, User> {

    @Override
    public User dtoToEntity(UserDTO userDto) {
        return new User(userDto.getId(),
                userDto.getEmail(),
                userDto.getName(),
                userDto.getPassword());
    }

    @Override
    public UserDTO entityToDto(User user) {
        return new UserDTO(user.getId(),
                user.getEmail(),
                user.getName(),
                user.getPassword());
    }
}
