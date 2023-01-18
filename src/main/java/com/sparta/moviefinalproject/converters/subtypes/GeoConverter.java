package com.sparta.moviefinalproject.converters.subtypes;

import com.sparta.moviefinalproject.converters.Converter;
import com.sparta.moviefinalproject.dtos.subdtos.GeoDTO;
import com.sparta.moviefinalproject.entities.subentities.Geo;

public class GeoConverter implements Converter<GeoDTO, Geo> {
    @Override
    public Geo dtoToEntity(GeoDTO geoDto) {
        return new Geo(geoDto.getType(),
                geoDto.getCoordinates());
    }

    @Override
    public GeoDTO entityToDto(Geo geo) {
        return new GeoDTO(geo.getType(),
                geo.getCoordinates());
    }
}
