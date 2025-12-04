package com.example.sdoop2finalproject.Models.Showtimes;

import com.example.sdoop2finalproject.Models.Movie.Movie;
import com.example.sdoop2finalproject.Models.Movie.MovieData;
import javafx.collections.FXCollections;

import java.time.LocalDate;

/**
 * Has all showtimes using the Singleton pattern.
 * Creates and stores a single instance.
 * @Author Mounsif
 */
public class ShowtimeData {
    /**
     * The single instance of ShowtimeData used throughout the program.
     */
    private static final ShowtimeData aInstance = new ShowtimeData();
    /**
     * Collection storing all showtimes available.
     */
    private final ShowtimeCollection aShowCollection = new ShowtimeCollection();

    /**
     *  The private constructor, prevents external instantiation.
     */
    private ShowtimeData() {
        TestShows();
    }

    /**
     * Return the single instance of the class
     * @return ShowtimeData instance
     */
    public static ShowtimeData getInstance() {
        return aInstance;
    }

    /**
     * Returns the collection of all showtimes.
     * @return the ShowtimeCollection object
     */
    public ShowtimeCollection getShows() {
        return aShowCollection;
    }


    /**
     * Loads sample movie only for testing purposes.
     */
    private void TestShows() {

        Movie avatar = MovieData.getInstance().getMovies().findByID(1);
        Movie interstellar = MovieData.getInstance().getMovies().findByID(2);

        aShowCollection.addShow(new MovieShow(1, "18:00", "2025-12-20", 1, avatar));
        aShowCollection.addShow(new MovieShow(2, "21:00", "2025-12-21", 4, avatar));
        aShowCollection.addShow(new MovieShow(3, "20:15", "2025-12-22", 2, interstellar));
    }

    /**
     * Refreshes the observable showtime list to trigger UI updates.
     */
    public void refreshShowtimeList() {
        var list = aShowCollection.getShowList();
        var copy = FXCollections.observableArrayList(list);
        list.setAll(copy);
    }
}
