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

    @Test
    @DisplayName("Test get method for comment with ID of 5a9427648b0beebeb69579e7")
    public void FindCommentById_SuccessIfExists() throws Exception{
        mvc.perform(get("/api/comments/5a9427648b0beebeb69579e7")
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
    @DisplayName("Test get method for all comments")
    public void FindAllComments_SuccessIfExists() throws Exception{
        mvc.perform(get("/api/comments")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].email").value("mercedes_tyler@fakegmail.com"))
                .andExpect(jsonPath("$[1].email").value("john_bishop@fakegmail.com"))
                .andExpect(jsonPath("$[2].email").value("tom_wlaschiha@gameofthron.es"));
    }
}
