package com.texo.filmproducer.controller;

import com.texo.filmproducer.model.FilmProducerIntervalListResponse;
import com.texo.filmproducer.service.FilmProducerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/film-producers")
public class FilmProducerController {

    private final FilmProducerService filmProducerService;

    @ApiOperation("Retrieves film producers with the biggest and smallest prize victories intervals.")
    @GetMapping("prize-intervals")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public FilmProducerIntervalListResponse findMovieProducersPrizeIntervals() {
        log.info("Querying movie producers prize intervals.");

        return filmProducerService.findProducersPrizeIntervals();
    }
}
