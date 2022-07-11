package com.texo.filmproducer.model;

import lombok.Data;
import lombok.Getter;

@Data
public class ProducerResponse implements Comparable<ProducerResponse> {
    private String producer;
    private Integer interval;
    private Integer previousWin;
    private Integer followingWin;

    @Override
    public int compareTo(ProducerResponse o) {
        return this.interval.compareTo(o.getInterval());
    }
}
