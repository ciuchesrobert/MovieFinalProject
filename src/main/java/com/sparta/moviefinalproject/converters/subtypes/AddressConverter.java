package com.sparta.moviefinalproject.converters.subtypes;

import com.sparta.moviefinalproject.converters.Converter;
import com.sparta.moviefinalproject.dtos.subdtos.AddressDTO;
import com.sparta.moviefinalproject.entities.subentities.Address;

public class AddressConverter implements Converter<AddressDTO, Address> {

    @Override
    public Address dtoToEntity(AddressDTO addressDto) {
        return new Address(addressDto.getStreet1(),
                addressDto.getCity(),
                addressDto.getState(),
                addressDto.getZipcode());
    }

    @Override
    public AddressDTO entityToDto(Address address) {
        return new AddressDTO(address.getStreet1(),
                address.getCity(),
                address.getState(),
                address.getZipcode());
    }
}
