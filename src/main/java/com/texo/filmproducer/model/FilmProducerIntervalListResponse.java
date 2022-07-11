package com.texo.filmproducer.model;

import lombok.Data;

import java.util.List;

@Data
public class FilmProducerIntervalListResponse {
    private List<FilmProducerIntervalResponse> min;
    private List<FilmProducerIntervalResponse> max;
}
