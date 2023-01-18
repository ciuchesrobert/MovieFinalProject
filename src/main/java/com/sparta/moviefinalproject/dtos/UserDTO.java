package com.sparta.moviefinalproject.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import com.sparta.moviefinalproject.entities.User;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private ObjectId id;
    private String email;
    private String name;
    private String password;

    public UserDTO(String email, String name, String password) {
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
