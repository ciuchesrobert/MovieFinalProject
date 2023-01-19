package com.sparta.moviefinalproject.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {
    private ObjectId id;
    private List<ObjectId> movieId ;
    private ObjectId theaterId;
    private List<LocalDateTime> dateTime;
}
