package com.texo.filmproducer.mapper;

import com.texo.filmproducer.domain.MovieEntity;
import com.texo.filmproducer.model.MovieResponse;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class MovieMapperTest {
    private final MovieMapper mapper = Mappers.getMapper(MovieMapper.class);

    private EasyRandom generator;

    @BeforeEach
    void setup() {
        generator = new EasyRandom(new EasyRandomParameters().seed(1L));
    }

    @Test
    void shouldMapToEntity() {
        final var expected = generator.nextObject(MovieEntity.class);

        expected.setId(null);

        final var source = mapper.mapToRequest(expected);
        final var actual = mapper.map(source);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldMapToResponse() {
        final var expected = generator.nextObject(MovieResponse.class);
        final var source = mapper.map(expected);
        final var actual = mapper.mapToResponse(source);

        Assertions.assertEquals(expected, actual);
    }
}
