package com.sparta.moviefinalproject.dtos;

import com.sparta.moviefinalproject.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private ObjectId id;
    private String email;
    private String name;
    private String password;

    public UserDto(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
    public boolean dtoEqualsEntity(User obj) {
        if (this.getId() == obj.getId() &&
                this.getEmail() == obj.getEmail() &&
                this.getName() == obj.getName() &&
                this.getPassword() == obj.getPassword()){
            return true;
        }
        else {
            return false;
        }


    }
}
