package com.sparta.moviefinalproject.controllertesting.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.moviefinalproject.MovieFinalProjectApplication;
import com.sparta.moviefinalproject.dtos.TheaterDTO;
import com.sparta.moviefinalproject.entities.subentities.Address;
import com.sparta.moviefinalproject.entities.subentities.Geo;
import com.sparta.moviefinalproject.entities.subentities.Location;
import org.bson.types.ObjectId;
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
        String id = "59a47286cfa9a3a73e51e72c";
        mvc.perform(get("/api/theaters/" + id)
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
        mvc.perform(get("/api/theaters")
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
        String id = "63c97206dc255d54e5719597";
        mvc.perform(MockMvcRequestBuilders
                        .post("/comments")
                        .content(asJsonString(new TheaterDTO(
                                new ObjectId(id),
                                new Location(
                                        new Address(
                                                "City",
                                                "State",
                                                "Street",
                                                "Zipcode"),
                                                new Geo(
                                                        "point",
                                                        new Double[] {0.0, 0.0})),
                                                        "123")
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.location").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.theaterId").value("123"));
    }

    @Test
    @Disabled
    public void UpdateTheater_CheckIfUpdatesPersist() throws Exception {
        String id = "63c97206dc255d54e5719597";
        mvc.perform( MockMvcRequestBuilders
                        .put("/comments/" + id)
                        .content(asJsonString(new TheaterDTO(
                                new ObjectId(id),
                                new Location(
                                        new Address(
                                                "City",
                                                "State",
                                                "Street",
                                                "Zipcode"),
                                        new Geo(
                                                "point",
                                                new Double[] {0.0, 0.0})),
                                "123")
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("yash2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("yash2@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value("text2"));
    }
}