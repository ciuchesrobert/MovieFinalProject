package com.sparta.moviefinalproject.daos.implementations;

import com.sparta.moviefinalproject.converters.TheaterConverter;
import com.sparta.moviefinalproject.dtos.TheaterDto;
import com.sparta.moviefinalproject.entities.Theater;
import com.sparta.moviefinalproject.repositories.TheaterRepository;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TheaterDao implements com.sparta.moviefinalproject.daos.interfaces.TheaterDao {

    private final TheaterRepository theaterRepo;

    public TheaterDao(TheaterRepository theaterRepo) {
        this.theaterRepo = theaterRepo;
    }


    @Override
    public void create(TheaterDto theaterDto) {
        theaterRepo.insert(new TheaterConverter().dtoToEntity(theaterDto));
    }

    @Override
    public Optional<TheaterDto> findById(ObjectId id) {
        if(theaterRepo.findById(id).isPresent()) {
            Theater theater = theaterRepo.findById(id).get();
            return Optional.of(new TheaterConverter().entityToDto(theater));
        }
        return Optional.empty();
    }

    @Override
    public void update(ObjectId id, TheaterDto updatedTheater) {
        Theater theater = new TheaterConverter().dtoToEntity(updatedTheater);
        theater.setId(id);
        theaterRepo.save(theater);
    }

    @Override
    public void deleteById(ObjectId id) {
        if(theaterRepo.findById(id).isPresent()) {
            theaterRepo.deleteById(id);
        }
    }

    @Override
    public List<TheaterDto> findAll() {
        List<Theater> theaters = theaterRepo.findAll();
        List<TheaterDto> theaterDtos = new ArrayList<>();
        for(Theater theater : theaters) {
            theaterDtos.add(new TheaterConverter().entityToDto(theater));
        }
        return theaterDtos;
    }
}
