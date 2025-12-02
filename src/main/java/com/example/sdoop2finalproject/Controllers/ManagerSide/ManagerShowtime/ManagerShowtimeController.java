package com.example.sdoop2finalproject.Controllers.ManagerSide.ManagerShowtime;

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
import javafx.stage.Stage;

/**
 * Displays all the showitmes for a selected movie
 * and give access to the manager to add a showtime.
 * @Author Mounsif
 */
public class ManagerShowtimeController {
    @FXML
    private FlowPane managerShowtimeFlow;

    @FXML
    private Button addShowtime;

    @FXML
    private Button showtimeBackButton;

    private Movie aMovie;
    /**
     * Run automatically whem the view is opened.
     * Sets actions for the add showtime and back buttons.
     */
    @FXML
    public void initialize() {
        addShowtime.setOnAction(e -> openAddShowtimeView());
        showtimeBackButton.setOnAction(e -> closeShowtimeView());
    }

    /**
     * Displays showtimes cards inside a Flowpane.
     * @param pMovie that the showtime is for.
     */
    public void loadShowtimes(Movie pMovie) {
        this.aMovie = pMovie;
        managerShowtimeFlow.getChildren().clear();

        for (MovieShow show : ShowtimeData.getInstance().getShows().getShowByMovieID(pMovie.getaMovieID())) {
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/com/example/sdoop2finalproject/ManagerShowtime/manager-showtime-card.fxml")
                );

                HBox card = loader.load();
                ManagerShowtimeCardController controller = loader.getController();
                controller.setData(show);

                managerShowtimeFlow.getChildren().add(card);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Open the add showtime view and give the selected movie to it.
     * When the view closes the list refresh.
     */
    private void openAddShowtimeView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/example/sdoop2finalproject/ManagerShowtime/add-showtime.fxml"
            ));

            Parent root = loader.load();

            // Pass the movie
            AddShowtimeController controller = loader.getController();
            controller.setMovie(aMovie);

            Stage stage = new Stage();
            stage.setTitle("Add Showtime");
            stage.setScene(new Scene(root));

            stage.setOnHidden(e -> loadShowtimes(aMovie));

            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Close the showtime manager view.
     */
    @FXML
    private void closeShowtimeView() {
        Stage stage = (Stage) showtimeBackButton.getScene().getWindow();
        stage.close();
    }

}
