package com.example.sdoop2finalproject.Models;

/**
 * Represents a movie.
 * Stores information such as ID, title, genre, release date,
 * poster image path, ticket price, and description.
 */
public class Movie {

    /** ID for the movie */
    private int aMovieID;

    /** Title of the movie */
    private String aMovieName;

    /** Release date of the movie */
    private String aReleaseDate;

    /** Genre of the movie */
    private String aMovieGenre;

    /** File path to the movie poster image */
    private String aImagePath;

    /** Ticket price */
    private double aPrice;

    /** Description of the movie */
    private String aDescription;

    /**
     * Construct a movie object with basic details.
     */
    public Movie(int pMovieID, String pMovieName, String pReleaseDate, String pMovieGenre, String pImagePath) {
        this.aMovieID = pMovieID;
        this.aMovieName = pMovieName;
        this.aReleaseDate = pReleaseDate;
        this.aMovieGenre = pMovieGenre;
        this.aImagePath = pImagePath;

        // Defaults for extended data
        this.aPrice = 0.0;
        this.aDescription = "";
    }

    /**
     * Construct a movie object with ALL details.
     */
    public Movie(int pMovieID, String pMovieName, String pReleaseDate,
                 String pMovieGenre, String pImagePath, double pPrice, String pDescription) {

        this.aMovieID = pMovieID;
        this.aMovieName = pMovieName;
        this.aReleaseDate = pReleaseDate;
        this.aMovieGenre = pMovieGenre;
        this.aImagePath = pImagePath;
        this.aPrice = pPrice;
        this.aDescription = pDescription;
    }

    // ===========================
    //          GETTERS
    // ===========================

    public int getMovieID() {
        return aMovieID;
    }

    public String getMovieName() {
        return aMovieName;
    }

    public String getReleaseDate() {
        return aReleaseDate;
    }

    public String getGenre() {
        return aMovieGenre;
    }

    /**
     * Returns the file path to the movie's poster image.
     */
    public String getImagePath() {
        return aImagePath;
    }

    public double getPrice() {
        return aPrice;
    }

    public String getDescription() {
        return aDescription;
    }

    // ===========================
    //          SETTERS
    // ===========================

    public void setMovieName(String pMovieName) {
        this.aMovieName = pMovieName;
    }

    public void setGenre(String pGenre) {
        this.aMovieGenre = pGenre;
    }

    public void setReleaseDate(String pReleaseDate) {
        this.aReleaseDate = pReleaseDate;
    }

    public void setPrice(double pPrice) {
        this.aPrice = pPrice;
    }

    public void setDescription(String pDescription) {
        this.aDescription = pDescription;
    }
}
