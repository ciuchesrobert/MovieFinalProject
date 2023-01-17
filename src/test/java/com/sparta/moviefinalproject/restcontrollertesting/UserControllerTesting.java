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
public class UserControllerTesting {
    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Test get method for user with ID of 59b99db4cfa9a34dcd7885b6")
    public void FindUserById_IfExistsReturnSuccess() throws Exception {
        mvc.perform(get("/api/users/59b99db4cfa9a34dcd7885b6")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id.timestamp").exists())
                .andExpect(jsonPath("$.email").value("sean_bean@gameofthron.es"))
                .andExpect(jsonPath("$.password")
                        .value("$2b$12$UREFwsRUoyF0CRqGNK0LzO0HM/jLhgUCNNIJ9RJAqMUQ74crlJ1Vu"));
    }

    @Test
    @DisplayName("Test get method for all users")
    public void FindAllUsers() throws Exception {
        mvc.perform(get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].email").value("sean_bean@gameofthron.es"))
                .andExpect(jsonPath("$[1].email").value("mark_addy@gameofthron.es"))
                .andExpect(jsonPath("$[2].email").value("nikolaj_coster-waldau@gameofthron.es"));
    }
}