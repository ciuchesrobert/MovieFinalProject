package com.sparta.moviefinalproject.repositories;

import com.sparta.moviefinalproject.dtos.CommentDTO;
import com.sparta.moviefinalproject.entities.Comment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, ObjectId> {
    List<CommentDTO> findAllCommentsByMovieId(ObjectId objectId);
}