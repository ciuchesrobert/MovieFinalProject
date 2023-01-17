package com.sparta.moviefinalproject.restControllers;

import com.sparta.moviefinalproject.entities.Comment;
import com.sparta.moviefinalproject.entities.Session;
import com.sparta.moviefinalproject.repositories.SessionRepository;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {
    final SessionRepository sessionRepository;

    public SessionController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @GetMapping("/all")
    public List<Session> findAll() {
        return sessionRepository.findAll();
    }


    @GetMapping("/{id}")
    public Session findById(@PathVariable("id") String id) {
        return sessionRepository.findById(new ObjectId(id)).get();
    }
}
