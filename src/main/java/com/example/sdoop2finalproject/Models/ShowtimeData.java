package com.example.sdoop2finalproject.Models;

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

        aShowCollection.addShow(new MovieShow(1, "18:00", "2025-11-24", 1, avatar));
        aShowCollection.addShow(new MovieShow(2, "21:00", "2025-11-24", 4, avatar));
        aShowCollection.addShow(new MovieShow(2, "18:00", "2025-11-25", 4, avatar));
        aShowCollection.addShow(new MovieShow(3, "18:00", "2025-11-26", 4, avatar));
        aShowCollection.addShow(new MovieShow(4, "21:00", "2025-11-26", 4, avatar));
        aShowCollection.addShow(new MovieShow(3, "20:15", "2025-11-25", 2, interstellar));
    }

}
