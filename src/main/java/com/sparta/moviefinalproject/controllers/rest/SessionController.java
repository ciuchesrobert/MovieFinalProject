package com.sparta.moviefinalproject.controllers.rest;

import com.sparta.moviefinalproject.repositories.SessionRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {
    final SessionRepository sessionRepository;

    public SessionController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }
}