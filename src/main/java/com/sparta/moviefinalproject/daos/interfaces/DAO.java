package com.sparta.moviefinalproject.daos.interfaces;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    T create(T t);

    Optional<T> findById(ObjectId id);

    T update(ObjectId id, T t);

    void deleteById(ObjectId id);

    List<T> findAll();
}
