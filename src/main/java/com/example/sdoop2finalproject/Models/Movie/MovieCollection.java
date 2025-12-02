package com.example.sdoop2finalproject.Models.Movie;

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
    private final ObservableList<Movie> aMovies = FXCollections.observableArrayList();

    /**
     * Returns the observable list of all movies.
     * @return ObservableList of Movie objects.
     */
    public ObservableList<Movie> getMovies() {
        return aMovies;
    }

    /**
     * It adds a Movie Object to the collection.
     * @param pMovie to add
     */
    public void addMovie(Movie pMovie) {
        aMovies.add(pMovie);
    }

    /**
     * It removes a Movie Object of the collection.
     * @param pMovie to remove
     */
    public void removeMovie(Movie pMovie) {
        aMovies.remove(pMovie);
    }

    /**
     * Searches the movie list for a Movie with the given unique ID.
     * @param pId the unique ID of a movie that we search
     * @return movie with the matching ID,
     * but if NULL it will return {@code null}
     */
    public Movie findByID(int pId) {
        for (Movie movie : aMovies) {
            if (movie.getaMovieID() == pId) {
                return movie;
            }
        }
        return null;
    }
}
