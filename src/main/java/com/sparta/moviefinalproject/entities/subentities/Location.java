package com.sparta.moviefinalproject.entities.subentities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    @Embedded
    private Address address;
    @Embedded
    private Geo geo;

}
