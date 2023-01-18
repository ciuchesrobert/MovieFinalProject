package com.sparta.moviefinalproject.entities;

import com.mongodb.lang.NonNull;
import com.mongodb.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime date;
}
