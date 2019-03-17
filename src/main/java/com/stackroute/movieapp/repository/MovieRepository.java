package com.stackroute.movieapp.repository;

import com.stackroute.movieapp.domain.Movie;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


import java.util.List;

public interface MovieRepository extends MongoRepository<Movie, Integer> {


//    @Query("Select m from Movie m where m.moviename = ?1")
//    List<Movie> findByName(String name);
}
