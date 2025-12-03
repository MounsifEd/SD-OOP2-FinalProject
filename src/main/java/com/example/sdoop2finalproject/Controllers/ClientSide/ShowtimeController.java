package com.example.sdoop2finalproject.Controllers.ClientSide;

import com.example.sdoop2finalproject.Models.Movie.Movie;
import com.example.sdoop2finalproject.Models.Showtimes.MovieShow;
import com.example.sdoop2finalproject.Models.Showtimes.ShowtimeData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

/**
 * Controller that displays showtimes for a selected movie.
 * @Author Mounsif
 */
public class ShowtimeController {
    /**
     * Contains all showtime cards.
     */
    @FXML
    private FlowPane showtimeFlow;

    /**
     * Display the movie title for a selected movie.
     */
    @FXML
    private Text showtimeTitle;

    /**
     * Close the showtimes screen.
     */
    @FXML
    private Button showtimeBackButton;
    /**
     * The movie that the showtime is currently displaying.
     */
    private Movie aMovie;

    /**
     * This method gives the action to the Back button, so clicking it
     * closes the showtime window.
     */
    @FXML
    private void initialize() {
        showtimeBackButton.setOnAction(e ->goBack());
    }

    /**
     * Loads and displays all showtimes for the given movie.
     * @param pMovie for which showtimes will be shown.
     */
    public void loadShowtimes(Movie pMovie) {

        this.aMovie = pMovie;

        showtimeTitle.setText(pMovie.getaMovieName());

        showtimeFlow.getChildren().clear();

        for (MovieShow show : ShowtimeData.getInstance()
                .getShows()
                .getShowByMovieID(pMovie.getaMovieID())) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sdoop2finalproject/ClientSide/showtime-card.fxml"));
                HBox row = loader.load();

                ShowtimeCardController controller = loader.getController();
                controller.setData(show);

                showtimeFlow.getChildren().add(row);

            } catch (IOException e) {
                throw new RuntimeException("Unable to load showtime row.", e);
            }
        }
    }

    /**
     * Close the current screen.
     */
    private void goBack() {
        // Get the current window and close it
        Stage stage = (Stage) showtimeBackButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Log out: open login view and close showtime + any other windows (e.g., Movie window).
     */
    @FXML
    public void openLoginView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sdoop2finalproject/login-view.fxml"));
            Parent root = loader.load();

            // Open login in a new Stage
            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root, 929, 648));
            loginStage.setTitle("Log In");
            loginStage.show();

            // Explicitly close the current showtime window
            Stage currentShowtime = (Stage) showtimeFlow.getScene().getWindow();
            if (currentShowtime != null && currentShowtime != loginStage) {
                currentShowtime.close();
            }

            // Close any other open windows except the new login window
            for (Window w : Window.getWindows()) {
                if (w instanceof Stage) {
                    Stage s = (Stage) w;
                    if (s != loginStage) {
                        s.close();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to load login view", e);
        }
    }
}
