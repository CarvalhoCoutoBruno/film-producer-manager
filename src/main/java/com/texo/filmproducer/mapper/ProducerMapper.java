package com.texo.filmproducer.mapper;

import com.texo.filmproducer.domain.MovieEntity;
import com.texo.filmproducer.model.ProducerResponse;
import org.mapstruct.Mapper;

@Mapper
public interface ProducerMapper {

    ProducerResponse map (MovieEntity source);
}
