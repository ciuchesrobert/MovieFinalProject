package com.sparta.moviefinalproject.webcontrollertesting;


import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sparta.moviefinalproject.MovieFinalProjectApplication;
import com.sparta.moviefinalproject.repositories.MovieRepository;
import com.sparta.moviefinalproject.webcontrollers.MovieWebController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = MovieFinalProjectApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
public class MovieWebControllerTesting {
    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Test get method for movie with ID of 573a1390f29313caabcd4135")
    public void testGetAllMovies() throws Exception {
        mvc.perform(get("/movies/573a1390f29313caabcd4135"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("")));
    }

}
