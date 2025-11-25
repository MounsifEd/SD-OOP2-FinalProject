package com.example.sdoop2finalproject.Controllers;

import com.example.sdoop2finalproject.Models.Movie;
import com.example.sdoop2finalproject.Models.MovieData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * movie-view controller that display all movie cards.
 * @Author Mounsif
 */
public class MovieController {
    /**
     * Contains all movie cards displayed.
     */
    @FXML
    private FlowPane movieFlowPane;

    /**
     * Initializes the controller after the FXML is loaded.
     */
    @FXML
    public void initialize() {
        loadMovies();
    }

    /**
     * Loads all movies from {@link MovieData},
     * creates a card for each movie and adds them to {@link FlowPane}
     * @throws RuntimeException if the movie-card FXML cannot be loaded.
     */
    private void loadMovies() {
        movieFlowPane.getChildren().clear();

        for (Movie aMovie : MovieData.getInstance().getMovies().getMovies()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sdoop2finalproject/movie-card.fxml"));

                VBox card = loader.load();
                loader.<MovieCardController>getController().setData(aMovie);
                movieFlowPane.getChildren().add(card);

            } catch (IOException e) {
                throw new RuntimeException("Unable to load movie card FXML", e);
            }
        }
    }
}
