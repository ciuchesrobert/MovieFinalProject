package com.sparta.moviefinalproject.webControllers;

import com.sparta.moviefinalproject.repositories.SessionRepository;
import org.springframework.stereotype.Controller;

@Controller

public class SessionWebController {
    final SessionRepository sessionRepository;

    public SessionWebController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

}
