package com.example.sdoop2finalproject.Models.Showtimes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * By using ObservableList, it stores a collection of MovieShow objects.
 * And can do various thing like add or delete a movie Object of the ObservableList.
 * Get all showtimes that belong to a specific movie.
 * @Author Mounsif
 */
public class ShowtimeCollection {
    /**
     * List containing all showtimes.
     */
    private final ObservableList<MovieShow> aShowList = FXCollections.observableArrayList();

    /**
     * @return the full list of showtimes.
     */
    public ObservableList<MovieShow> getShowList() {
        return aShowList;
    }

    /**
     * Adds a new showtime to the collection.
     * @param pShow the MovieShow object to add.
     */
    public void addShow(MovieShow pShow) {
        aShowList.add(pShow);
    }

    /**
     * Removes a showtime from the collection.
     * @param pShow the MovieShow object to remove.
     */
    public void removeShow(MovieShow pShow) {
        aShowList.remove(pShow);
    }


    /**
     * Get all showtimes that belong to a specific movie.
     * @param pMovieID of the movie to filter.
     * @return the showtimes for the movie.
     */
    public ObservableList<MovieShow> getShowByMovieID(int pMovieID) {
        return aShowList.filtered(s -> s.getMovie().getaMovieID() == pMovieID);
    }

}
