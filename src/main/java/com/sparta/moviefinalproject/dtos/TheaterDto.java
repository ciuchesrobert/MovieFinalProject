package com.sparta.moviefinalproject.dtos;

import com.sparta.moviefinalproject.entities.subentities.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TheaterDto {
    private ObjectId id;
    private Location location;
    private String theaterId;
}
