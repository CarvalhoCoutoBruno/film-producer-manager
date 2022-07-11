package com.texo.filmproducer.mapper;

import com.texo.filmproducer.domain.MovieEntity;
import com.texo.filmproducer.model.MovieRequest;
import com.texo.filmproducer.model.MovieResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MovieMapper {
    MovieEntity map(MovieRequest source);

    MovieRequest mapToRequest(MovieEntity source);

    MovieResponse mapToResponse(MovieEntity source);

    MovieEntity map(MovieResponse source);

    List<MovieResponse> map(List<MovieEntity> source);
}
