package com.texo.filmproducer.repository;

import com.texo.filmproducer.domain.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    List<MovieEntity> findByProducersContaining(final String producers);
    Optional<MovieEntity> findByTitle(final String title);
}
