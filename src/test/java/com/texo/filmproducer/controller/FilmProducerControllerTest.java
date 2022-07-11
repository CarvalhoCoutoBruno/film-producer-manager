package com.texo.filmproducer.controller;

import com.texo.filmproducer.model.FilmProducerIntervalListResponse;
import com.texo.filmproducer.repository.MovieRepository;
import com.texo.filmproducer.service.FilmProducerService;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@WebMvcTest(FilmProducerController.class)
class FilmProducerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmProducerService filmProducerService;

    @MockBean
    private MovieRepository movieRepository;

    private EasyRandom generator;

    @BeforeEach
    void setUp() {
        generator = new EasyRandom(new EasyRandomParameters().seed(1L));
    }

    @Test
    void shouldReturnFilmProducerPrizeIntervals() throws Exception {
        final var response = generator.nextObject(FilmProducerIntervalListResponse.class);

        when(filmProducerService.findProducersPrizeIntervals())
                .thenReturn(response);

        mockMvc.perform(get("/v1/film-producers/prize-intervals"))
                .andExpect(status().isAccepted())
                .andDo(print())
                .andExpect(jsonPath("$.min[0].producer", is(response.getMin().get(0).getProducer())))
                .andExpect(jsonPath("$.max[0].producer", is(response.getMax().get(0).getProducer())));
    }
}