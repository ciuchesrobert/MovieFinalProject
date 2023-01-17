package com.sparta.moviefinalproject.webcontrollers;

import com.sparta.moviefinalproject.repositories.SessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sessions")
public class SessionWebController {
    final SessionRepository sessionRepository;

    public SessionWebController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

}
