package com.texo.filmproducer;

import com.texo.filmproducer.repository.MovieRepository;
import com.texo.filmproducer.util.CsvUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.transaction.Transactional;

@SpringBootApplication
@RequiredArgsConstructor
public class FilmProducerManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmProducerManagerApplication.class, args);
	}

	@Bean
	@Transactional
	public CommandLineRunner loadDatabase(final MovieRepository movieRepository) {
		return (args) -> {
			final var csvUtils = new CsvUtils();
			final var movies = csvUtils.parseMovies();

			movieRepository.saveAll(movies);
		};
	}
}
