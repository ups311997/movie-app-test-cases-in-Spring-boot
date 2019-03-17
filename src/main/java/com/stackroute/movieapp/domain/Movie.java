package com.stackroute.movieapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.processing.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Document(value="movie")
public class Movie {
    @Id
    @NotNull
    private int movieid;

    @NotNull
    @Size(min = 1,max = 30)
    private String moviename;

    @NotNull
    @Size(min = 1,max = 50)
    private String moviedetails;

    public Movie() {
    }

    public Movie(int movieid,String moviename, String moviedetails) {
this.movieid=movieid;
        this.moviename = moviename;
        this.moviedetails = moviedetails;
    }

    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public String getMoviedetails() {
        return moviedetails;
    }

    public void setMoviedetails(String moviedetails) {
        this.moviedetails = moviedetails;
    }
}

