package com.sparta.moviefinalproject.DBObjects;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link org.springframework.data.jpa.domain.AbstractAuditable} entity
 */
@Data
public class Award{
    private Integer nominaions;
    private String text;
    private Integer wins;

}