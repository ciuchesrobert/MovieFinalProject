package com.sparta.moviefinalproject.controllertesting.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.moviefinalproject.MovieFinalProjectApplication;
import com.sparta.moviefinalproject.dtos.UserDTO;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = MovieFinalProjectApplication.class)
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(
        locations = "classpath:application.properties")
public class UserWebControllerTesting {
    @Autowired
    private MockMvc mvc;

    ObjectMapper objectMapper = new ObjectMapper();

    UserDTO dto = new UserDTO();

    @Test
    @DisplayName("Test @GetMapping for find method")
    @WithMockUser(roles = "ADMIN")
    public void findByIdWebTest() throws Exception {
        mvc.perform(get("/users/basic/searchById/59b99db4cfa9a34dcd7885b7")) // get request to url
                .andExpect(status().isOk()) // check status code
                .andExpect(content().string(containsString("59b99db4cfa9a34dcd7885b7"))); // check content
    }

    @Test
    @DisplayName("Test @GetMapping for find method to fail")
    @WithMockUser(roles = "ADMIN")
    public void findByIdWebTestFail() throws Exception {
        mvc.perform(get("/users/basic/searchById/59b99wb4cfa9a344cd7885b7")) // get request to url
                .andExpect(status().is4xxClientError()); // check content
    }

    @Test
    @DisplayName("Test @GetMapping for findALL method")
    @WithMockUser(roles = "ADMIN")
    public void findAllWebTest() throws Exception {
        mvc.perform(get("/users/basic/10")) // get request to url
                .andExpect(status().isOk()) // check status code
                .andExpect(content().string(containsString("<td>59b99de5cfa9a34dcd78861a</td>"))); // check content
    }

    @Test
    @DisplayName("Test @PostMapping for create method sending null bode to fail")
    @WithMockUser(roles = "ADMIN")
    public void createWebTestFail() throws Exception {
        mvc.perform(post("/users/admin/create")
                        .contentType("application/json"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("Test @PostMapping for create method")
    @WithMockUser(roles = "ADMIN")
    public void createWebTest() throws Exception {
        mvc.perform(get("/users/admin/create")
                        .content(asJsonString((new UserDTO(
                                new ObjectId("63c9e2dc294411a4c69aca2e"), "rob@gmail.com", "rob", "1234"
                        ))))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test @PutMapping for update method")
    @WithMockUser(roles = "ADMIN")
    public void updateWebTest() throws Exception {
        mvc.perform(get("/users/admin/update")
                        .content(asJsonString((new UserDTO(
                                new ObjectId("63c9e2dc294411a4c69aca2e"), "robss@gmail.com", "rob", "1234"
                        ))))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test @deleteMapping for delete method")
    @WithMockUser(roles = "ADMIN")
    public void deleteWebTest() throws Exception {
        mvc.perform(get("/users/admin/delete")
                        .content(asJsonString((new UserDTO(
                                new ObjectId("63c9e2dc294411a4c69aca2e"), "robss@gmail.com", "rob", "1234"
                        ))))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }


    public String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}
