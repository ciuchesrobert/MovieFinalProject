package com.sparta.moviefinalproject.entities;

import com.sparta.moviefinalproject.converters.subtypeconverters.AwardConverter;
import com.sparta.moviefinalproject.converters.subtypeconverters.LocationConverter;
import com.sparta.moviefinalproject.dtos.TheaterDto;
import com.sparta.moviefinalproject.entities.subentities.Award;
import com.sparta.moviefinalproject.entities.subentities.Location;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "theaters")
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @NonNull
    private ObjectId id;
    @Embedded
    @NonNull
    private Location location;
    @NonNull
    private String theaterId;

    public boolean entityEqualsDto(TheaterDto obj)
    {
        Location locationConverted = new Location(obj.getLocation().getAddress(), obj.getLocation().getGeo());
        if (this.getId() == obj.getId() &&
        this.getLocation().equals(locationConverted) &&
        this.getTheaterId() == obj.getTheaterId())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
