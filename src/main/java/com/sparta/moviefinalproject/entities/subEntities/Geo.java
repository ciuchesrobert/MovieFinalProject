package com.sparta.moviefinalproject.entities.subEntities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Geo {
    @Transient
    private Double[] coordinates;
    private String type;
}
