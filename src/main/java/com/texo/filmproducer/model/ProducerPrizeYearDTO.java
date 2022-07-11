package com.texo.filmproducer.model;

import lombok.Data;

@Data
public class ProducerPrizeYearDTO implements Comparable<ProducerPrizeYearDTO> {
    private String name;
    private Integer year;

    @Override
    public int compareTo(ProducerPrizeYearDTO o) {
        var result = name.compareTo(o.getName());

        if (result != 0) {
            return result;
        }

        return year.compareTo(o.year);
    }
}
