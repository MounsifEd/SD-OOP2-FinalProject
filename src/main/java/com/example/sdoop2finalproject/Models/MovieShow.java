package com.example.sdoop2finalproject.Models;

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
    private final String aShowTime;
    /**
     * Date if the show in YYYY-MMM-DD
     */
    private final String aShowDate;
    /**
     * ID of the room where the movie will be shown.
     */
    private final int aRoomID;
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
}


