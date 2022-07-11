package com.texo.filmproducer.util;

import java.util.Arrays;

public class CustomStringUtils {
    private static final String SPLIT_REGEX = ",|\\band\\b";

    public static String[] splitProducers(String producers) {
        return Arrays.stream(producers.split(SPLIT_REGEX)).map(String::trim).toArray(String[]::new);
    }
}
