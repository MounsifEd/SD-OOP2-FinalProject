package com.example.sdoop2finalproject.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * By using ObservableList, it stores a collection of Movie objects.
 * And can do various thing like add or delete a movie Object of the ObservableList.
 * Find a movie based of its ID.
 * @Author Mounsif
 */
public class MovieCollection {
    /**
     * Stores all Movie objects.
     */
    private final ObservableList<Movie> movies = FXCollections.observableArrayList();

    /**
     * Returns the observable list of all movies.
     * @return ObservableList of Movie objects.
     */
    public ObservableList<Movie> getMovies() {
        return movies;
    }

    /**
     * It adds a Movie Object to the collection.
     * @param movie to add
     */
    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    /**
     * It removes a Movie Object of the collection.
     * @param movie to remove
     */
    public void removeMovie(Movie movie) {
        movies.remove(movie);
    }

    /**
     * Searches the movie list for a Movie with the given unique ID.
     * @param id the unique ID of a movie that we search
     * @return movie with the matching ID,
     * but if NULL it will return {@code null}
     */
    public Movie findByID(int id) {
        for (Movie movie : movies) {
            if (movie.getMovieID() == id) {
                return movie;
            }
        }
        return null;
    }
}
