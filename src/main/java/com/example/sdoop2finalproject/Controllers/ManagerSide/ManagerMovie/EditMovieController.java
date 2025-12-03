package com.example.sdoop2finalproject.Controllers.ManagerSide.ManagerMovie;

import com.example.sdoop2finalproject.Models.Movie.Movie;
import com.example.sdoop2finalproject.Models.Movie.MovieData;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for the edit movie view.
 * Allows users to modify movie title, genre, and release date of an existing movie.
 *
 * @author Alejandro
 */
public class EditMovieController {

    /** Text field for entering/editing the movie title */
    @FXML
    private TextField titleField;

    /** Text field for entering/editing the movie genre */
    @FXML
    private TextField genreField;

    /** Text field for entering/editing the movie release date */
    @FXML
    private TextField releaseField;

    /** The movie object being edited */
    private Movie selectedMovie;

    /** Callback to refresh the UI after successful update */
    private Runnable refreshCallback;

    /**
     * Initializes the controller.
     * Called automatically after the FXML file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the movie to be edited and populates the text fields with its current data.
     *
     * @param movie the Movie object to edit
     */
    public void setMovie(Movie movie) {
        this.selectedMovie = movie;
        titleField.setText(movie.getaMovieName());
        genreField.setText(movie.getaMovieGenre());
        releaseField.setText(String.valueOf(movie.getaReleaseDate()));
    }

    /**
     * Updates the movie with new values from the text fields.
     * Validates input, updates the movie object, refreshes the data,
     * and closes the edit window on success.
     */
    @FXML
    private void updateMovie() {
        if (selectedMovie == null) {
            showError("Error: No movie loaded!");
            return;
        }

        try {
            String newTitle = titleField.getText().trim();
            String newGenre = genreField.getText().trim();
            int newReleaseDate = Integer.parseInt(releaseField.getText().trim());

            // Validate inputs
            if (newTitle.isEmpty()) {
                showError("Title cannot be empty.");
                return;
            }

            if (newGenre.isEmpty()) {
                showError("Genre cannot be empty.");
                return;
            }

            if (newReleaseDate < 1888 || newReleaseDate > 2100) {
                showError("Please enter a valid release date (1888-2100).");
                return;
            }

            // Update existing object
            selectedMovie.setMovieName(newTitle);
            selectedMovie.setMovieGenre(newGenre);
            selectedMovie.setReleaseDate(newReleaseDate);

            // Notify MovieCollection to refresh observable list
            MovieData.getInstance().refreshMovieList();

            // Refresh the card UI
            if (refreshCallback != null) {
                refreshCallback.run();
            }

            // Show success message
            showSuccess("Movie updated successfully!");

            closeWindow();

        } catch (NumberFormatException e) {
            showError("Release date must be a valid year.");
        }
    }

    /**
     * Closes the edit movie window.
     */
    @FXML
    private void closeWindow() {
        Stage stage = (Stage) titleField.getScene().getWindow();
        stage.close();
    }

    /**
     * Displays an error dialog with the specified message.
     *
     * @param msg the error message to display
     */
    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    /**
     * Displays a success dialog with the specified message.
     *
     * @param msg the success message to display
     */
    private void showSuccess(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    /**
     * Sets a callback to be executed after the movie is successfully updated.
     * This is typically used to refresh the UI in the parent view.
     *
     * @param callback the Runnable to execute after update
     */
    public void setOnMovieUpdated(Runnable callback) {
        this.refreshCallback = callback;
    }
}