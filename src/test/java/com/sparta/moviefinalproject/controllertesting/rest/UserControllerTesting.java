package com.sparta.moviefinalproject.controllertesting.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.moviefinalproject.MovieFinalProjectApplication;
import com.sparta.moviefinalproject.entities.User;
import org.bson.types.ObjectId;
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
        String id = "59b99db4cfa9a34dcd7885b6";
        mvc.perform(get("/api/users/" + id)
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
    @DisplayName("Test @PostMapping for create method for users")
    public void CreateUser_CheckIfExists() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/users")
                        .content(asJsonString(new User(
                                new ObjectId("63c7cf248b1a8a9b4bf831a0"), "yash2@gmail.com", "yash2", "yash2"
                        )))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("yash2@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("yash2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("yash2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    @DisplayName("Test @PutMapping for update method for users")
    public void UpdateUser_CheckIfUpdatesPersist() throws Exception {
        String id = "59b99db6cfa9a34dcd7885bc";
        mvc.perform( MockMvcRequestBuilders
                        .put("/api/users/" + id)
                        .content(asJsonString(new User(
                                new ObjectId("59b99db6cfa9a34dcd7885bc"), "iain_glen@gameofthron.es",
                                "Jorah Mormont",
                                "$2b$12$K8bKkwnpkrjsBPzASZxO/.yj7d9kvupiVtO6JA3Xl106AKXr3pXFK"
                        )))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id.timestamp").value(1505336758))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id.date").value("2017-09-13T21:05:58.000+00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("iain_glen@gameofthron.es"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Jorah Mormont"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("$2b$12$K8bKkwnpkrjsBPzASZxO/.yj7d9kvupiVtO6JA3Xl106AKXr3pXFK"));
    }

}