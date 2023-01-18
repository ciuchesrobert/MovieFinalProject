package com.sparta.moviefinalproject.converters;

import com.sparta.moviefinalproject.dtos.CommentDTO;
import com.sparta.moviefinalproject.entities.Comment;

public class CommentConverter implements Converter<CommentDTO, Comment>{
    @Override
    public Comment dtoToEntity(CommentDTO commentDto)
    {
        return new Comment(commentDto.getId(),
                commentDto.getName(),
                commentDto.getEmail(),
                commentDto.getMovieId(),
                commentDto.getText(),
                commentDto.getDate());
    }

    @Override
    public CommentDTO entityToDto(Comment comment)
    {
        return new CommentDTO(comment.getId(),
                comment.getName(),
                comment.getEmail(),
                comment.getMovieId(),
                comment.getText(),
                comment.getDate());
    }
}
