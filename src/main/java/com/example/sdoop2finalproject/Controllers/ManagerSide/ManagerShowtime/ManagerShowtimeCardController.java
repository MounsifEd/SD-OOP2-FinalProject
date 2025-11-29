package com.example.sdoop2finalproject.Controllers.ManagerSide.ManagerShowtime;

import com.example.sdoop2finalproject.Models.Showtimes.MovieShow;
import com.example.sdoop2finalproject.Models.Showtimes.ShowtimeData;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

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

    private void editShowtime() {

    }
}
