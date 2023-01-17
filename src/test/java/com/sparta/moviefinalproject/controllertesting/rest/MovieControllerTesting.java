package com.sparta.moviefinalproject.controllertesting.rest;

import com.sparta.moviefinalproject.MovieFinalProjectApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = MovieFinalProjectApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
public class MovieControllerTesting {
    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Test get method for movie with ID of 573a1390f29313caabcd4135")
    public void FindMovieById_SuccessIfExists() throws Exception{
        mvc.perform(get("/api/movies/573a1390f29313caabcd4135")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id.timestamp").value("1463423888"));
    }

    @Test
    @DisplayName("Test get method for all movies")
    public void FindAllMovies_SuccessIfExists() throws Exception{
        mvc.perform(get("/api/movies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("Blacksmith Scene"))
                .andExpect(jsonPath("$[1].title").value("The Great Train Robbery"))
                .andExpect(jsonPath("$[2].title").value("The Land Beyond the Sunset"));
    }


}
