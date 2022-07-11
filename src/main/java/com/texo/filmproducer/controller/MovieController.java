package com.texo.filmproducer.controller;

import com.texo.filmproducer.model.MovieRequest;
import com.texo.filmproducer.model.MovieResponse;
import com.texo.filmproducer.service.MovieService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/movies")
public class MovieController {

    final MovieService movieService;

    @ApiOperation("Find movies.")
    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<MovieResponse> findMovies(@ApiParam(value = "Name of the producers")
                                          @RequestParam(value = "producers", required = false) final String producers) {
        log.info("Querying movies available in database.");

        return movieService.findMovies(producers);
    }

    @ApiOperation("Save a new movie.")
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMovie(@Valid @RequestBody MovieRequest request) {
        log.info("Saved a new movie entity with title {}.", request.getTitle());

        movieService.saveMovie(request);
    }

    @ApiOperation("Delete a movie by ID.")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMovie(@ApiParam(value = "Movie ID.", example = "12345")
                            @PathVariable(name = "id") final Long id) {
        log.info("Deleting movie with id {}.", id);

        movieService.deleteMovie(id);
    }
}
