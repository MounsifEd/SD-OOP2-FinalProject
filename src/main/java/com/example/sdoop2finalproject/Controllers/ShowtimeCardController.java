package com.example.sdoop2finalproject.Controllers;

import com.example.sdoop2finalproject.Models.MovieShow;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * Controller for a single showtime card.
 * Controller for the showtime-card view.
 * @Author Mounsif
 */
public class ShowtimeCardController {
    /**
     * Display the date.
     */
    @FXML
    private Text dateText;

    /**
     * Display the time.
     */
    @FXML
    private Text timeText;

    /**
     * Display the room number.
     */
    @FXML
    private Text roomText;

    @FXML
    private Button selectBtn;


    /**
     * Change UI components.
     * @param pShow the showtime displayed in the row.
     */
    public void setData(MovieShow pShow) {
        dateText.setText(pShow.getShowDate());
        timeText.setText(pShow.getAshowTime());
        roomText.setText("Room " + pShow.getRoomID());

        selectBtn.setOnAction(e -> showUnavailablePaymentAlert());
    }

    /**
     * Shows an alert telling the user that payment is unavailable.
     */
    private void showUnavailablePaymentAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ticket Payment");
        alert.setHeaderText("Payment Unavailable");
        alert.setContentText("The payment method is currently unavailable. Please try later. ");
        alert.showAndWait();
    }

}

