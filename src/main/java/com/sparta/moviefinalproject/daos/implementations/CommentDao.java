package com.sparta.moviefinalproject.daos.implementations;

import com.sparta.moviefinalproject.converters.CommentConverter;
import com.sparta.moviefinalproject.dtos.CommentDto;
import com.sparta.moviefinalproject.entities.Comment;
import com.sparta.moviefinalproject.repositories.CommentRepository;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommentDao implements com.sparta.moviefinalproject.daos.interfaces.CommentDao {

    private final CommentRepository commentRepo;

    public CommentDao(CommentRepository commentRepo)
    {
        this.commentRepo = commentRepo;
    }

    @Override
    public void create(CommentDto commentDto) {
        commentRepo.insert(new CommentConverter().dtoToEntity(commentDto));
    }

    @Override
    public Optional<CommentDto> findById(ObjectId id) {
        if(commentRepo.findById(id).isPresent())
        {
            Comment comment = commentRepo.findById(id).get();
            return Optional.of(new CommentConverter().entityToDto(comment));
        }

        return Optional.empty();
    }

    @Override
    public void update(ObjectId id, CommentDto updatedComment) {
        Comment comment = new CommentConverter().dtoToEntity(updatedComment);
        comment.setId(id);
        commentRepo.save(comment);
    }

    @Override
    public void deleteById(ObjectId id) {
        if (commentRepo.findById(id).isPresent())
        {
            commentRepo.deleteById(id);
        }
    }

    @Override
    public List<CommentDto> findAll() {
        List<Comment> comments = commentRepo.findAll();
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : comments)
        {
            commentDtos.add(new CommentConverter().entityToDto(comment));
        }
        return commentDtos;
    }
}
