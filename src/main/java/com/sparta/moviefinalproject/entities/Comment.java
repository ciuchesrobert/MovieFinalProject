package com.sparta.moviefinalproject.entities;

import com.mongodb.lang.NonNull;
import com.mongodb.lang.Nullable;
import com.sparta.moviefinalproject.dtos.CommentDto;
import com.sparta.moviefinalproject.dtos.UserDto;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @NonNull
    private ObjectId id;
    @NonNull
    private String name;
    @NonNull
    private String email;
    @Nullable
    private ObjectId movieId;
    @NonNull
    private String text;
    @NonNull
    private LocalDateTime date;

    public boolean entityEqualsDto(CommentDto obj) {
        if (this.getId() == obj.getId() &&
        this.getDate() == obj.getDate() &&
        this.getEmail() == obj.getEmail() &&
        this.getMovieId() == obj.getMovieId() &&
        this.getText() == obj.getText() &&
        this.getName() == obj.getName()){
            return true;
        }
        else {
            return false;
        }
    }
}
