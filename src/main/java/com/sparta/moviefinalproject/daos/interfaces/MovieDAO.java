package com.sparta.moviefinalproject.daos.interfaces;

import com.sparta.moviefinalproject.dtos.MovieDTO;

import java.util.List;

public interface MovieDAO extends DAO<MovieDTO> {
    List<MovieDTO> findAllMoviesByTitleContaining(String name);
}
