package com.texo.filmproducer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class MovieAlreadyExistsException extends RuntimeException {
    public MovieAlreadyExistsException() {
        super("Movie title already exists in the database.");
    }
}
