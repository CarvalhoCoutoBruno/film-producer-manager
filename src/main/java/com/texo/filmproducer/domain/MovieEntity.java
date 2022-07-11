package com.texo.filmproducer.domain;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "movie")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CsvBindByPosition(position = 0)
    private Integer year;

    @CsvBindByPosition(position = 1)
    private String title;

    @CsvBindByPosition(position = 2)
    private String studios;

    @CsvBindByPosition(position = 3)
    private String producers;

    @CsvBindByPosition(position = 4)
    private boolean winner;
}

