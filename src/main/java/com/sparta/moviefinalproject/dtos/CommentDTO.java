package com.sparta.moviefinalproject.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
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
}
