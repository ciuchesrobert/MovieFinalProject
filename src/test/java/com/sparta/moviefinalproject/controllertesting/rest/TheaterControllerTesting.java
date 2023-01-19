package com.sparta.moviefinalproject.controllertesting.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.moviefinalproject.MovieFinalProjectApplication;
import com.sparta.moviefinalproject.dtos.TheaterDTO;
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

    public String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Testing @GetMapping for findById method on theater with ID of 59a47286cfa9a3a73e51e72c")
    public void FindCommentById_SuccessIfExists() throws Exception{
        mvc.perform(get("/api/theaters/{id}?apikey=ndlkjdlksajkdsajkaksdlksaj2132","59a47286cfa9a3a73e51e72c")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.location.address.city").value("Bloomington"))
                .andExpect(jsonPath("$.location.address.zipcode").value("55425"))
                .andExpect(jsonPath("$.theaterId").value("1000"));
    }

    @Test
    @DisplayName("Test @GetMapping for findAll method for theaters")
    public void FindAllComments_SuccessIfExists() throws Exception{
        mvc.perform(get("/api/theaters?apikey=ndlkjdlksajkdsajkaksdlksaj2132")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].theaterId").value("1000"))
                .andExpect(jsonPath("$[1].theaterId").value("1003"))
                .andExpect(jsonPath("$[2].theaterId").value("1008"));
    }

    @Test
    @Disabled
    @DisplayName("Test @PostMapping for create method for comments")
    public void CreateTheater_CheckIfExists() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/comments")
                        .content(asJsonString(new TheaterDTO()))
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
    public void UpdateTheater_CheckIfUpdatesPersist() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                        .put("/comments/{id}", "no idea what to put for id")
                        .content(asJsonString(new TheaterDTO()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("yash2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("yash2@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value("text2"));
    }
}