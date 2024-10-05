package com.example.moviesRecommendation.controller;

import com.example.moviesRecommendation.entity.Movie;
import com.example.moviesRecommendation.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    private static final Logger log = LogManager.getLogger();


    @PostMapping("/")
    private Movie addMovie( @RequestParam("movie") String movieJson, @RequestParam("file") MultipartFile file) {
        try {
            return movieService.addMovie(movieJson, file);
        }catch (Exception exception){
            log.error("error occured: "+ exception.getMessage());
            return null;
        }
    }

    @GetMapping("/")
    List<Movie> getAllMovies(){
        try {
            return movieService.getAllMovies();
        }catch (Exception exception){
            log.error("error occured:  "+ exception.getMessage());
            return null;
        }
    }

    @GetMapping("/movieById")
    Movie getMovieById(@RequestParam Long movieId){
        try {
            return movieService.getMovieById(movieId);
        }catch (Exception exception){
            log.error("error occured: "+exception.getMessage());
            return null;
        }
    }

    @GetMapping("getMoviesByType")
    List<Movie> getMoviesByType(@RequestParam("type") String type){
        try {
            return movieService.getMoviesByType(type);
        }catch (Exception exception){
            log.error("error occured: "+ exception.getMessage());
            return null;
        }
    }

    @GetMapping("/popular")
    public List<Movie> getPopularMovies() {
        try {
            return movieService.getPopularMovies();
        } catch (Exception exception) {
            log.error("Error occurred: " + exception.getMessage());
            return null;
        }
    }

    @GetMapping("/search")
    public List<Movie> searchMovies(@RequestParam("name") String name) {
        try {
            return movieService.searchMoviesByName(name);
        } catch (Exception exception) {
            log.error("Error occurred: " + exception.getMessage());
            return null;
        }
    }


}
