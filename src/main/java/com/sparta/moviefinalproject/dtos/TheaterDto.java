package com.sparta.moviefinalproject.dtos;

import com.sparta.moviefinalproject.entities.Theater;
import com.sparta.moviefinalproject.entities.subentities.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TheaterDto {
    private ObjectId id;
    private Location location;
    private String theaterId;

    public boolean dtoEqualsEntity(Theater obj)
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
