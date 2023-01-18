package com.sparta.moviefinalproject.converters.subtypes;

import com.sparta.moviefinalproject.converters.Converter;
import com.sparta.moviefinalproject.dtos.subdtos.LocationDTO;
import com.sparta.moviefinalproject.entities.subentities.Location;

public class LocationConverter implements Converter<LocationDTO, Location> {
    @Override
    public Location dtoToEntity(LocationDTO locationDto) {
        return new Location(locationDto.getAddress(),
                locationDto.getGeo());
    }

    @Override
    public LocationDTO entityToDto(Location location) {
        return new LocationDTO(location.getAddress(),
                location.getGeo());
    }
}
