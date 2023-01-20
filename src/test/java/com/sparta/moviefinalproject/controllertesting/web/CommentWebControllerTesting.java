package com.sparta.moviefinalproject.controllertesting.web;

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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
public class CommentWebControllerTesting {
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
    @Disabled
    @WithMockUser(roles = "BASIC")
    @DisplayName("Search for comment by ID as basic user, return success if present")
    public void displayCommentAsBasic_SuccessIfPresent() throws Exception {
        String id = "573a1390f29313caabcd5a93";
        mvc.perform(get("/comments/basic/search/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("573a1390f29313caabcd5a93")))
                .andExpect(content().string(containsString("")));
    }
    @Test
    @Disabled
    @WithMockUser(roles = "BASIC")
    @DisplayName("Search all comments as basic user, return success if present")
    public void findAllCommentsAsBasic_SuccessIfPresent() throws Exception {
        String pageNumber = "0";
        mvc.perform(get("/comments/basic/all/" + pageNumber)) // get request to url
                .andExpect(status().isOk()) // check status code
                .andExpect(content().string(containsString("5a9427648b0beebeb69579e7")))
                .andExpect(content().string(containsString("5a9427648b0beebeb69579f5")))
                .andExpect(content().string(containsString("5a9427648b0beebeb6957a21")));
    }

    @Test
    @Disabled
    @WithMockUser(roles = "ADMIN")
    @DisplayName("Search for comment by ID as admin user, return success if present")
    public void displayCommentByIdAsAdmin_SuccessIfPresent() throws Exception {
        String id = "573a1390f29313caabcd5a93";
        mvc.perform(get("/comments/admin/search/" + id))
                .andExpect(status().isOk()) // check status code
                .andExpect(content().string(containsString("573a1390f29313caabcd5a93")))
                .andExpect(content().string(containsString("")));
    }
    @Test
    @Disabled
    @WithMockUser(roles = "ADMIN")
    @DisplayName("Search all comments as admin user, return success if present")
    public void findAllCommentsAsAdmin_SuccessIfPresent() throws Exception {
        String pageNumber = "0";
        mvc.perform(get("/comments/admin/all/" + pageNumber)) // get request to url
                .andExpect(status().isOk()) // check status code
                .andExpect(content().string(containsString("5a9427648b0beebeb69579e7")))
                .andExpect(content().string(containsString("5a9427648b0beebeb69579f5")))
                .andExpect(content().string(containsString("5a9427648b0beebeb6957a21")));
    }

    @Test
    @Disabled
    @WithMockUser(roles = "BASIC")
    @DisplayName("Create new comment as basic, return success if present")
    public void CreateNewCommentAsBasic_200IfSuccess() throws Exception {
        LocalDateTime testDate = LocalDateTime.parse("2015-08-26 00:03:50.133000000", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
        CommentDTO comment = new CommentDTO(
                new ObjectId("63c98e8fa0b54e883aac7518"),
                "Testname",
                "Testemail@gmail.com",
                new ObjectId("573a1390f29313caabcd5b9a"),
                "Testcomment",
                testDate
        );
        mvc.perform(post("/comments/admin/create")
                        .contentType("application/json")
                        .content(asJsonString(comment)))
                .andExpect(status().isOk());
    }
    @Test
    @Disabled
    @WithMockUser(roles = "ADMIN")
    @DisplayName("Create new comment as admin, return success if present")
    public void CreateNewCommentAsAdmin_200IfSuccess() throws Exception {
        LocalDateTime testDate = LocalDateTime.parse("2015-08-26 00:03:50.133000000", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
        CommentDTO comment = new CommentDTO(
                new ObjectId("63c98e8fa0b54e883aac7518"),
                "Testname",
                "Testemail@gmail.com",
                new ObjectId("573a1390f29313caabcd5b9a"),
                "Testcomment",
                testDate
        );
        mvc.perform(get("/comments/admin/create")
                .contentType("application/json")
                        .content(asJsonString(comment)))
        .andExpect(status().isOk());
    }

    @Test
    @Disabled
    @WithMockUser(roles = "ADMIN")
    @DisplayName("Delete comment by ID as admin, return 204 if success")
    public void DeleteCommentByIdAsAdmin_ReturnNotFoundIfSuccess() throws Exception {
        String id = "commentId";
        mvc.perform(post("/comments/admin/delete/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    @Disabled
    @WithMockUser(roles = "BASIC")
    @DisplayName("Delete comment by ID as basic, return 403 forbidden")
    public void DeleteCommentByIdAsBasic_Return403Forbidden() throws Exception {
        String id = "commentId";
        mvc.perform(post("/comments/basic/delete/" + id))
                .andExpect(status().isForbidden());
    }

    @Test
    @Disabled
    @WithMockUser(roles = "ADMIN")
    @DisplayName("Update comment as admin, return 200 if success")
    public void UpdateCommentAsAdmin_Return200IfSuccess() throws Exception {
        String id = "63c98e8fa0b54e883aac7518";
        LocalDateTime testDate = LocalDateTime.parse("2015-08-26 00:03:50.133000000", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
        CommentDTO comment = new CommentDTO(
                new ObjectId(id),
                "Testname",
                "Testemail@gmail.com",
                new ObjectId("movieId"),
                "Testcomment",
                testDate
        );
        mvc.perform(post("/comments/admin/update/" + id)
                .contentType("application/json")
                        .content(asJsonString(comment)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Testname")))
                .andExpect(content().string(containsString("Testemail@gmail.com")))
                .andExpect(content().string(containsString("Testcomment")));
    }

    @Test
    @Disabled
    @WithMockUser(roles = "BASIC")
    @DisplayName("Update comment as admin, return 403 forbidden")
    public void UpdateCommentAsBasic_Return403Forbidden() throws Exception {
        String id = "63c98e8fa0b54e883aac7518";
        LocalDateTime testDate = LocalDateTime.parse("2015-08-26 00:03:50.133000000", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
        CommentDTO comment = new CommentDTO(
                new ObjectId(id),
                "Testname",
                "Testemail@gmail.com",
                new ObjectId("movieId"),
                "Testcomment",
                testDate
        );
        mvc.perform(post("/comments/admin/update/" + id)
                        .contentType("application/json")
                        .content(asJsonString(comment)))
                .andExpect(status().isForbidden());
    }

}