package com.texo.filmproducer.repository;

import com.texo.filmproducer.domain.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
}
