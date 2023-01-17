package com.sparta.moviefinalproject.converters;

public interface Converter<T, K> {
    public K dtoToEntity(T t);

    public T entityToDto(K k);
}
