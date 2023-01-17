package com.sparta.moviefinalproject.converters.subtypes;

import com.sparta.moviefinalproject.converters.Converter;
import com.sparta.moviefinalproject.dtos.subdtos.LocationDto;
import com.sparta.moviefinalproject.entities.subentities.Location;

public class LocationConverter implements Converter<LocationDto, Location> {
    @Override
    public Location dtoToEntity(LocationDto locationDto) {
        return new Location(locationDto.getAddress(),
                locationDto.getGeo());
    }

    @Override
    public LocationDto entityToDto(Location location) {
        return new LocationDto(location.getAddress(),
                location.getGeo());
    }
}
