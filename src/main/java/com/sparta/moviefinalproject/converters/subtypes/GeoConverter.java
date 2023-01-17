package com.sparta.moviefinalproject.converters.subtypes;

import com.sparta.moviefinalproject.converters.Converter;
import com.sparta.moviefinalproject.dtos.subdtos.GeoDto;
import com.sparta.moviefinalproject.entities.subentities.Geo;

public class GeoConverter implements Converter<GeoDto, Geo> {
    @Override
    public Geo dtoToEntity(GeoDto geoDto) {
        return new Geo(geoDto.getType(),
                geoDto.getCoordinates());
    }

    @Override
    public GeoDto entityToDto(Geo geo) {
        return new GeoDto(geo.getType(),
                geo.getCoordinates());
    }
}
