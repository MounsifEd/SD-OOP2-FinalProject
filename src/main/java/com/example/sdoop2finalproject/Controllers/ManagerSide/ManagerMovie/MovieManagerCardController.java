package com.example.sdoop2finalproject.Controllers.ManagerSide.ManagerMovie;

import com.example.sdoop2finalproject.Controllers.ManagerSide.ManagerShowRoom.EditRoomController;
import com.example.sdoop2finalproject.Controllers.ManagerSide.ManagerShowtime.ManagerShowtimeController;
import com.example.sdoop2finalproject.Models.Movie.Movie;
import com.example.sdoop2finalproject.Models.Movie.MovieData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Displays movie information and buttons as a card for each movie object.
 * @Author Mounsif
 */
public class MovieManagerCardController {
    @FXML
    private Text movieManagerTitle;
    @FXML
    private Text movieManagerGenre;
    @FXML
    private Text movieManagerRelease;
    @FXML
    private Button editManagerButton;
    @FXML
    private Button removeManagerButton;
    @FXML
    private Button showtimeManagerButton;

    private Movie aMovie;

    /**
     *Run automatically when the view is opened.
     * Sets all button actions.
     */
    @FXML
    public void initialize() {
        setButtonActions();
    }

    /**
     * Fills the movie card with the movie's information.
     * @param pMovie the movie object to display in the current card.
     */
    public void setMovieManagerData(Movie pMovie) {
        this.aMovie = pMovie;

        movieManagerTitle.setText(pMovie.getaMovieName());
        movieManagerGenre.setText("Genre: " + pMovie.getaMovieGenre());
        movieManagerRelease.setText("Release: " + pMovie.getaReleaseDate());
    }

    /**
     *Groups all the button actions that need to be set.
     */
    private void setButtonActions() {
        removeManagerButton.setOnAction(e -> removeMovieCard());
        showtimeManagerButton.setOnAction(e -> openManagerShowtimeView());
        editManagerButton.setOnAction(event -> editMovieCard());
    }

    /**
     * Remove the movie from MovieData and the card that was created in the manager view.
     */
    private void removeMovieCard() {
        MovieData.getInstance().getMovies().removeMovie(aMovie);

        VBox card = (VBox) removeManagerButton.getParent().getParent();
        FlowPane parent = (FlowPane) card.getParent();
        parent.getChildren().remove(card);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Movie: " + aMovie.getaMovieName());
        alert.setHeaderText(null);
        alert.setContentText(aMovie.getaMovieName() + " was removed.");
        alert.showAndWait();
    }

    /**
     * Opens the showtime manager view for the movie.
     */
    private void openManagerShowtimeView() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/sdoop2finalproject/ManagerShowtime/manager-showtime-view.fxml")
            );
            Parent root = loader.load();

            ManagerShowtimeController controller = loader.getController();
            controller.loadShowtimes(aMovie);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Showtimes for: " + aMovie.getaMovieName());
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Refreshes the card's UI elements with the current movie data.
     * Called after the movie has been edited to reflect updated information.
     */
    private void refreshUI() {
        movieManagerTitle.setText(aMovie.getaMovieName());
        movieManagerGenre.setText("Genre: " + aMovie.getaMovieGenre());
        movieManagerRelease.setText("Release: " + aMovie.getaReleaseDate());
    }

    /**
     * Opens the edit movie dialog window.
     * Loads the edit-movie.fxml view, passes the current movie data to the
     * EditMovieController, and sets up a callback to refresh the card UI
     * after successful updates.
     *
     * <p>If an error occurs while loading the FXML, the exception is
     * printed to the console.</p>
     */
    private void editMovieCard() {
        try {
            // Load the edit movie FXML
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/sdoop2finalproject/ManagerMovie/edit-movie.fxml")
            );

            Parent root = loader.load();

            // Get the controller and configure it
            EditMovieController controller = loader.getController();

            // Pass current movie to edit window
            controller.setMovie(aMovie);

            // Pass a callback so the card can refresh UI after update
            controller.setOnMovieUpdated(this::refreshUI);

            // Create and show the edit window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Edit Movie");
            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}