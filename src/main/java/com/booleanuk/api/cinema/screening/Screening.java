package com.booleanuk.api.cinema.screening;

import com.booleanuk.api.cinema.movie.Movie;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movieId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Movie movie;

    private Integer screenNumber;
    private Integer capacity;
    private Timestamp startsAt;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    public Screening(Integer id, Movie movie, Integer screenNumber, Integer capacity, Timestamp startsAt, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.movie = movie;
        this.screenNumber = screenNumber;
        this.capacity = capacity;
        this.startsAt = startsAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Screening() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Integer getScreenNumber() {
        return screenNumber;
    }

    public void setScreenNumber(Integer screenNumber) {
        this.screenNumber = screenNumber;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Timestamp getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(Timestamp startsAt) {
        this.startsAt = startsAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Screening screening = (Screening) o;
        return Objects.equals(id, screening.id) && Objects.equals(movie, screening.movie) && Objects.equals(screenNumber, screening.screenNumber) && Objects.equals(capacity, screening.capacity) && Objects.equals(startsAt, screening.startsAt) && Objects.equals(createdAt, screening.createdAt) && Objects.equals(updatedAt, screening.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movie, screenNumber, capacity, startsAt, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Screening{" +
                "id=" + id +
                ", movie=" + movie +
                ", screenNumber=" + screenNumber +
                ", capacity=" + capacity +
                ", startsAt=" + startsAt +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
