package com.example.sdoop2finalproject.Controllers;

import com.example.sdoop2finalproject.Models.Movie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
     * When Clicked the Showtime screen opens.
     */
    @FXML
    private Button showtimeBtn;


    /**
     * Store the movie assigned to this card.
     */
    private Movie aMovie;

    /**
     * Change UI components using data fromm given Movie Object.
     * Method called in MovieController when creating movie cards.
     * @param pMovie that needs to be displayed on the card
     */
    public void setData(Movie pMovie) {
        this.aMovie = pMovie;

        posterView.setImage(new Image(getClass().getResource(pMovie.getaImagePath()).toExternalForm()));

        movieTitle.setText(pMovie.getaMovieName());
        movieGenre.setText("Genre: " + pMovie.getaMovieGenre());
        movieRelease.setText("Release: " + pMovie.getaReleaseDate());
        showtimeBtn.setOnAction(e -> openShowtimePage());
    }

    /**
     * Opens the showtime view.
     * Change the title to the corresponding movie.
     */
    private void openShowtimePage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sdoop2finalproject/showtime-view.fxml"));
            Parent root = loader.load();

            ShowtimeController controller = loader.getController();
            controller.loadShowtimes(aMovie);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(aMovie.getaMovieName() + " Showtimes");
            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
