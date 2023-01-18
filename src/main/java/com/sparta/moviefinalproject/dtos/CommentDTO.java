package com.sparta.moviefinalproject.dtos;
import com.mongodb.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime date;
}
