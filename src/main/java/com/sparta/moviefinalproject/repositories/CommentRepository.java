package com.sparta.moviefinalproject.repositories;
import com.sparta.moviefinalproject.entities.Comment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<Comment, ObjectId> {

}