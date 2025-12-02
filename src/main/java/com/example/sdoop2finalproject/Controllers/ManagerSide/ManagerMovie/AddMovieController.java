package com.example.sdoop2finalproject.Controllers.ManagerSide.ManagerMovie;

import com.example.sdoop2finalproject.Models.Movie.Movie;
import com.example.sdoop2finalproject.Models.Movie.MovieData;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller responsible to add new movies.
 * See if the fields are correctly filled, if yes, it creates and add a movie object
 * @Author Mounsif
 */
public class AddMovieController {

    @FXML
    private TextField titleField;

    @FXML
    private TextField genreField;

    @FXML
    private TextField releaseField;


    /**
     * Handles the Add Movie button action.
     * Read inputs, if correct, add the new movie to MovieData,
     * and close the add movie view.
     */
    @FXML
    private void addMovie() {
        int releaseYear;

        String title = titleField.getText().trim();
        String genre = genreField.getText().trim();
        String release = releaseField.getText().trim();

        int nextID = MovieData.getInstance().getMovies().getMovies().size() + 1;


        if (!title.isEmpty() && !genre.isEmpty() && !release.isEmpty()) {
            try {
                releaseYear = Integer.parseInt(release);
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please Enter a Valid Year. (Release must be numbers)");
                alert.showAndWait();
                return;
            }

            Movie newMovie = new Movie(nextID, title, releaseYear, genre);
            MovieData.getInstance().getMovies().addMovie(newMovie);
            ((Stage) titleField.getScene().getWindow()).close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill all fields");
            alert.showAndWait();
        }
    }

    /**
     * Close the add movie view when clicking Cancel
     */
    @FXML
    private void closeMovie() {
        ((Stage) titleField.getScene().getWindow()).close();
    }
}
