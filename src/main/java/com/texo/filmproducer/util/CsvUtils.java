package com.texo.filmproducer.util;

import com.opencsv.bean.CsvToBeanBuilder;
import com.texo.filmproducer.domain.MovieEntity;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.List;

public class CsvUtils {
    private static String FILE_PATH = "data/movielist.csv";

    public List<MovieEntity> parseMovies() throws FileNotFoundException {

        final var classloader = Thread.currentThread().getContextClassLoader();
        final var stream = classloader.getResourceAsStream(FILE_PATH);
        final var reader = new InputStreamReader(stream);

        return new CsvToBeanBuilder<MovieEntity>(reader)
                .withSeparator(';')
                .withSkipLines(1)
                .withType(MovieEntity.class)
                .build()
                .parse();
    }
}
