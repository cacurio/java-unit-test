package com.api.movie.service;

import com.api.movie.model.Genre;
import com.api.movie.model.Movie;
import com.api.movie.repository.MovieRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MovieServiceShould {

    MovieService movieService;

    @Before
    public void setUp() throws Exception {

        MovieRepository movieRepository = Mockito.mock(MovieRepository.class);

        Mockito.when(movieRepository.findAll()).thenReturn(
                Arrays.asList(
                        new Movie(1, "Dark Knight", 152, Genre.ACTION),
                        new Movie(2, "Memento", 113, Genre.THRILLER),
                        new Movie(3, "There's Something About Mary", 119, Genre.COMEDY),
                        new Movie(4, "Super 8", 112, Genre.THRILLER),
                        new Movie(5, "Scream", 111, Genre.HORROR),
                        new Movie(6, "Home Alone", 103, Genre.COMEDY),
                        new Movie(7, "Matrix", 119, Genre.ACTION)
                )
        );
        movieService = new MovieService(movieRepository);
    }

    @Test
    public void return_movies_by_genre() {
        Collection<Movie> movies = movieService.findMoviesByGenre(Genre.COMEDY);
        assertThat(getMovieId(movies), is(Arrays.asList(3, 6)));
    }

    @Test
    public void return_movies_by_Length() {

        Collection<Movie> movies = movieService.findMoviesByLength(119);
        assertThat(getMovieId(movies), is(Arrays.asList(3, 7)));
    }

    @Test
    public void return_movies_by_name() {
        Movie movieData = new Movie(2, "Memento", 113, Genre.THRILLER);
        Collection<Movie> movies = movieService.findByName("Memento");
        assertThat(getMovieId(movies), is(Arrays.asList(2)));
    }

    private List<Integer> getMovieId(Collection<Movie> movies) {
        return movies.stream().map(Movie::getId).collect(Collectors.toList());
    }
}