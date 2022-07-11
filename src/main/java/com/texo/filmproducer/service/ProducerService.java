package com.texo.filmproducer.service;

import com.texo.filmproducer.model.ProducerListResponse;
import com.texo.filmproducer.model.ProducerResponse;
import com.texo.filmproducer.repository.MovieRepository;
import com.texo.filmproducer.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerService {

    private final MovieRepository movieRepository;

    public void findProducersWithBiggestAndSmallestWinIntervals() {
        final var movies = movieRepository.findAll();
        final var producers = new ArrayList<ProducerResponse>();

        movies.forEach(movie -> {
            if (movie.isWinner()) {
                final var producerNames = StringUtils.splitProducers(movie.getProducers());

                Arrays.stream(producerNames).forEach(producerName -> {

                    final var producerResponse = producers
                            .stream()
                            .filter(producer -> producer.getProducer().equals(producerName))
                            .findFirst();

                    if (producerResponse.isEmpty()) {
                        final var producer = new ProducerResponse();

                        producer.setProducer(producerName);
                        producer.setPreviousWin(movie.getYear());
                        producer.setFollowingWin(movie.getYear());
                        producer.setInterval(0);

                        producers.add(producer);
                    } else {
                        final var updatedProducer = setProducerWinDates(producerResponse.get(), movie.getYear());
                        final var producerIndexToBeUpdated = producers.indexOf(producerResponse.get());

                        producers.set(producerIndexToBeUpdated, updatedProducer);
                    }
                });
            }
        });

        Collections.sort(producers);

        final var producerListResponse = new ProducerListResponse();

        producerListResponse.setMax(getTheProducersWithMaxInterval(producers));
        producerListResponse.setMin(getTheProducersWithMinInterval(producers));


    }

    private ProducerResponse setProducerWinDates(final ProducerResponse producer, final Integer prizeDate) {
        final var previewsWin = producer.getPreviousWin() > prizeDate ? prizeDate : producer.getPreviousWin();
        final var followingWin = producer.getFollowingWin() < prizeDate ? prizeDate : producer.getFollowingWin();

        producer.setPreviousWin(previewsWin);
        producer.setFollowingWin(followingWin);
        producer.setInterval(followingWin - previewsWin);

        return producer;
    }

    private List<ProducerResponse> getTheProducersWithMaxInterval(final List<ProducerResponse> producers) {
        final var maxIntervalAvailable = producers.get(producers.size() - 1).getInterval();

        return producers.stream().filter(producer -> producer
                        .getInterval()
                        .equals(maxIntervalAvailable))
                .collect(Collectors.toList());
    }

    private List<ProducerResponse> getTheProducersWithMinInterval(final List<ProducerResponse> producers) {
        final var minIntervalAvailable = producers
                .stream()
                .filter(producer -> producer.getInterval() > 0)
                .findFirst()
                .get()
                .getInterval();

        return producers.stream().filter(producer -> producer
                        .getInterval()
                        .equals(minIntervalAvailable))
                .collect(Collectors.toList());
    }

}
