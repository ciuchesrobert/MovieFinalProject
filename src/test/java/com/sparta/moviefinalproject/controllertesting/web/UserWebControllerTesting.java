package com.sparta.moviefinalproject.controllertesting.web;

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

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = MovieFinalProjectApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
public class UserWebControllerTesting {
    @Autowired
    private MockMvc mvc;

    ObjectMapper objectMapper = new ObjectMapper();
    UserDTO dto = new UserDTO();

    @Test
    @Disabled
    @DisplayName("")
    public void get1() throws Exception {
        mvc.perform(get("/users/12345678")) // get request to url
                .andExpect(status().isOk()) // check status code
                .andExpect(content().string(containsString(""))); // check content
    }

    @Test
    @Disabled
    @DisplayName("")
    public void get2() throws Exception {
        mvc.perform(get("/users/12345678")) // get request to url
                .andExpect(status().isOk()) // check status code
                .andExpect(content().string(containsString(""))); // check content
    }

    @Test
    @Disabled
    @DisplayName("")
    public void get3() throws Exception {
        mvc.perform(get("/users/12345678")) // get request to url
                .andExpect(status().isOk()) // check status code
                .andExpect(content().string(containsString(""))); // check content
    }

    @Test
    @Disabled
    @DisplayName("")
    public void get4() throws Exception {
        mvc.perform(get("/users/12345678")) // get request to url
                .andExpect(status().isOk()) // check status code
                .andExpect(content().string(containsString(""))); // check content
    }

    @Test
    @Disabled
    @DisplayName("")
    public void get5() throws Exception {
        mvc.perform(get("/users/12345678")) // get request to url
                .andExpect(status().isOk()) // check status code
                .andExpect(content().string(containsString(""))); // check content
    }

    @Test
    @Disabled
    @DisplayName("")
    public void post1() throws Exception {
        String requestBody = objectMapper.writeValueAsString(dto);
        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON).content(requestBody)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();

    }





}
