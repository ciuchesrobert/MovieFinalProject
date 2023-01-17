package com.sparta.moviefinalproject.dtos.subdtos;

import com.sparta.moviefinalproject.entities.subentities.Address;
import com.sparta.moviefinalproject.entities.subentities.Geo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO {
    private Address address;
    private Geo geo;
}
