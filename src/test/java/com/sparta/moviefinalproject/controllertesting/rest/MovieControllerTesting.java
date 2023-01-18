package com.sparta.moviefinalproject.controllertesting.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.moviefinalproject.MovieFinalProjectApplication;
import com.sparta.moviefinalproject.dtos.MovieDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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

    public String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Testing @GetMapping for findById method on movie with ID of 573a1390f29313caabcd4135")
    public void FindMovieById_SuccessIfExists() throws Exception{
        mvc.perform(get("/api/movies/{id}","573a1390f29313caabcd4135")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id.timestamp").value("1463423888"));
    }

    @Test
    @DisplayName("Test @GetMapping for findAll method for movies")
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

    @Test
    @Disabled
    @DisplayName("Test @PostMapping for create method for comments")
    public void CreateMovie_CheckIfExists() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/movies")
                        .content(asJsonString(new MovieDTO()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("yash"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("yash@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value("text"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    @Disabled
    public void UpdateMovie_CheckIfUpdatesPersist() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                        .put("/movies/{id}", "no idea what to put for id")
                        .content(asJsonString(new MovieDTO()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("yash2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("yash2@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value("text2"));
    }


}
