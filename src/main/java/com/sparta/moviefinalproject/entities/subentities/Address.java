package com.sparta.moviefinalproject.entities.subentities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import java.util.Objects;

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
    private String street1;
    @NonNull
    private String zipcode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return city.equals(address.city) && state.equals(address.state) && street1.equals(address.street1) && zipcode.equals(address.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, state, street1, zipcode);
    }
}
