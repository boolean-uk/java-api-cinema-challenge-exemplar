package com.booleanuk.api.cinema.screening;

import com.booleanuk.api.cinema.movie.Movie;
import com.booleanuk.api.cinema.movie.MovieRepository;
import com.booleanuk.api.cinema.response.DataResponse;
import com.booleanuk.api.cinema.response.ListResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("movies/{movieId}/screenings")
public class ScreeningController {
    private final ScreeningRepository repository;
    private final MovieRepository movieRepository;

    public ScreeningController(ScreeningRepository repository, MovieRepository movieRepository) {
        this.repository = repository;
        this.movieRepository = movieRepository;
    }

    record PostScreening(Integer screenNumber, Integer capacity, String startsAt) {}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public DataResponse<Screening> create(@RequestBody PostScreening request, @PathVariable("movieId") Integer movieId) {
        Movie movie = this.movieRepository.findById(movieId).orElseThrow();
        Timestamp startsAt = Timestamp.valueOf(request.startsAt());

        Screening screening = new Screening();
        screening.setMovie(movie);
        screening.setStartsAt(startsAt);
        screening.setScreenNumber(request.screenNumber());
        screening.setCapacity(request.capacity());

        Screening saved = this.repository.save(screening);

        return new DataResponse<>(saved);
    }

    @GetMapping
    public ListResponse<Screening> getAll(@PathVariable("movieId") Integer movieId) {
        return new ListResponse<>(this.repository.findByMovieId(movieId));
    }
}
