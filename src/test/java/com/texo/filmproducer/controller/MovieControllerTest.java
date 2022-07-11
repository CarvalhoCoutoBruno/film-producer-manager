package com.texo.filmproducer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.texo.filmproducer.domain.MovieEntity;
import com.texo.filmproducer.model.MovieRequest;
import com.texo.filmproducer.model.MovieResponse;
import com.texo.filmproducer.repository.MovieRepository;
import com.texo.filmproducer.service.MovieService;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @MockBean
    private MovieRepository movieRepository;

    private EasyRandom generator;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        generator = new EasyRandom(new EasyRandomParameters().seed(1L));
        objectMapper = new ObjectMapper();
    }

    @Test
    void shouldReturnMovies() throws Exception {
        final var response = generator.objects(MovieResponse.class, 1).collect(Collectors.toList());

        when(movieService.findMovies(any()))
                .thenReturn(response);

        mockMvc.perform(get("/v1/movies"))
                .andExpect(status().isAccepted())
                .andDo(print())
                .andExpect(jsonPath("$[0].id", is(response.get(0).getId())))
                .andExpect(jsonPath("$[0].year", is(response.get(0).getYear())))
                .andExpect(jsonPath("$[0].title", is(response.get(0).getTitle())))
                .andExpect(jsonPath("$[0].studios", is(response.get(0).getStudios())))
                .andExpect(jsonPath("$[0].producers", is(response.get(0).getProducers())))
                .andExpect(jsonPath("$[0].winner", is(response.get(0).isWinner())));
    }

    @Test
    void shouldSaveMovie() throws Exception {
        final var request = generator.nextObject(MovieRequest.class);

        request.setYear(2021);

        when(movieService.saveMovie(any()))
                .thenReturn(new MovieEntity());

        mockMvc.perform(post("/v1/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void shouldDeleteMovie() throws Exception {
        doNothing().when(movieService).deleteMovie(any());

        mockMvc.perform(delete("/v1/movies/12345"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
