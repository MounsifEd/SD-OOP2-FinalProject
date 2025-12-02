package com.example.sdoop2finalproject.Models.Showtimes;

import com.example.sdoop2finalproject.Models.Movie.Movie;

/**
 * Represent a single showtime for a movie.
 * @Author Mounsif
 */
public class MovieShow {
    /**
     * ID for the showtime.
     */
    private final int aShowID;
    /**
     * Time at what the movie will be shown
     */
    private String aShowTime;
    /**
     * Date if the show in YYYY-MMM-DD
     */
    private String aShowDate;
    /**
     * ID of the room where the movie will be shown.
     */
    private int aRoomID;
    /**
     * The movie at this showtime.
     */
    private final Movie aMovie;

    /**
     * Create a MovieShow object.
     * @param pShowID ID for the showtime.
     * @param pShowTime Time at what the movie will be shown
     * @param pShowDate Date if the show in YYYY-MMM-DD
     * @param pRoomID ID of the room where the movie will be shown.
     * @param pMovie The movie at this showtime.
     */
    public MovieShow(int pShowID, String pShowTime, String pShowDate, int pRoomID, Movie pMovie) {
        this.aShowID = pShowID;
        this.aShowTime = pShowTime;
        this.aShowDate = pShowDate;
        this.aRoomID = pRoomID;
        this.aMovie = pMovie;
    }

    /**
     * @return the showtime ID
     */
    public int getShowID() {
        return aShowID;
    }

    /**
     *
     * @return the time of the show.
     */
    public String getAshowTime() {
        return aShowTime;
    }

    /**
     *
     * @return the date of the show.
     */
    public String getShowDate() {
        return aShowDate;
    }

    /**
     *
     * @return the room where the movie will be shown.
     */
    public int getRoomID() {
        return aRoomID;
    }

    /**
     *
     * @return the movie for the current showtime.
     */
    public Movie getMovie() {
        return aMovie;
    }

    /**
     * Sets the showtime.
     * @param pShowTime the new showtime
     */
    public void setShowTime(String pShowTime) {
        this.aShowTime = pShowTime;
    }

    /**
     * Sets the show date.
     * @param pShowDate the new show date
     */
    public void setShowDate(String pShowDate) {
        this.aShowDate = pShowDate;
    }

    /**
     * Sets the room ID.
     * @param pRoomID the new room ID
     */
    public void setRoomID(int pRoomID) {
        this.aRoomID = pRoomID;
    }
}


