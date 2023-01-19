package com.sparta.moviefinalproject.daos.implementations;

import com.sparta.moviefinalproject.converters.CommentConverter;
import com.sparta.moviefinalproject.converters.MovieConverter;
import com.sparta.moviefinalproject.dtos.CommentDTO;
import com.sparta.moviefinalproject.entities.Comment;
import com.sparta.moviefinalproject.entities.Theater;
import com.sparta.moviefinalproject.repositories.CommentRepository;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CommentDAO implements com.sparta.moviefinalproject.daos.interfaces.CommentDAO {

    private final CommentRepository commentRepo;

    public CommentDAO(CommentRepository commentRepo)
    {
        this.commentRepo = commentRepo;
    }

    @Override
    public CommentDTO create(CommentDTO commentDto) {
        return new CommentConverter().entityToDto(commentRepo.insert(new CommentConverter().dtoToEntity(commentDto)));
    }

    @Override
    public Optional<CommentDTO> findById(ObjectId id) {
        if(commentRepo.findById(id).isPresent())
        {
            Comment comment = commentRepo.findById(id).get();
            return Optional.of(new CommentConverter().entityToDto(comment));
        }

        return Optional.empty();
    }

    public List<CommentDTO> findAllByEmail(String email){
        if (commentRepo.findAllByEmail(email).size()>0){
            return commentRepo.findAllByEmail(email);
        }
        else return null;
    }

    @Override
    public CommentDTO update(ObjectId id, CommentDTO updatedComment) {
        Comment comment = new CommentConverter().dtoToEntity(updatedComment);
        comment.setId(id);
        commentRepo.save(comment);
        return new CommentConverter().entityToDto(comment);
    }

    @Override
    public void deleteById(ObjectId id) {
        if (commentRepo.findById(id).isPresent())
        {
            commentRepo.deleteById(id);
        }
    }

    @Override
    public List<CommentDTO> findAll() {
        List<Comment> comments = commentRepo.findAll();
        List<CommentDTO> commentDTOs = new ArrayList<>();
        for (Comment comment : comments)
        {
            commentDTOs.add(new CommentConverter().entityToDto(comment));
        }
        return commentDTOs;
    }

    public Page<Comment> findAllCommentsPagination(int pageNum){
        return commentPage(PageRequest.of(pageNum, 10));
    }

    public Page<Comment> commentPage(Pageable pageable){
        return commentRepo.findAll(pageable);
    }

    @Override
    public List<CommentDTO> findAllCommentsByMovieId(ObjectId objectId) {
        return commentRepo.findAllCommentsByMovieId(objectId);
    }
}
