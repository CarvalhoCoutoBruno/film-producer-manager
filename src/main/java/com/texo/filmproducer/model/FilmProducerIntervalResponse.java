package com.texo.filmproducer.model;

import lombok.Data;

@Data
public class FilmProducerIntervalResponse implements Comparable<FilmProducerIntervalResponse> {
    private String producer;
    private Integer interval;
    private Integer previousWin;
    private Integer followingWin;

    @Override
    public int compareTo(FilmProducerIntervalResponse o) {
        return this.interval.compareTo(o.getInterval());
    }
}
