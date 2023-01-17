package com.sparta.moviefinalproject.controllertesting.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.moviefinalproject.MovieFinalProjectApplication;
import com.sparta.moviefinalproject.dtos.UserDTO;
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
public class UserControllerTesting {
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
    @DisplayName("Testing @GetMapping for findById method on users with ID of 59b99db4cfa9a34dcd7885b6")
    public void FindUserById_IfExistsReturnSuccess() throws Exception {
        mvc.perform(get("/api/users/{id}","59b99db4cfa9a34dcd7885b6")
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
    @DisplayName("Test @GetMapping for findAll method for users")
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

    @Test
    @Disabled
    @DisplayName("Test @PostMapping for create method for comments")
    public void CreateUser_CheckIfExists() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/comments")
                        .content(asJsonString(new UserDTO()))
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
    public void UpdateUser_CheckIfUpdatesPersist() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                        .put("/comments/{id}", "no idea what to put for id")
                        .content(asJsonString(new UserDTO()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("yash2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("yash2@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value("text2"));
    }
}