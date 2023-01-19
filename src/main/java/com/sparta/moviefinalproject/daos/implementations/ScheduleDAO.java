package com.sparta.moviefinalproject.daos.implementations;

import com.sparta.moviefinalproject.converters.ScheduleConverter;
import com.sparta.moviefinalproject.dtos.MovieDTO;
import com.sparta.moviefinalproject.dtos.ScheduleDTO;
import com.sparta.moviefinalproject.dtos.TheaterDTO;
import com.sparta.moviefinalproject.entities.Schedule;
import com.sparta.moviefinalproject.repositories.ScheduleRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleDAO implements com.sparta.moviefinalproject.daos.interfaces.ScheduleDAO {

    private final ScheduleRepository scheduleRepo;
    private final MovieDAO movieDAO;
    private final TheaterDAO theaterDAO;

    public ScheduleDAO(ScheduleRepository scheduleRepo,
                       MovieDAO movieDAO, TheaterDAO theaterDAO) {
        this.scheduleRepo = scheduleRepo;
        this.movieDAO = movieDAO;
        this.theaterDAO = theaterDAO;
    }


    @Override
    public ScheduleDTO create(ScheduleDTO scheduleDTO) {
        scheduleDTO.setId(new ObjectId());
        Schedule schedule = new ScheduleConverter().dtoToEntity(scheduleDTO);
        return new ScheduleConverter().entityToDto(scheduleRepo.insert(schedule));

    }

    @Override
    public Optional<ScheduleDTO> findById(ObjectId id) {
        Optional<Schedule> scheduleOpt = scheduleRepo.findById(id);
        if(scheduleOpt.isPresent()) {
            Schedule schedule = scheduleOpt.get();
            return Optional.of(new ScheduleConverter().entityToDto(schedule));
        }
        return Optional.empty();
    }

    @Override
    public ScheduleDTO update(ObjectId id, ScheduleDTO updatedSchedule) {
        Schedule schedule = new ScheduleConverter().dtoToEntity(updatedSchedule);
        schedule.setId(id);
        return new ScheduleConverter().entityToDto(scheduleRepo.save(schedule));
    }

    @Override
    public void deleteById(ObjectId id) {
        if(scheduleRepo.findById(id).isPresent()) {
            scheduleRepo.deleteById(id);
        }
    }

    @Override
    public List<ScheduleDTO> findAll() {
        List<Schedule> schedules = scheduleRepo.findAll();
        List<ScheduleDTO> scheduleDTOs = new ArrayList<>();
        for(Schedule schedule : schedules) {
            scheduleDTOs.add(new ScheduleConverter().entityToDto(schedule));
        }
        return scheduleDTOs;
    }

    @Override
    public List<MovieDTO> findAllMoviesFromSchedule(ObjectId scheduleId) {
        List<MovieDTO> movies = new ArrayList<>();
        List<ObjectId> movieIds = scheduleRepo.findById(scheduleId).get().getMovieId();
        for(ObjectId movieId : movieIds) {
            movies.add(movieDAO.findById(movieId).get());
        }
        return movies;
    }

    @Override
    public TheaterDTO findTheaterFromSchedule(ObjectId scheduleId) {
        return theaterDAO.findById(
                scheduleRepo.findById(scheduleId).get().getTheaterId()).get();
    }

    @Override
    public List<LocalDateTime> findDateTimesFromSchedule(ObjectId scheduleId) {
        return scheduleRepo.findById(scheduleId).get().getDateTime();
    }

}
