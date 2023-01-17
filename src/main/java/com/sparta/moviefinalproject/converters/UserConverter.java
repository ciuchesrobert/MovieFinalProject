package com.sparta.moviefinalproject.converters;

import com.sparta.moviefinalproject.dtos.UserDto;
import com.sparta.moviefinalproject.entities.User;

public class UserConverter implements Converter<UserDto, User> {

    @Override
    public User dtoToEntity(UserDto userDto) {
        return new User(userDto.getId(),
                userDto.getEmail(),
                userDto.getName(),
                userDto.getPassword());
    }

    @Override
    public UserDto entityToDto(User user) {
        return new UserDto(user.getId(),
                user.getEmail(),
                user.getName(),
                user.getPassword());
    }
}
