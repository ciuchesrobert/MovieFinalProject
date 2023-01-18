package com.sparta.moviefinalproject.daos.implementations;

import com.sparta.moviefinalproject.converters.TheaterConverter;
import com.sparta.moviefinalproject.dtos.TheaterDTO;
import com.sparta.moviefinalproject.entities.Theater;
import com.sparta.moviefinalproject.repositories.TheaterRepository;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TheaterDAO implements com.sparta.moviefinalproject.daos.interfaces.TheaterDAO {

    private final TheaterRepository theaterRepo;

    public TheaterDAO(TheaterRepository theaterRepo) {
        this.theaterRepo = theaterRepo;
    }


    @Override
    public void create(TheaterDTO theaterDto) {
        theaterRepo.insert(new TheaterConverter().dtoToEntity(theaterDto));
    }

    @Override
    public Optional<TheaterDTO> findById(ObjectId id) {
        if(theaterRepo.findById(id).isPresent()) {
            Theater theater = theaterRepo.findById(id).get();
            return Optional.of(new TheaterConverter().entityToDto(theater));
        }
        return Optional.empty();
    }

    @Override
    public void update(ObjectId id, TheaterDTO updatedTheater) {
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
    public List<TheaterDTO> findAll() {
        List<Theater> theaters = theaterRepo.findAll();
        List<TheaterDTO> theaterDTOs = new ArrayList<>();
        for(Theater theater : theaters) {
            theaterDTOs.add(new TheaterConverter().entityToDto(theater));
        }
        return theaterDTOs;
    }
}
