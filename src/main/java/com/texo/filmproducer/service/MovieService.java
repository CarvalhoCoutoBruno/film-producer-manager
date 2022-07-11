package com.texo.filmproducer.service;

import com.texo.filmproducer.domain.MovieEntity;
import com.texo.filmproducer.exception.MovieAlreadyExistsException;
import com.texo.filmproducer.exception.MovieNotFoundException;
import com.texo.filmproducer.mapper.MovieMapper;
import com.texo.filmproducer.model.MovieRequest;
import com.texo.filmproducer.model.MovieResponse;
import com.texo.filmproducer.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Transactional
    public MovieEntity saveMovie(final MovieRequest request) {
        final var existingEntity = movieRepository.findByTitle(request.getTitle());

        if (existingEntity.isPresent()) {
            throw new MovieAlreadyExistsException();
        }

        final var entity = movieMapper.map(request);

        return movieRepository.save(entity);
    }

    @Transactional
    public void deleteMovie(final Long id) {
        final var entity = movieRepository.findById(id)
                .orElseThrow(MovieNotFoundException::new);

        movieRepository.delete(entity);
    }

    public List<MovieResponse> findMovies(final String producers) {
        List<MovieEntity> movieEntityList;

        if (StringUtils.isBlank(producers)) {
            movieEntityList = movieRepository.findAll();
        } else {
            movieEntityList = movieRepository.findByProducersContaining(producers);
        }

        return movieMapper.map(movieEntityList);
    }
}
