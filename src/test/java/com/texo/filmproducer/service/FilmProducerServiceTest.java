package com.texo.filmproducer.service;

import com.texo.filmproducer.domain.MovieEntity;
import com.texo.filmproducer.repository.MovieRepository;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Collectors;

@SpringBootTest
class FilmProducerServiceTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private FilmProducerService service;

    private EasyRandom generator;

    @BeforeEach
    void setUp() {
        generator = new EasyRandom(new EasyRandomParameters().seed(1L));
    }

    @Test
    void shouldGetProducersMaxAndMinIntervals() {
        final var movieEntityList = generator.objects(MovieEntity.class, 4).collect(Collectors.toList());

        final var producerMaxName = "Matthew Vaughn";
        final var producerMinName = "Joel Silver";
        final var expectedMaxInterval = 13;
        final var expectedMinInterval = 1;

        final var response = service.findProducersPrizeIntervals();

        final var expectedMinItem = response.getMin().get(0);

        final var expectedMaxItem = response.getMax().get(0);

        Assertions.assertEquals(producerMaxName, expectedMaxItem.getProducer());
        Assertions.assertEquals(expectedMaxInterval, expectedMaxItem.getInterval());
        Assertions.assertEquals(producerMinName, expectedMinItem.getProducer());
        Assertions.assertEquals(expectedMinInterval, expectedMinItem.getInterval());
    }
}
