package com.sparta.moviefinalproject.daos.interfaces;

import com.sparta.moviefinalproject.dtos.TheaterDTO;
import com.sparta.moviefinalproject.entities.Theater;
import com.sparta.moviefinalproject.entities.subentities.Address;

import java.util.List;

public interface TheaterDAO extends DAO<TheaterDTO> {
    List<TheaterDTO> findAllTheatersByLocation_Address_ZipcodeContainingIgnoreCase(String name);
    List<TheaterDTO> findAllTheatersByLocation_AddressContaining(Address name);
    List<TheaterDTO> findAllTheatersByLocation_Address_CityContainingIgnoreCase(String name);
    List<TheaterDTO> findAllTheatersByLocation_Address_StateContainingIgnoreCase(String name);
    List<TheaterDTO> findAllTheatersByLocation_Address_Street1ContainingIgnoreCase(String name);
}
