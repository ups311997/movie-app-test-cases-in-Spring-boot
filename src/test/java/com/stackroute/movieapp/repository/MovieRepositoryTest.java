package com.stackroute.movieapp.repository;

import com.stackroute.movieapp.domain.Movie;
import org.apache.catalina.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataMongoTest
public class MovieRepositoryTest {



        @Autowired
        private MovieRepository movieRepository;
        private Movie movie;

        @Before
        public void setUp()
        {
            movie = new Movie();
            movie.setMovieid(10);
            movie.setMoviename("Vampire");
            movie.setMoviedetails("Vampires are not real");

        }

        @After
        public void tearDown(){

            movieRepository.deleteAll();
        }


        @Test
        public void testSaveMovie(){
            movieRepository.save(movie);
            Movie fetchMovie = movieRepository.findById(movie.getMovieid()).get();
            Assert.assertEquals(10,fetchMovie.getMovieid());

        }

        @Test
        public void testSaveMovieFailure(){
            Movie testMovie = new Movie(13,"Fugly","comedy movie");
            movieRepository.save(movie);
            Movie fetchMovie = movieRepository.findById(movie.getMovieid()).get();
            Assert.assertNotSame(testMovie,movie);
        }

        @Test
        public void testGetAllMovie(){
            Movie m = new Movie(11,"Dhadak","Romantic Movie");
            Movie m1 = new Movie(12,"DDlJ","Fabulous movie");
            movieRepository.save(m);
            movieRepository.save(m1);

            List<Movie> list = movieRepository.findAll();
            Assert.assertEquals("Dhadak",list.get(0).getMoviename());




        }


    }


