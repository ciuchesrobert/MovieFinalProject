package com.sparta.moviefinalproject.entities;


import com.mongodb.lang.Nullable;
import com.sparta.moviefinalproject.dtos.UserDto;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @NonNull
    private ObjectId id;
    @NonNull
    private String email;
    @NonNull
    private String name;
    @NonNull
    private String password;


    public boolean entityEqualsDto(UserDto obj) {
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
