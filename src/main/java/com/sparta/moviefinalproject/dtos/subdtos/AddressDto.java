package com.sparta.moviefinalproject.dtos.subdtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private String city;
    private String state;
    private String street1;
    private String zipcode;
}
