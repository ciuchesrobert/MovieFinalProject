package com.sparta.moviefinalproject.entities;

import com.sparta.moviefinalproject.dtos.CommentDTO;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private ObjectId id;
    private String name;
    private String email;
    @Field("movie_id")
    private ObjectId movieId;
    private String text;
    private LocalDateTime date;

    public boolean entityEqualsDto(CommentDTO obj) {
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
