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
    // works
    @DisplayName("Testing @GetMapping for findById method on theater with ID of 59a47286cfa9a3a73e51e72c")
    public void FindTheaterById_SuccessIfExists() throws Exception{
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
    // works
    @DisplayName("Test @GetMapping for findAll method for theaters")
    public void FindAllTheaters_SuccessIfExists() throws Exception{
        mvc.perform(get("/api/theaters")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].theaterId").value("1000"))
                .andExpect(jsonPath("$[1].theaterId").value("1008"))
                .andExpect(jsonPath("$[2].theaterId").value("1004"));
    }

    @Test
//    @Disabled
    // works
    @DisplayName("Test @PostMapping for create method for theaters")
    public void CreateTheater_CheckIfExists() throws Exception {
        String id = "63ca9d7c8fa2e415f2b02ae0";
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/theaters")
                        .content(asJsonString(new TheaterDTO(
                                new ObjectId(id),
                                new Location(
                                        new Address(
                                                "City",
                                                "State",
                                                "Street",
                                                "Zipcode"),
                                                new Geo(
                                                        "Point",
                                                        new Double[] {1.1, 2.2})),
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
//    @Disabled
    // works
    public void UpdateTheater_CheckIfUpdatesPersist() throws Exception {
        String id = "63ca9d7c8fa2e415f2b02ae0";
        mvc.perform( MockMvcRequestBuilders
                        .put("/api/theaters/" + id)
                        .content(asJsonString(new TheaterDTO(
                                new ObjectId(id),
                                new Location(
                                        new Address(
                                                "Spartaglobal",
                                                "State",
                                                "Street",
                                                "Zipcode"),
                                        new Geo(
                                                "Point",
                                                new Double[] {0.0, 0.0})),
                                "125")
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.location.address.city").value("Spartaglobal"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.theaterId").value("125"));
    }
}