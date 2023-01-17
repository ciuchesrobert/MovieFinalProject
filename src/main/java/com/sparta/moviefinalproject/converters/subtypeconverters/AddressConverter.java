package com.sparta.moviefinalproject.converters.subtypeconverters;

import com.sparta.moviefinalproject.converters.Converter;
import com.sparta.moviefinalproject.dtos.subdtos.AddressDto;
import com.sparta.moviefinalproject.entities.subentities.Address;

public class AddressConverter implements Converter<AddressDto, Address> {

    @Override
    public Address dtoToEntity(AddressDto addressDto) {
        return new Address(addressDto.getStreet(),
                addressDto.getCity(),
                addressDto.getState(),
                addressDto.getZipcode());
    }

    @Override
    public AddressDto entityToDto(Address address) {
        return new AddressDto(address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getZipcode());
    }
}
