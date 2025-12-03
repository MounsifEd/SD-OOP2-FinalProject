package com.example.sdoop2finalproject.Controllers.ManagerSide.ManagerMovie;

import com.example.sdoop2finalproject.Models.Movie.Movie;
import com.example.sdoop2finalproject.Models.Movie.MovieData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller that display all movies for the manager
 * @Author Mounsif
 */
public class ManagerController {

    @FXML
    private FlowPane movieManagerFlowPane;

    @FXML
    private Button addMovieButton;

    @FXML
    private Button showRoomManagerButton;

    /**
     * Run when the view is open,
     * it loads all current movies.
     * set actions to open the add movie view and
     * the room View
     */
    @FXML
    public void initialize() {
        loadMovies();
        addMovieButton.setOnAction(e -> openAddMovieView());
        showRoomManagerButton.setOnAction(e -> openRoomView());
    }

    /**
     * Loads all movies cards inside a flow pane.
     */
    private void loadMovies() {
        movieManagerFlowPane.getChildren().clear();

        for (Movie movie : MovieData.getInstance().getMovies().getMovies()) {
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/com/example/sdoop2finalproject/ManagerMovie/movie-manager-card.fxml")
                );

                VBox card = loader.load();

                MovieManagerCardController controller = loader.getController();
                controller.setMovieManagerData(movie);

                movieManagerFlowPane.getChildren().add(card);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Opens the add movie view.
     * When closed it refresh the movie cards.
     */
    @FXML
    private void openAddMovieView() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/sdoop2finalproject/ManagerMovie/add-movie.fxml")
            );

            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Add New Movie");

            stage.setOnHidden(e -> loadMovies());

            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens the room view.
     */
    private void openRoomView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sdoop2finalproject/ManagerRoom/show-rooms-view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Cinema Rooms");
            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Simple handler to return to the login view.
     */
    @FXML
    public void openLoginView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sdoop2finalproject/login-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) movieManagerFlowPane.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Log In");
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
