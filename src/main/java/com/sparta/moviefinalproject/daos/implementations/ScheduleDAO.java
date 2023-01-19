package com.sparta.moviefinalproject.daos.implementations;

import com.sparta.moviefinalproject.dtos.ScheduleDTO;
import com.sparta.moviefinalproject.repositories.ScheduleRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleDAO implements com.sparta.moviefinalproject.daos.interfaces.ScheduleDAO {

    private final ScheduleRepository scheduleRepo;

    public ScheduleDAO(ScheduleRepository scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
    }


    @Override
    public void create(ScheduleDTO scheduleDTO) {

    }

    @Override
    public Optional<ScheduleDTO> findById(ObjectId id) {
        return Optional.empty();
    }

    @Override
    public void update(ObjectId id, ScheduleDTO scheduleDTO) {

    }

    @Override
    public void deleteById(ObjectId id) {

    }

    @Override
    public List<ScheduleDTO> findAll() {
        return null;
    }
}
