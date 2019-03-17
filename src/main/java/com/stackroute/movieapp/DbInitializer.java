package com.stackroute.movieapp;

import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.exceptions.MovieAlreadyExistsException;
import com.stackroute.movieapp.handler.GlobalExceptionHandler;
import com.stackroute.movieapp.repository.MovieRepository;
import com.stackroute.movieapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class DbInitializer implements CommandLineRunner, ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private MovieRepository movieRepository;

    public DbInitializer(MovieRepository movieRepository){
        this.movieRepository=movieRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Movie movie1=new Movie(2,"Stree","Horror-Comedy");
        Movie movie2=new Movie(3,"Conjuring","Horror");

        this.movieRepository.save(movie1);
        this.movieRepository.save(movie2);
    }

    @Autowired
    private MovieService movieService;

    @Autowired
    Environment env;

    @Value("${id}")
    int id;

    @Value("${details}")
    String details;



    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

//        try {
//            movieService.saveMovie(new Movie(id,env.getProperty("name"),details));
//        } catch (MovieAlreadyExistsException e) {
//            e.printStackTrace();
//        }

    }
}
