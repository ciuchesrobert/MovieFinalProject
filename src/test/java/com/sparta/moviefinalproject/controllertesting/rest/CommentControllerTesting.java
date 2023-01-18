package com.sparta.moviefinalproject.controllertesting.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.moviefinalproject.MovieFinalProjectApplication;
import com.sparta.moviefinalproject.dtos.CommentDTO;
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

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = MovieFinalProjectApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
public class CommentControllerTesting {
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
    @DisplayName("Testing @GetMapping for findById method on comments with ID of 5a9427648b0beebeb69579e7")
    public void FindCommentById_SuccessIfExists() throws Exception {
        mvc.perform(get("/api/comments/{id}", "5a9427648b0beebeb69579e7")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id.timestamp").value("1519658852"))
                .andExpect(jsonPath("$.email").value("mercedes_tyler@fakegmail.com"))
                .andExpect(jsonPath("$.text").value("Eius veritatis vero facilis quaerat fuga temporibus. " +
                        "Praesentium expedita sequi repellat id. Corporis minima enim ex. " +
                        "Provident fugit nisi dignissimos nulla nam ipsum aliquam."));
    }

    @Test
    @DisplayName("Test @GetMapping for findAll method for comments")
    public void FindAllComments_SuccessIfExists() throws Exception {
        mvc.perform(get("/api/comments")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].email").value("mercedes_tyler@fakegmail.com"))
                .andExpect(jsonPath("$[1].email").value("john_bishop@fakegmail.com"))
                .andExpect(jsonPath("$[2].email").value("tom_wlaschiha@gameofthron.es"));
    }

    @Test
    @Disabled
    @DisplayName("Test @PostMapping for create method for comments")
    public void CreateComment_CheckIfExists() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/comments")
                        .content(asJsonString(new CommentDTO(new ObjectId("https://observablehq.com/@hugodf/mongodb-objectid-generator"),
                                "name", "email", new ObjectId("movieId"),
                                "text", LocalDateTime.now())))
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
    public void UpdateComment_CheckIfUpdatesPersist() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                        .put("/comments/{id}", "id")
                        .content(asJsonString(new CommentDTO(new ObjectId("id"),
                                "name", "email", new ObjectId("movieId"),
                                "text", LocalDateTime.now())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id.timestamp").value(12345678))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id.date").value("date"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("name"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("email"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value("text"));
    }
}
