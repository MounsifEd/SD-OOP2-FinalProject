package com.example.sdoop2finalproject.Models.Movie;

/**
 * Represents a movie and stores its essential information such as ID, title, genre,
 * and release date. This class provides basic getters and setters for modifying
 * the movie's title, genre, and release date.
 *
 * @author Alejandro
 */
public class Movie {

    /**
     * Unique identifier for the movie.
     */
    private final int aMovieID;

    /**
     * Title of the movie.
     */
    private String aMovieName;

    /**
     * Release date of the movie (typically the year).
     */
    private int aReleaseDate;

    /**
     * Genre of the movie.
     */
    private String aMovieGenre;

    /**
     * Constructs a Movie object with all its details.
     *
     * @param pMovieID      The unique ID for the movie.
     * @param pMovieName    The title of the movie.
     * @param pReleaseDate  The release date of the movie.
     * @param pMovieGenre   The genre of the movie.
     */
    public Movie(int pMovieID, String pMovieName, int pReleaseDate, String pMovieGenre) {
        this.aMovieID = pMovieID;
        this.aMovieName = pMovieName;
        this.aReleaseDate = pReleaseDate;
        this.aMovieGenre = pMovieGenre;
    }

    /**
     * Gets the movie ID.
     *
     * @return the unique movie ID.
     */
    public int getaMovieID() {
        return aMovieID;
    }

    /**
     * Gets the movie title.
     *
     * @return the movie title.
     */
    public String getaMovieName() {
        return aMovieName;
    }

    /**
     * Gets the release date.
     *
     * @return the release date of the movie.
     */
    public int getaReleaseDate() {
        return aReleaseDate;
    }

    /**
     * Gets the genre of the movie.
     *
     * @return the movie genre.
     */
    public String getaMovieGenre() {
        return aMovieGenre;
    }

    /**
     * Sets the movie name.
     *
     * @param pMovieName the new title for the movie.
     */
    public void setMovieName(String pMovieName) {
        this.aMovieName = pMovieName;
    }

    /**
     * Sets the movie genre.
     *
     * @param pMovieGenre the new genre of the movie.
     */
    public void setMovieGenre(String pMovieGenre) {
        this.aMovieGenre = pMovieGenre;
    }

    /**
     * Sets the release date of the movie.
     *
     * @param pReleaseDate the new release date.
     */
    public void setReleaseDate(int pReleaseDate) {
        this.aReleaseDate = pReleaseDate;
    }
}
