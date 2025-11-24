package com.example.sdoop2finalproject.Models;

public class Movie {
    private final int movieID;
    private final String movieName;
    private final String releaseDate;
    private final String movieGenre;
    /**
     * File path to the movie poster image used by MovieCardController.
     */
    private final String imagePath;

    public Movie(int pMovieID, String pMovieName, String pReleaseDate, String pMovieGenre, String pImagePath) {
        this.movieID  = pMovieID;
        this.movieName = pMovieName;
        this.releaseDate = pReleaseDate;
        this.movieGenre = pMovieGenre;
        this.imagePath = pImagePath;
    }

    public int getMovieID() {
        return movieID;
    }
    public String getMovieName() {
        return movieName;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public String getMovieGenre() {
        return movieGenre;
    }

    /**
     * Returns the file path to the movie's poster image.
     * @return image path
     */
    public String getImagePath() {
        return imagePath;
    }
}
