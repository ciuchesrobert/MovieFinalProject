package com.sparta.moviefinalproject.entities.subentities;

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
    @NonNull
    private String type;
    @Transient
    @NonNull
    private Double[] coordinates;
}
