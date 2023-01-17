package com.sparta.moviefinalproject.converters;

import com.sparta.moviefinalproject.dtos.CommentDto;
import com.sparta.moviefinalproject.entities.Comment;

public class CommentConverter implements Converter<CommentDto, Comment>{
    @Override
    public Comment dtoToEntity(CommentDto commentDto)
    {
        return new Comment(commentDto.getId(),
                commentDto.getName(),
                commentDto.getEmail(),
                commentDto.getMovieId(),
                commentDto.getText(),
                commentDto.getDate());
    }

    @Override
    public CommentDto entityToDto(Comment comment)
    {
        return new CommentDto(comment.getId(),
                comment.getName(),
                comment.getEmail(),
                comment.getMovieId(),
                comment.getText(),
                comment.getDate());
    }
}
