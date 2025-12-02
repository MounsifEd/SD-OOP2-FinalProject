package com.example.sdoop2finalproject.Controllers.ManagerSide.ManagerShowtime;

import com.example.sdoop2finalproject.Models.Showtimes.MovieShow;
import com.example.sdoop2finalproject.Models.Showtimes.ShowtimeData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Displays showtime information and buttons as a card for each showtime object.
 * @Author Mounsif
 */
public class ManagerShowtimeCardController {
    @FXML
    private Text dateText;

    @FXML
    private Text timeText;

    @FXML
    private Text roomText;

    @FXML
    private Button showtimeEdit;

    @FXML
    private Button showtimeRemove;

    private MovieShow aShow;

    @FXML
    private HBox showtimeCard;

    /**
     * Run automatically when the card is loaded.
     * Sets actions for the remove and edit button.
     */
    @FXML
    public void initialize() {
        showtimeRemove.setOnAction(e -> deleteShowtime());
        showtimeEdit.setOnAction(e -> editShowtime());
    }

    /**
     * Fills the showtime card with its information.
     * @param show that this card represents
     */
    public void setData(MovieShow show) {
        this.aShow = show;

        dateText.setText(show.getShowDate());
        timeText.setText(show.getAshowTime());
        roomText.setText("Room " + show.getRoomID());
    }

    /**
     * Delete the showtime from ShowtimeData,
     * and removes the card created.
     */
    private void deleteShowtime() {
        ShowtimeData.getInstance().getShows().removeShow(aShow);

        FlowPane parent = (FlowPane) showtimeCard.getParent();
        parent.getChildren().remove(showtimeCard);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Showtime Removed");
        alert.setHeaderText(null);
        alert.setContentText("Showtime was successfully removed.");
        alert.showAndWait();
    }

    /**
     * Refreshes the card's UI elements with the current showtime data.
     * Called after the showtime has been edited to reflect updated information.
     */
    private void refreshUI() {
        dateText.setText(aShow.getShowDate());
        timeText.setText(aShow.getAshowTime());
        roomText.setText("Room " + aShow.getRoomID());
    }

    /**
     * Opens the edit showtime dialog window.
     * Loads the editshowtime.fxml view, passes the current showtime data to the
     * EditShowtimeController, and sets up a callback to refresh the card UI
     * after successful updates.
     *
     * <p>If an error occurs while loading the FXML, the exception is
     * printed to the console.</p>
     */
    private void editShowtime() {
        try {
            // Load the edit showtime FXML
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/sdoop2finalproject/ManagerShowtime/edit-showtime.fxml")
            );

            Parent root = loader.load();

            // Get the controller and configure it
            EditShowtimeController controller = loader.getController();

            // Pass current showtime to edit window
            controller.setShowtime(aShow);

            // Pass a callback so the card can refresh UI after update
            controller.setOnShowtimeUpdated(this::refreshUI);

            // Create and show the edit window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Edit Showtime");
            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
