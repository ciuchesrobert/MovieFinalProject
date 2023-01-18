package com.sparta.moviefinalproject.dtos;
import com.sparta.moviefinalproject.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private ObjectId id;
    private String name;
    private String email;
    private ObjectId movieId;
    private String text;
    private LocalDateTime date;

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
