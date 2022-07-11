package com.texo.filmproducer.model;

import lombok.Data;

@Data
public class MovieResponse {

    private Long id;
    private Integer year;
    private String title;
    private String studios;
    private String producers;
    private boolean winner;
}
