package com.sparta.moviefinalproject.converters;

import com.sparta.moviefinalproject.dtos.ScheduleDTO;
import com.sparta.moviefinalproject.entities.Schedule;

public class ScheduleConverter implements Converter<ScheduleDTO, Schedule> {
    @Override
    public Schedule dtoToEntity(ScheduleDTO scheduleDTO) {
        return new Schedule(scheduleDTO.getId(),
                scheduleDTO.getMovieId(),
                scheduleDTO.getTheaterId(),
                scheduleDTO.getDateTime());
    }

    @Override
    public ScheduleDTO entityToDto(Schedule schedule) {
        return new ScheduleDTO(schedule.getId(),
                schedule.getMovieId(),
                schedule.getTheaterId(),
                schedule.getDateTime());
    }
}
