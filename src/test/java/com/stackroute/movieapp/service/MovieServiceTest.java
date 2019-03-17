package com.stackroute.movieapp.service;

import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.exceptions.MovieAlreadyExistsException;
import com.stackroute.movieapp.repository.MovieRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class MovieServiceTest {

    private Movie movie;

    //Create a mock for UserRepository
    @Mock
    private MovieRepository movieRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    private MovieServiceImpl movieService;
    List<Movie> list = null;


    @Before
    public void setUp() {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        movie = new Movie();
        movie.setMovieid(9);
        movie.setMoviename("Speed");
        movie.setMoviedetails("A Great Movie");
        list = new ArrayList<>();
        list.add(movie);


    }

    @Test
    public void saveMovieTestSuccess() throws MovieAlreadyExistsException {

        when(movieRepository.save((Movie) any())).thenReturn(movie);
        Movie savedMovie = movieService.saveMovie(movie);
        Assert.assertEquals(movie, savedMovie);

        //verify here verifies that userRepository save method is only called once
        verify(movieRepository, times(1)).save(movie);

    }

    @Test(expected = MovieAlreadyExistsException.class)
    public void saveMovieTestFailure() throws MovieAlreadyExistsException {
        when(movieRepository.save((Movie) any())).thenReturn(null);
        Movie savedMovie = movieService.saveMovie(movie);
        System.out.println("savedMovie" + savedMovie);
        Assert.assertEquals(movie, savedMovie);

       /*doThrow(new UserAlreadyExistException()).when(userRepository).findById(eq(101));
       userService.saveUser(user);*/


    }

    @Test
    public void getAllMovie() {

        movieRepository.save(movie);
        //  stubbing the mock to return specific data
        when(movieRepository.findAll()).thenReturn(list);
        List<Movie> movieList = movieService.getAllMovies();
        Assert.assertEquals(list, movieList);
    }

    @Test
    public void getMovieById(){

        when(movieRepository.findById(9)).thenReturn(java.util.Optional.ofNullable(movie));
        Movie foundMovie = movieService.getMovieById(9);
        Assert.assertEquals(movie,foundMovie);
    }

    @Test
    public void deleteMovie(){
        movieService.deleteMovie(9);
        verify(movieRepository,times(1)).deleteById(9);
    }
}





