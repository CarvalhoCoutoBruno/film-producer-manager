package com.texo.filmproducer.service;

import com.texo.filmproducer.exception.MovieAlreadyExistsException;
import com.texo.filmproducer.model.MovieRequest;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MovieServiceTest {

    @Autowired
    private MovieService service;

    private EasyRandom generator;

    @BeforeEach
    void setUp() {
        generator = new EasyRandom(new EasyRandomParameters().seed(1L));
    }

    @Test
    void shouldSaveNewMovie() {
        final var request = generator.nextObject(MovieRequest.class);

        request.setTitle("test movie 1");

        final var saved = service.saveMovie(request);

        Assertions.assertEquals(request.getProducers(), saved.getProducers());
        Assertions.assertEquals(request.getStudios(), saved.getStudios());
        Assertions.assertEquals(request.getYear(), saved.getYear());
        Assertions.assertEquals(request.getTitle(), saved.getTitle());
    }

    @Test
    void shouldFindMovieByProducers() {
        final var request = generator.nextObject(MovieRequest.class);

        request.setTitle("test movie 2");
        request.setProducers("Producers test 1");

        final var saved = service.saveMovie(request);
        final var actual = service.findMovies(request.getProducers()).get(0);

        Assertions.assertEquals(saved.getProducers(), actual.getProducers());
        Assertions.assertEquals(saved.getId(), actual.getId());
        Assertions.assertEquals(saved.getStudios(), actual.getStudios());
        Assertions.assertEquals(saved.getYear(), actual.getYear());
        Assertions.assertEquals(saved.getTitle(), actual.getTitle());
    }

    @Test
    void shouldFindAllMovies() {
        final var actual = service.findMovies(null);

        Assertions.assertTrue(actual.size() > 0);;
    }

    @Test
    void shouldThrowMovieAlreadyExistsException() {
        final var request = generator.nextObject(MovieRequest.class);

        request.setTitle("test movie 3");

        service.saveMovie(request);

        Assertions.assertThrows(MovieAlreadyExistsException.class, ()-> service.saveMovie(request));
    }
}
