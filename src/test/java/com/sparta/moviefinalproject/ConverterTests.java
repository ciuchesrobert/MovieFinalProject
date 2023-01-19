package com.sparta.moviefinalproject;

import com.sparta.moviefinalproject.converters.CommentConverter;
import com.sparta.moviefinalproject.converters.MovieConverter;
import com.sparta.moviefinalproject.converters.TheaterConverter;
import com.sparta.moviefinalproject.converters.UserConverter;
import com.sparta.moviefinalproject.converters.subtypes.ImdbConverter;
import com.sparta.moviefinalproject.dtos.CommentDTO;
import com.sparta.moviefinalproject.dtos.MovieDTO;
import com.sparta.moviefinalproject.dtos.TheaterDTO;
import com.sparta.moviefinalproject.dtos.UserDTO;
import com.sparta.moviefinalproject.dtos.subdtos.*;
import com.sparta.moviefinalproject.entities.Comment;
import com.sparta.moviefinalproject.entities.Movie;
import com.sparta.moviefinalproject.entities.Theater;
import com.sparta.moviefinalproject.entities.User;
import com.sparta.moviefinalproject.entities.subentities.*;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class ConverterTests {

    @Test
    @DisplayName("Switch a User Entity to DTO")
    public void switchAUserFromEntityToDto()
    {
        User userToTransfer = new User(new ObjectId("59b99db4cfa9a34dcd7885b6"), "test@hotmail.com", "bob", "password");
        UserDTO userDto = new UserConverter().entityToDto(userToTransfer);
        Assertions.assertTrue(userToTransfer.entityEqualsDto(userDto));
    }

    @Test
    @DisplayName("Switch a User DTO to Entity")
    public void switchAUserFromDtoToEntity()
    {
        UserDTO userDtoToTransfer = new UserDTO(new ObjectId("59b99db4cfa9a34dcd7885b6"), "test@hotmail.com", "bob", "password");
        User user = new UserConverter().dtoToEntity(userDtoToTransfer);
        Assertions.assertTrue(userDtoToTransfer.dtoEqualsEntity(user));
    }

    @Test
    @DisplayName("Switch a Movie Entity to DTO")
    public void switchAMovieFromEntityToDto()
    {
        String[] directors = {"Emre", "David"};
        String[] languages = {"English", "Turkish"};
        String[] writers = {"Emre", "David"};
        String[] genres = {"yes", "horror"};
        String[] cast = {"Emre", "David"};
        String[] countries = {"England", "Turkey"};
        Movie fakeMovie = new Movie(new ObjectId("573a1390f29313caabcd4323"),
                new Award(1,1,"yes"),
                directors,
                "this is a fake movie where....",
                languages,
                2,
                "Great movie",
                "Best movie ever",
                writers,
                genres,
                LocalDateTime.now(),
                "this a poster",
                new Tomato("Consensus", new Critic(10,10,10.1), LocalDateTime.now(), 2, LocalDateTime.now(), "production", 23, new Viewer(10,10,10.1), "lol.com"),
                "2023",
                new Imdb(10,10.1,10),
                "rated",
                LocalDateTime.now(),
                cast,
                120,
                countries,
                "type"
        );
        MovieDTO movieDto = new MovieConverter().entityToDto(fakeMovie);
        Assertions.assertTrue(fakeMovie.entityEqualsDto(movieDto));
    }
    @Test
    @DisplayName("Switch a Movie DTO to Entity")
    public void switchAMovieFromDtoToEntity()
    {
        String[] directors = {"Emre", "David"};
        String[] languages = {"English", "Turkish"};
        String[] writers = {"Emre", "David"};
        String[] genres = {"yes", "horror"};
        String[] cast = {"Emre", "David"};
        String[] countries = {"England", "Turkey"};
        MovieDTO fakeMovie = new MovieDTO(new ObjectId("573a1390f29313caabcd4323"),
                new AwardDTO(1,1,"yes"),
                directors,
                "this is a fake movie where....",
                languages,
                2,
                "Great movie",
                "Best movie ever",
                writers,
                genres,
                LocalDateTime.now(),
                "this a poster",
                new TomatoDTO("Consensus", new CriticDTO(10,10,10.1), LocalDateTime.now(), 2, LocalDateTime.now(), "production", 23, new ViewerDTO(10,10,10.1), "lol.com"),
                "2023",
                new ImdbDTO(10,10.1,10),
                "rated",
                LocalDateTime.now(),
                cast,
                120,
                countries,
                "type"
        );
        Movie movie = new MovieConverter().dtoToEntity(fakeMovie);
        Assertions.assertTrue(fakeMovie.dtoEqualsEntity(movie));
    }
    @Test
    @DisplayName("Switch a Theater Entity to DTO")
    public void switchATheaterFromEntityToDto()
    {
        Double[] geoArr = {1.1,2.1,3.1};
        Theater theater = new Theater(new ObjectId("59a47286cfa9a3a73e51e72c"), new Location(new Address("London", "London", "street", "zipcode"), new Geo("geoArr", geoArr)), "59a47286cfa9a3a73e51e72c");
        TheaterDTO theaterDto = new TheaterConverter().entityToDto(theater);
        Assertions.assertTrue(theater.entityEqualsDto(theaterDto));
    }
    @Test
    @DisplayName("Switch a Theater DTO to Entity")
    public void switchATheaterFromDtoToEntity()
    {
        Double[] geoArr = {1.1,2.1,3.1};
        TheaterDTO theaterDto = new TheaterDTO(new ObjectId("59a47286cfa9a3a73e51e72c"), new Location(new Address("London", "London", "street", "zipcode"), new Geo("geoArr", geoArr)), "59a47286cfa9a3a73e51e72c");
        Theater theater = new TheaterConverter().dtoToEntity(theaterDto);
        System.out.println(theaterDto);
        System.out.println(theater);
        Assertions.assertTrue(theaterDto.dtoEqualsEntity(theater));
    }

    @Test
    @DisplayName("Switch a Comment Entity to DTO")
    public void switchACommentFromEntityToDto()
    {
        Comment comment = new Comment(new ObjectId("5a9427648b0beebeb69579e7"),
                "name",
                "test@email.com",
                new ObjectId("5a9427648b0beebeb69579e7"),
                "text",
                LocalDateTime.now());
        CommentDTO commentDto = new CommentConverter().entityToDto(comment);
        Assertions.assertTrue(comment.entityEqualsDto(commentDto));
    }
    @Test
    @DisplayName("Switch a Comment DTO to Entity")
    public void switchACommentFromDtoToEntity()
    {
        CommentDTO commentDto = new CommentDTO(new ObjectId("5a9427648b0beebeb69579e7"),
                "name",
                "test@email.com",
                new ObjectId("5a9427648b0beebeb69579e7"),
                "text",
                LocalDateTime.now());
        Comment comment = new CommentConverter().dtoToEntity(commentDto);
        Assertions.assertTrue(commentDto.dtoEqualsEntity(comment));
    }
}
