package com.sparta.moviefinalproject.controllertesting.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
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
            return new ObjectMapper().registerModule(new ParameterNamesModule())
                    .registerModule(new Jdk8Module())
                    .registerModule(new JavaTimeModule()).writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Testing @GetMapping for findById method on comments with ID of 5a9427648b0beebeb69579e7")
    public void FindCommentById_SuccessIfExists() throws Exception {
        String id = "5a9427648b0beebeb69579e7";
        mvc.perform(get("/api/comments/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value("mercedes_tyler@fakegmail.com"))
                .andExpect(jsonPath("$.text").value("Eius veritatis vero facilis quaerat fuga temporibus. " +
                        "Praesentium expedita sequi repellat id. Corporis minima enim ex. " +
                        "Provident fugit nisi dignissimos nulla nam ipsum aliquam."))
                .andExpect(jsonPath("$.id").exists());
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
//    @Disabled
    @DisplayName("Test @PostMapping for create method for comments")
    public void CreateComment_CheckIfExists() throws Exception {
        String id = "63c96eb07490c61b87557684";
        mvc.perform(MockMvcRequestBuilders
                        .post("/comments")
                        .content(asJsonString(new CommentDTO(new ObjectId(id),
                                "test", "test@test.com", new ObjectId("63c948d27439c01a0476a846"),
                                "test", LocalDateTime.now())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("test@test.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value("test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
//    @Disabled
    public void UpdateComment_CheckIfUpdatesPersist() throws Exception {
        String id = "CREATE_COMMENT_THEN_PUT_ID_HERE";
        mvc.perform( MockMvcRequestBuilders
                        .put("/comments/" + id)
                        .content(asJsonString(new CommentDTO(new ObjectId(id),
                                "testupdated", "testupdated@test.com", new ObjectId("PUT_MOVIE_ID_HERE"),
                                "testupdated", LocalDateTime.now())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("testupdated"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("email"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value("testupdated"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }
}
