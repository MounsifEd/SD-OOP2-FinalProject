package com.example.sdoop2finalproject.Controllers;

import com.example.sdoop2finalproject.Models.Movie;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * Controller for the movie-card view.
 * @Author Mounsif
 */
public class MovieCardController {
    /**
     * Display the movie poster.
     */
    @FXML
    private ImageView posterView;
    /**
     * Display the movie title.
     */
    @FXML
    private Text movieTitle;
    /**
     * Displau the movie genre.
     */
    @FXML
    private Text movieGenre;
    /**
     * display the movie release year.
     */
    @FXML
    private Text movieRelease;


    /**
     * Change UI components using data fromm given Movie Object.
     * Method called in MovieController when creating movie cards.
     * @param movie that needs to be displayed on the card
     */
    public void setData(Movie movie) {
        posterView.setImage(new Image(getClass().getResource(movie.getImagePath()).toExternalForm()));
        movieTitle.setText(movie.getMovieName());
        movieGenre.setText("Genre: " + movie.getMovieGenre());
        movieRelease.setText("Release: " + movie.getReleaseDate());

    }
}
