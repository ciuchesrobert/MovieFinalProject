package com.sparta.moviefinalproject.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.sparta.moviefinalproject.entities.Comment;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private ObjectId id;
    private String name;
    private String email;
    private ObjectId movieId;
    private String text;
    private LocalDateTime date;


//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public boolean dtoEqualsEntity(Comment obj) {
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
