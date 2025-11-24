package com.example.sdoop2finalproject.Models;

/**
 * Class that uses Singleton responsible
 * for storing and accessing data of all movies.
 *
 * @Author Mounsif
 */
public class MovieData {
    private static final MovieCollection movieCollection = new MovieCollection();
    /**
     * The single instance of the class.
     */
    private static final MovieData instance = new MovieData();

    /**
     * The private constructor, prevents external instantiation.
     */
    private MovieData() {
        testMovies();
    }

    /**
     * Returns the singleton instance.
     * @return the MovieData instance.
     */
    public static MovieData getInstance() {
        return instance;
    }

    /**
     * Give access to MovieCollection that stores all Movie objects.
     * @return the MovieCollection instance.
     */
    public MovieCollection getMovies() {
        return movieCollection;
    }

    /**
     * Loads sample movie only for testing purposes.
     */
    private void testMovies() {
        movieCollection.addMovie(new Movie(1, "Avatar", "2009", "Sci-Fi", "/com/example/sdoop2finalproject/img/8124Pstj51L.jpg"));
        movieCollection.addMovie(new Movie(2, "Interstellar", "2014", "Sci-Fi", "/com/example/sdoop2finalproject/img/8124Pstj51L.jpg"));
        movieCollection.addMovie(new Movie(3, "Inside Out", "2015", "Animation", "/com/example/sdoop2finalproject/img/8124Pstj51L.jpg"));
        movieCollection.addMovie(new Movie(4, "Spider-Man", "2021", "Action", "/com/example/sdoop2finalproject/img/8124Pstj51L.jpg"));

    }
}
