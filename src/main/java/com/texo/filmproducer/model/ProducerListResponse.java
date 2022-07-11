package com.texo.filmproducer.model;

import lombok.Data;

import java.util.List;

@Data
public class ProducerListResponse {
    private List<ProducerResponse> min;
    private List<ProducerResponse> max;
}
