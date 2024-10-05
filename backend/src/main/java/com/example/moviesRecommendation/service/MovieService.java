package com.example.moviesRecommendation.service;

import com.example.moviesRecommendation.entity.Movie;
import com.example.moviesRecommendation.repository.MovieRespository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class MovieService {

    @Autowired
    private MovieRespository movieRespository;

    @Autowired
    private ObjectMapper objectMapper;
    private static final Logger log = LogManager.getLogger();

    public List<Movie> getAllMovies() {
        return movieRespository.findAll();
    }

    public Movie addMovie(String movieJson, MultipartFile file){
        try {
            Movie movie = objectMapper.readValue(movieJson, Movie.class);
            Movie newMovie = new Movie();
            newMovie.setName(movie.getName());
            newMovie.setCast(movie.getCast());
            newMovie.setDescription(movie.getDescription());
            newMovie.setRating(movie.getRating());
            newMovie.setImage(file.getBytes());
            newMovie.setMovie_type(movie.getMovie_type());
            newMovie.setDirector(movie.getDirector());
            newMovie.setWriters(movie.getWriters());
            newMovie.setRunTime(movie.getRunTime());
            return movieRespository.save(newMovie);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

    public Movie getMovieById(Long movieId) {

        Optional<Movie> movieOptional = movieRespository.findById(movieId);
        if(movieOptional.isPresent()){
            return movieOptional.get();
        }
        return null;
    }



    public List<Movie> getPopularMovies() {
        return movieRespository.findMoviesWithRatingHigherThan(8);
    }


    public List<Movie> searchMoviesByName(String name) {
        return movieRespository.findMoviesByName(name);
    }

    public List<Movie> getMoviesByType(String type) {
        return movieRespository.getMoviesByType(type);
    }
}
