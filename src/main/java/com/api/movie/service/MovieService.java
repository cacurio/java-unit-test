package com.api.movie.service;

import com.api.movie.model.Genre;
import com.api.movie.model.Movie;
import com.api.movie.repository.MovieRepository;

import java.util.Collection;
import java.util.stream.Collectors;

public class MovieService {

    MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Collection<Movie> findMoviesByGenre(Genre genre) {
        return movieRepository.findAll()
                .stream().filter(movie -> movie.getGenre().equals(genre))
                .collect(Collectors.toList());
    }

    public Collection<Movie> findMoviesByLength(int length) {
        return movieRepository.findAll()
                .stream().filter(movie -> movie.getMinutes() == length)
                .collect(Collectors.toList());
    }


    public Collection<Movie> findByName(String name){
        return movieRepository.findAll()
                .stream().filter(movie -> movie.getName().equals(name))
                .collect(Collectors.toList());
    }
}
