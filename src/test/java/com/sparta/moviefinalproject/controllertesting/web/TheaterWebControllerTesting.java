package com.sparta.moviefinalproject.controllertesting.web;

import com.sparta.moviefinalproject.MovieFinalProjectApplication;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = MovieFinalProjectApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
public class TheaterWebControllerTesting {
    @Autowired
    private MockMvc mvc;

    @Test
    @Disabled
    @DisplayName("")
    public void get1() throws Exception {
        mvc.perform(get("/theaters/12345678")) // get request to url
                .andExpect(status().isOk()) // check status code
                .andExpect(content().string(containsString(""))); // check content
    }

    @Test
    @Disabled
    @DisplayName("")
    public void get2() throws Exception {
        mvc.perform(get("/theaters/12345678")) // get request to url
                .andExpect(status().isOk()) // check status code
                .andExpect(content().string(containsString(""))); // check content
    }

    @Test
    @Disabled
    @DisplayName("")
    public void get3() throws Exception {
        mvc.perform(get("/theaters/12345678")) // get request to url
                .andExpect(status().isOk()) // check status code
                .andExpect(content().string(containsString(""))); // check content
    }

    @Test
    @Disabled
    @DisplayName("")
    public void get4() throws Exception {
        mvc.perform(get("/theaters/12345678")) // get request to url
                .andExpect(status().isOk()) // check status code
                .andExpect(content().string(containsString(""))); // check content
    }

    @Test
    @Disabled
    @DisplayName("")
    public void get5() throws Exception {
        mvc.perform(get("/theaters/12345678")) // get request to url
                .andExpect(status().isOk()) // check status code
                .andExpect(content().string(containsString(""))); // check content
    }

}