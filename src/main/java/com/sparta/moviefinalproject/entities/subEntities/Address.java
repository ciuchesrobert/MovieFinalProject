package com.sparta.moviefinalproject.entities.subEntities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @NonNull
    private String city;
    @NonNull
    private String state;
    @NonNull
    private String street;
    @NonNull
    private String zipcode;
}
