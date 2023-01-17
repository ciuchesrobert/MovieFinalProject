package com.sparta.moviefinalproject.entities.subEntities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Geo {
    @Transient
    @NonNull
    private Double[] coordinates;
    @NonNull
    private String type;
}
