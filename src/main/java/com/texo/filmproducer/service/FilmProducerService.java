package com.texo.filmproducer.service;

import com.texo.filmproducer.model.FilmProducerIntervalListResponse;
import com.texo.filmproducer.model.FilmProducerIntervalResponse;
import com.texo.filmproducer.model.ProducerPrizeYearDTO;
import com.texo.filmproducer.repository.MovieRepository;
import com.texo.filmproducer.util.CustomStringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmProducerService {

    private final MovieRepository movieRepository;

    public FilmProducerIntervalListResponse findProducersPrizeIntervals() {
        final var movies = movieRepository.findAll();
        final var producerIntervalsResponse = new ArrayList<FilmProducerIntervalResponse>();
        final var producerPrizeYears = new ArrayList<ProducerPrizeYearDTO>();

        movies.forEach(movie -> {
            if (movie.isWinner()) {
                final var producerNames = CustomStringUtils.splitProducers(movie.getProducers());

                Arrays.stream(producerNames).forEach(producerName -> {

                    if (!StringUtils.isBlank(producerName)) {
                        final var producerPrizeYear = new ProducerPrizeYearDTO();

                        producerPrizeYear.setName(producerName);
                        producerPrizeYear.setYear(movie.getYear());

                        producerPrizeYears.add(producerPrizeYear);
                    }
                });
            }
        });

        producerPrizeYears.forEach(producerPrizeYear -> {
            if (producerIntervalsResponse.stream().noneMatch(item -> item.getProducer().equals(producerPrizeYear.getName()))) {
                final var producerPrizeList = producerPrizeYears
                        .stream()
                        .filter(item -> item.getName().equals(producerPrizeYear.getName()))
                        .collect(Collectors.toList());

                if (producerPrizeList.size() > 1) {
                    final var producerIntervals = getAllProducerIntervals(producerPrizeList);

                    producerIntervalsResponse.addAll(producerIntervals);
                }
            }
        });

        Collections.sort(producerIntervalsResponse);

        final var producerListResponse = new FilmProducerIntervalListResponse();

        if (producerIntervalsResponse.size() > 0) {
            producerListResponse.setMax(getTheProducersWithMaxInterval(producerIntervalsResponse));
            producerListResponse.setMin(getTheProducersWithMinInterval(producerIntervalsResponse));
        }

        return producerListResponse;
    }

    private List<FilmProducerIntervalResponse> getAllProducerIntervals(final List<ProducerPrizeYearDTO> producerPrizeList) {

        final var producerIntervalsResponse = new ArrayList<FilmProducerIntervalResponse>();

        Collections.sort(producerPrizeList);

        final var producerIntervals = getProducerIntervals(producerPrizeList);

        producerIntervalsResponse.addAll(producerIntervals);

        return producerIntervalsResponse;
    }

    private List<FilmProducerIntervalResponse> getProducerIntervals(final List<ProducerPrizeYearDTO> producerPrizeList) {
        final var producerIntervals = new ArrayList<FilmProducerIntervalResponse>();

        if (producerPrizeList.size() > 1) {
            final var listIterator = producerPrizeList.listIterator();
            var producerPrizeYear = listIterator.next();

            while (listIterator.hasNext()) {
                final var producerInterval = new FilmProducerIntervalResponse();
                final var previousWin = producerPrizeYear.getYear();

                producerPrizeYear = listIterator.next();

                final var followingWin = producerPrizeYear.getYear();

                producerInterval.setProducer(producerPrizeYear.getName());
                producerInterval.setPreviousWin(previousWin);
                producerInterval.setFollowingWin(followingWin);
                producerInterval.setInterval(followingWin - previousWin);

                producerIntervals.add(producerInterval);
            }
        }

        return producerIntervals;
    }

    private List<FilmProducerIntervalResponse> getTheProducersWithMaxInterval(final List<FilmProducerIntervalResponse> producersIntervals) {
        final var maxIntervalAvailable = producersIntervals.get(producersIntervals.size() - 1).getInterval();

        return producersIntervals.stream().filter(producer -> producer
                        .getInterval()
                        .equals(maxIntervalAvailable))
                .collect(Collectors.toList());
    }

    private List<FilmProducerIntervalResponse> getTheProducersWithMinInterval(final List<FilmProducerIntervalResponse> producersIntervals) {
        final var minIntervalAvailable = producersIntervals.get(0).getInterval();

        return producersIntervals.stream().filter(producer -> producer
                        .getInterval()
                        .equals(minIntervalAvailable))
                .collect(Collectors.toList());
    }

}
