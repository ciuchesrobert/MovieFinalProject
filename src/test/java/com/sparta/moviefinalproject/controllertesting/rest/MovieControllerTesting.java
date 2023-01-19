package com.sparta.moviefinalproject.controllertesting.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.sparta.moviefinalproject.MovieFinalProjectApplication;
import com.sparta.moviefinalproject.dtos.MovieDTO;
import com.sparta.moviefinalproject.dtos.subdtos.*;
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
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = MovieFinalProjectApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
public class MovieControllerTesting {
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
    @DisplayName("Testing @GetMapping for findById method on movie with ID of 573a1390f29313caabcd4135      WORKS")
    public void FindMovieById_SuccessIfExists() throws Exception{
        String id = "573a1390f29313caabcd4135";
        // ADD EXPECTED VALUES
        mvc.perform(get("/api/movies/" + id + "?apikey=DSRMSR5jM2UkV5cW4XZNraP2u5ZNNEzV6TU3n6pa9HpiHC2tW0Dzr7ehYMtDPt1N")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Blacksmith Scene"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.plot").value("Three men hammer on an anvil and pass a bottle of beer around."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value("1895"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    @DisplayName("Test @GetMapping for findAll method for movies            WORKS")
    public void FindAllMovies_SuccessIfExists() throws Exception{
        mvc.perform(get("/api/movies?apikey=DSRMSR5jM2UkV5cW4XZNraP2u5ZNNEzV6TU3n6pa9HpiHC2tW0Dzr7ehYMtDPt1N")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("Blacksmith Scene"))
                .andExpect(jsonPath("$[1].title").value("The Great Train Robbery"))
                .andExpect(jsonPath("$[2].title").value("The Land Beyond the Sunset"));
    }

    @Test
//    @Disabled
    @DisplayName("Test @PostMapping for create method for comments                WORKS")
    public void CreateMovie_CheckIfExists() throws Exception {
        String id = "63c9624ac73610a60c93e132";
        LocalDateTime testDate = LocalDateTime.parse("2015-08-26 00:03:50.133000000", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
        MovieDTO movieDTO = new MovieDTO(new ObjectId(id),
                new AwardDTO(1, 0, "1 win."),
                new String[]{"William K.L. Dickson"},
                "b",
                null,
                0,
                "Three men hammer on an anvil and pass a bottle of beer around.",
                "Yash Test 19/01/23",
                new String[]{"a, b"},
                new String[]{"Short"},
                testDate,
                "a",
                new TomatoDTO("a",
                        new CriticDTO(5, 4, 3.0), testDate, 4,
                        testDate, "a", 5,
                        new ViewerDTO(32, 184, 5.0), "a"),
                "1895",
                new ImdbDTO(2, 5.0, 1189),
                "UNRATED",
                testDate,
                new String[]{"Charles Kayser, John Ott"},
                1,
                new String[]{"USA"},
                "movie"
        );
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/movies?apikey=DSRMSR5jM2UkV5cW4XZNraP2u5ZNNEzV6TU3n6pa9HpiHC2tW0Dzr7ehYMtDPt1N")
                        .content(asJsonString(movieDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(movieDTO.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.plot").value(movieDTO.getPlot()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value(movieDTO.getYear()));
    }

    @Test
//    @Disabled
    @DisplayName("Test @PutMapping for update method for comments                WORKS")
    public void UpdateOrReplaceMovie_CheckIfUpdatesPersist() throws Exception {
        String id = "63c9624a8b7647388504fb24";
        LocalDateTime testDate = LocalDateTime.parse("2015-08-26 00:03:50.133000000", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
        MovieDTO movieDTO = new MovieDTO(new ObjectId(id),
                new AwardDTO(1, 0, "1 win."),
                new String[]{"William K.L. Dickson"},
                "b",
                null,
                0,
                "Three men hammer on an anvil and pass a bottle of beer around.",
                "Yash Test 19/01/23 PUT METHOD UPDATED 2ND TIME",
                new String[]{"a, b"},
                new String[]{"Short"},
                testDate,
                "a",
                new TomatoDTO("a",
                        new CriticDTO(5, 4, 3.0), testDate, 4,
                        testDate, "a", 5,
                        new ViewerDTO(32, 184, 5.0), "a"),
                "1895",
                new ImdbDTO(2, 5.0, 1189),
                "UNRATED",
                testDate,
                new String[]{"Charles Kayser, John Ott"},
                1,
                new String[]{"USA"},
                "movie"
        );
        mvc.perform(MockMvcRequestBuilders
                        .put("/api/movies/" + id + "?apikey=DSRMSR5jM2UkV5cW4XZNraP2u5ZNNEzV6TU3n6pa9HpiHC2tW0Dzr7ehYMtDPt1N")
                        .content(asJsonString(movieDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(movieDTO.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.plot").value(movieDTO.getPlot()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value(movieDTO.getYear()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

//    @Test
//    @Disabled
//    public void UpdateMovie_CheckIfUpdatesPersist() throws Exception {
//        PATCH REQUEST IF WE DECIDE TO ADD IT
//        String id = "";
//        mvc.perform( MockMvcRequestBuilders
//                        .patch("/api/movies/" + id)
//                        .content(asJsonString(new MovieDTO()))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(""))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.plot").value(""))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value(""))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
//    }

    @Test
    public void FindMoviesWithTitleContains_IfFoundReturnSuccess() throws Exception {
        String title = "Blacksmith Scene";
        mvc.perform(get("/api/movies/search?title=" + title)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].plot").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].year").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists());
    }
}
