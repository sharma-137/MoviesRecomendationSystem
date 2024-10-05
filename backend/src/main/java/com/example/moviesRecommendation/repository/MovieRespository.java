package com.example.moviesRecommendation.repository;

import com.example.moviesRecommendation.entity.Movie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieRespository extends JpaRepository<Movie,Long> {


    @Query(value = "SELECT * FROM movie WHERE name LIKE %:name%", nativeQuery = true)
    List<Movie> findMoviesByName(@Param("name") String name);

    @Query(value = "SELECT * FROM movie WHERE rating > :rating", nativeQuery = true)
    List<Movie> findMoviesWithRatingHigherThan(@Param("rating") double rating);

    @Query(value = "SELECT * FROM movie WHERE movie_type = :type", nativeQuery = true)
    List<Movie> getMoviesByType(@Param("type") String type);
}
