/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.glassfish.movieplex7.client;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.glassfish.movieplex7.entities.Movie;
import org.glassfish.movieplex7.json.MovieWriter;

/**
 *
 * @author Eusuph
 */
@Named
@RequestScoped
public class MovieClientBean {
    
    /*
     * injecting movieBackingBean into movieClientBean
     */
    @Inject
    MovieBackingBean bean;
    
    Client client;
    WebTarget target;
    
    @PostConstruct
    public void init(){
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/movieplex7/webresources/movie/");
    }
    @PreDestroy
    public void destroy(){
        client.close();
    }
    
    public  Movie[] getMovies(){
        return target.request().get(Movie[].class);
    }
    
    public Movie getMovie(){
        Movie movie = target.path("{movie}").
                 resolveTemplate("movie", bean.getMovieId()).
                     request().get(Movie.class);
        return movie;
    }
    
    public void deleteMovie(){
        target.path("{movieId}").
                resolveTemplate(" movieId",bean.
                        getMovieId()).request().delete();
    }
    public void addMovie(){
        Movie movie = new Movie();
        movie.setId(bean.getMovieId());
        movie.setName(bean.getMovieName());
        movie.setActors(bean.getActors());
        target.register(MovieWriter.class).request().
                post(Entity.entity(movie, MediaType.APPLICATION_JSON));
    }
}
