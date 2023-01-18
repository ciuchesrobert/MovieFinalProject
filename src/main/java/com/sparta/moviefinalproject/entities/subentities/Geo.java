package com.sparta.moviefinalproject.entities.subentities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import java.util.Arrays;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Geo geo = (Geo) o;
        return type.equals(geo.type) && Arrays.equals(coordinates, geo.coordinates);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(type);
        result = 31 * result + Arrays.hashCode(coordinates);
        return result;
    }

}
