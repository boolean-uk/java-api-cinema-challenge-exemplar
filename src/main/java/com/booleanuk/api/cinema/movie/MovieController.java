package com.booleanuk.api.cinema.movie;

import com.booleanuk.api.cinema.response.DataResponse;
import com.booleanuk.api.cinema.response.ListResponse;
import com.booleanuk.api.cinema.screening.Screening;
import com.booleanuk.api.cinema.screening.ScreeningRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {
    private final MovieRepository repository;
    private final ScreeningRepository screeningRepository;

    public MovieController(MovieRepository repository, ScreeningRepository screeningRepository) {
        this.repository = repository;
        this.screeningRepository = screeningRepository;
    }

    record PostScreening(Integer screenNumber, Integer capacity, String startsAt) {}

    record PostMovie(String title, String rating, String description, Integer runtimeMins, List<PostScreening> screenings) {}

    @GetMapping
    public ListResponse<Movie> getAll() {
        return new ListResponse<>(this.repository.findAll());
    }

    @GetMapping("{id}")
    public DataResponse<Movie> getById(@PathVariable("id") Integer id) {
        Movie found = this.repository.findById(id).orElseThrow();
        return new DataResponse<>(found);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public DataResponse<Movie> create(@RequestBody PostMovie request) {
        Movie movie = new Movie(
                request.title(),
                request.rating(),
                request.description(),
                request.runtimeMins()
        );

        Movie saved = this.repository.save(movie);

        if (request.screenings() != null && request.screenings().size() > 0) {
            this.saveScreenings(saved, request.screenings());
        }

        return new DataResponse<>(saved);
    }

    private void saveScreenings(Movie movie, List<PostScreening> rawScreenings) {
        List<Screening> screenings = rawScreenings.stream()
                .map(raw -> {
                    Screening screening = new Screening();
                    screening.setCapacity(raw.capacity());
                    screening.setScreenNumber(raw.screenNumber());
                    screening.setMovie(movie);
                    screening.setStartsAt(Timestamp.valueOf(raw.startsAt()));

                    return screening;
                }).toList();

        this.screeningRepository.saveAll(screenings);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("{id}")
    public DataResponse<Movie> update(@RequestBody PostMovie request, @PathVariable("id") Integer id) {
        Movie movie = this.repository.findById(id).orElseThrow();
        movie.setTitle(request.title() != null ? request.title() : movie.getTitle());
        movie.setRating(request.rating() != null ? request.rating() : movie.getRating());
        movie.setDescription(request.description() != null ? request.description() : movie.getDescription());
        movie.setRuntimeMins(request.runtimeMins() != null ? request.runtimeMins() : movie.getRuntimeMins());

        Movie saved = this.repository.save(movie);

        return new DataResponse<>(saved);
    }

    @DeleteMapping("{id}")
    public DataResponse<Movie> delete(@PathVariable("id") Integer id) {
        Movie found = this.repository.findById(id).orElseThrow();
        this.repository.deleteById(id);
        return new DataResponse<>(found);
    }
}
