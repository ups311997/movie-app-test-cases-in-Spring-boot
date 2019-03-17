package com.stackroute.movieapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.exceptions.MovieAlreadyExistsException;
import com.stackroute.movieapp.service.MovieService;
import com.stackroute.movieapp.service.MovieServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MovieControllerTest {



        @Autowired
        private MockMvc mockMvc;
        private Movie movie;
        @MockBean
        private MovieServiceImpl movieService;
        @InjectMocks
        private MovieController movieController;

        private List<Movie> list =null;

        @Before
        public void setUp(){

            MockitoAnnotations.initMocks(this);
            mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
            movie = new Movie();
            movie.setMovieid(26);
            movie.setMoviename("Jonny Jonny");
            movie.setMoviedetails("comedy");

            list = new ArrayList();
            list.add(movie);
        }

        @Test
        public void saveMovie() throws Exception {
            when(movieService.saveMovie(any())).thenReturn(movie);
            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movie")
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andDo(MockMvcResultHandlers.print());


        }
        @Test
        public void saveMovieFailure() throws Exception {
            when(movieService.saveMovie(any())).thenThrow(MovieAlreadyExistsException.class);
            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movie")
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                    .andExpect(MockMvcResultMatchers.status().isConflict())
                    .andDo(MockMvcResultHandlers.print());
        }

        @Test
        public void getAllMovies() throws Exception {
            when(movieService.getAllMovies()).thenReturn(list);
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movies")
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());

        }


        private static String asJsonString(final Object obj)
        {
            try{
                return new ObjectMapper().writeValueAsString(obj);

            }catch(Exception e){
                throw new RuntimeException(e);
            }
        }










    }


