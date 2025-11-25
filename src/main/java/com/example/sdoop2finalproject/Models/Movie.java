package com.example.sdoop2finalproject.Models;

/**
 * Represents a movie
 * stores basic information such as its ID, name, genre, release date, and the path to its poster image.
 */
public class Movie {
    /**
     * ID for the movie.
     */
    private int aMovieID;
    /**
     * Title of the movie.
     */
    private String aMovieName;
    /**
     * release date of the movie.
     */
    private String aReleaseDate;
    /**
     * Genre of the movie.
     */
    private String aMovieGenre;
    /**
     * File path to the movie poster image used by MovieCardController.
     */
    private String aImagePath;
    /**
     * Price of the movie ticket.
     */
    private double aPrice;
    /**
     * Description of the movie.
     */
    private String aDescription;

    /**
     * Construct a movie object with all its details.
     * @param pMovieID ID for the movie.
     * @param pMovieName Title of the movie.
     * @param pReleaseDate Release date of the movie.
     * @param pMovieGenre Genre of the movie.
     * @param pImagePath Image of the movie.
     */
    public Movie(int pMovieID, String pMovieName, String pReleaseDate, String pMovieGenre, String pImagePath) {
        this.aMovieID = pMovieID;
        this.aMovieName = pMovieName;
        this.aReleaseDate = pReleaseDate;
        this.aMovieGenre = pMovieGenre;
        this.aImagePath = pImagePath;
        this.aPrice = 0.0;
        this.aDescription = "";
    }

    /**
     * Construct a movie object with all details including price and description.
     */
    public Movie(int pMovieID, String pMovieName, String pReleaseDate, String pMovieGenre, String pImagePath, double pPrice, String pDescription) {
        this.aMovieID = pMovieID;
        this.aMovieName = pMovieName;
        this.aReleaseDate = pReleaseDate;
        this.aMovieGenre = pMovieGenre;
        this.aImagePath = pImagePath;
        this.aPrice = pPrice;
        this.aDescription = pDescription;
    }

    /**
     * @return the movie ID
     */
    public int getaMovieID() {
        return aMovieID;
    }

    /**
     * @return the movie ID (alternative getter)
     */
    public int getMovieID() {
        return aMovieID;
    }

    /**
     * @return the movie name.
     */
    public String getaMovieName() {
        return aMovieName;
    }

    /**
     * @return the movie name (alternative getter)
     */
    public String getMovieName() {
        return aMovieName;
    }

    /**
     * @return the release date.
     */
    public String getaReleaseDate() {
        return aReleaseDate;
    }

    /**
     * @return the release date (alternative getter)
     */
    public String getReleaseDate() {
        return aReleaseDate;
    }

    /**
     * @return the movie genre
     */
    public String getaMovieGenre() {
        return aMovieGenre;
    }

    /**
     * @return the movie genre (alternative getter)
     */
    public String getGenre() {
        return aMovieGenre;
    }

    /**
     * Returns the file path to the movie's poster image.
     * @return image path
     */
    public String getaImagePath() {
        return aImagePath;
    }

    /**
     * @return the ticket price
     */
    public double getPrice() {
        return aPrice;
    }

    public String setMovieName(String pMovieName) {
        return aMovieName = pMovieName;
    }

    public String setGenre(String pGenre) {
        return aMovieGenre = pGenre;
    }

    public String setReleaseDate(String pReleaseDate) {
        return aReleaseDate = pReleaseDate;
    }

    /**
     * Set the ticket price
     * @param pPrice the new price
     */
    public void setPrice(double pPrice) {
        this.aPrice = pPrice;
    }

    /**
     * @return the movie description
     */
    public String getDescription() {
        return aDescription;
    }

    /**
     * Set the movie description
     * @param pDescription the new description
     */
    public void setDescription(String pDescription) {
        this.aDescription = pDescription;
    }
}