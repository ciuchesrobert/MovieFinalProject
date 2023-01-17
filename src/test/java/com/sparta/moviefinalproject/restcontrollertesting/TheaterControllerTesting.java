package com.sparta.moviefinalproject.restcontrollertesting;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = MovieFinalProjectApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
public class TheaterControllerTesting {
    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Test get method for theater with ID of 59a47286cfa9a3a73e51e72c")
    public void FindCommentById_SuccessIfExists() throws Exception{
        mvc.perform(get("/api/theaters/59a47286cfa9a3a73e51e72c")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.location.address.city").value("Bloomington"))
                .andExpect(jsonPath("$.location.address.zipcode").value("55425"))
                .andExpect(jsonPath("$.theaterId").value("1000"));
    }

    @Test
    @DisplayName("Test get method for all theaters")
    public void FindAllComments_SuccessIfExists() throws Exception{
        mvc.perform(get("/api/theaters")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].theaterId").value("1000"))
                .andExpect(jsonPath("$[1].theaterId").value("1003"))
                .andExpect(jsonPath("$[2].theaterId").value("1008"));
    }
}