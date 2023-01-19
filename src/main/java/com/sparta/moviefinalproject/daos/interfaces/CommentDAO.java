package com.sparta.moviefinalproject.daos.interfaces;

import com.sparta.moviefinalproject.dtos.CommentDTO;
import org.bson.types.ObjectId;

import java.util.List;

public interface CommentDAO extends DAO<CommentDTO> {
    List<CommentDTO> findAllCommentsByMovieId(ObjectId objectId);
}
