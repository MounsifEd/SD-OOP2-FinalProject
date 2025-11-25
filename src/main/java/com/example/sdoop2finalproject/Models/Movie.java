package com.example.sdoop2finalproject.Models;

/**
 * Represents a movie
 * stores basic information such as its ID, name, genre, release date, and the path to its poster image.
 */
public class Movie {
    /**
     * ID for the movie.
     */
    private final int aMovieID;
    /**
     * Title of the movie.
     */
    private final String aMovieName;
    /**
     * release date of the movie.
     */
    private final String aReleaseDate;
    /**
     * Genre of the movie.
     */
    private final String aMovieGenre;
    /**
     * File path to the movie poster image used by MovieCardController.
     */
    private final String aImagePath;

    /**
     * Construct a movie object with all its details.
     * @param pMovieID ID for the movie.
     * @param pReleaseDate Title of the movie.
     * @param pMovieGenre Genre of the movie.
     * @param pImagePath Image of the movie.
     */
    public Movie(int pMovieID, String pMovieName, String pReleaseDate, String pMovieGenre, String pImagePath) {
        this.aMovieID = pMovieID;
        this.aMovieName = pMovieName;
        this.aReleaseDate = pReleaseDate;
        this.aMovieGenre = pMovieGenre;
        this.aImagePath = pImagePath;
    }

    /**
     * @return the movie ID
     */
    public int getaMovieID() {
        return aMovieID;
    }

    /**
     * @return the movie name.
     */
    public String getaMovieName() {
        return aMovieName;
    }

    /**
     * @return the release date.
     */
    public String getaReleaseDate() {
        return aReleaseDate;
    }

    /**
     * @return the movie genre
     */
    public String getaMovieGenre() {
        return aMovieGenre;
    }

    /**
     * Returns the file path to the movie's poster image.
     * @return image path
     */
    public String getaImagePath() {
        return aImagePath;
    }
}
