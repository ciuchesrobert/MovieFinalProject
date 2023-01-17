package com.sparta.moviefinalproject.dtos.subDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AwardDto {
    private int nominations;
    private String text;
    private int wins;
}
