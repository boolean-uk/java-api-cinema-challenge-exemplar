package com.booleanuk.api.cinema.movie;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String rating;
    private String description;
    private Integer runtimeMins;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    public Movie(Integer id, String title, String rating, String description, Integer runtimeMins, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.description = description;
        this.runtimeMins = runtimeMins;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Movie(String title, String rating, String description, Integer runtimeMins) {
        this.title = title;
        this.rating = rating;
        this.description = description;
        this.runtimeMins = runtimeMins;
    }

    public Movie() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRuntimeMins() {
        return runtimeMins;
    }

    public void setRuntimeMins(Integer runtimeMins) {
        this.runtimeMins = runtimeMins;
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
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) && Objects.equals(title, movie.title) && Objects.equals(rating, movie.rating) && Objects.equals(description, movie.description) && Objects.equals(runtimeMins, movie.runtimeMins) && Objects.equals(createdAt, movie.createdAt) && Objects.equals(updatedAt, movie.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, rating, description, runtimeMins, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rating='" + rating + '\'' +
                ", description='" + description + '\'' +
                ", runtimeMins=" + runtimeMins +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
