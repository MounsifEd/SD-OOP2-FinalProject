package com.example.sdoop2finalproject.Controllers;

import com.example.sdoop2finalproject.Models.MovieRoom;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

public class ManagerShowtimeController {

    @FXML
    private ComboBox<MovieRoom> roomComboBox;

    @FXML
    private TextField timeField;

    @FXML
    public void initialize() {
        roomComboBox.getItems().addAll(
                new MovieRoom(1, "Theatre A", 120),
                new MovieRoom(2, "Theatre B", 80),
                new MovieRoom(3, "VIP Lounge", 40)
        );

        // Show formatted text in dropdown
        roomComboBox.setCellFactory(listView -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(MovieRoom item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText("Room " + item.getRoomID() +
                            ": " + item.getRoomName() +
                            " (" + item.getNumberOfSeats() + " seats)");
                }
            }
        });

        roomComboBox.setButtonCell(new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(MovieRoom item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText("Room " + item.getRoomID() +
                            ": " + item.getRoomName() +
                            " (" + item.getNumberOfSeats() + " seats)");
                }
            }
        });
    }

    @FXML
    private void onCreateShowtime() {
        MovieRoom selectedRoom = roomComboBox.getValue();
        String time = timeField.getText().trim();

        if (selectedRoom == null || time.isEmpty()) {
            showAlert("Missing Information", "Please choose a room and enter a screening time.");
            return;
        }

        System.out.println("Creating showtime:");
        System.out.println("Room: " + selectedRoom);
        System.out.println("Time: " + time);

        showAlert("Success", "Showtime created successfully.");
        timeField.clear();
        roomComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void onCancel() {
        System.out.println("Cancelled");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
