package com.example.sdoop2finalproject.Controllers.ManagerSide.ManagerShowtime;

import com.example.sdoop2finalproject.Models.ShowRooms.MovieRoom;
import com.example.sdoop2finalproject.Models.ShowRooms.RoomData;
import com.example.sdoop2finalproject.Models.Showtimes.MovieShow;
import com.example.sdoop2finalproject.Models.Showtimes.ShowtimeData;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Controller for the edit showtime view.
 * Allows users to modify showtime date, time, and room for an existing showtime.
 *
 * @author Alejandro
 */
public class EditShowtimeController {

    /** Text field for entering/editing the showtime date */
    @FXML
    private TextField dateField;

    /** Text field for entering/editing the showtime time */
    @FXML
    private TextField timeField;

    /** ComboBox for selecting the room */
    @FXML
    private ComboBox<MovieRoom> roomComboBox;

    /** The showtime object being edited */
    private MovieShow selectedShow;

    /** Callback to refresh the UI after successful update */
    private Runnable refreshCallback;

    /**
     * Initializes the controller after the FXML file has been loaded.
     * Loads all available rooms into the ComboBox.
     */
    @FXML
    private void initialize() {
        loadRooms();
    }

    /**
     * Loads all available rooms into the room ComboBox.
     * Displays rooms in the format "Room X (Y seats)".
     */
    private void loadRooms() {
        roomComboBox.getItems().clear();
        roomComboBox.getItems().addAll(RoomData.getInstance().getRooms().getRooms());

        // Custom display format for rooms
        roomComboBox.setCellFactory(lv -> new javafx.scene.control.ListCell<MovieRoom>() {
            @Override
            protected void updateItem(MovieRoom room, boolean empty) {
                super.updateItem(room, empty);
                setText(empty || room == null ? "" : room.toString());
            }
        });

        roomComboBox.setButtonCell(new javafx.scene.control.ListCell<MovieRoom>() {
            @Override
            protected void updateItem(MovieRoom room, boolean empty) {
                super.updateItem(room, empty);
                setText(empty || room == null ? "" : room.toString());
            }
        });
    }

    /**
     * Sets the showtime to be edited and populates the fields with its current data.
     *
     * @param show the MovieShow object to edit
     */
    public void setShowtime(MovieShow show) {
        this.selectedShow = show;
        dateField.setText(show.getShowDate());
        timeField.setText(show.getAshowTime());

        // Select the current room in the ComboBox
        MovieRoom currentRoom = RoomData.getInstance().getRooms().findRoomByID(show.getRoomID());
        if (currentRoom != null) {
            roomComboBox.setValue(currentRoom);
        }
    }

    /**
     * Updates the showtime with new values from the input fields.
     * Validates input, updates the showtime object, refreshes the data,
     * and closes the edit window on success.
     */
    @FXML
    private void updateShowtime() {
        if (selectedShow == null) {
            showError("Error: No showtime loaded!");
            return;
        }

        try {
            String newDate = dateField.getText().trim();
            String newTime = timeField.getText().trim();
            MovieRoom selectedRoom = roomComboBox.getValue();

            // Validate inputs
            if (newDate.isEmpty()) {
                showError("Date cannot be empty.");
                return;
            }

            if (newTime.isEmpty()) {
                showError("Time cannot be empty.");
                return;
            }

            if (selectedRoom == null) {
                showError("Please select a room.");
                return;
            }

            // Validate date format (YYYY-MM-DD)
            if (!isValidDate(newDate)) {
                showError("Invalid date format. Please use YYYY-MM-DD.");
                return;
            }

            // Validate time format (HH:MM)
            if (!isValidTime(newTime)) {
                showError("Invalid time format. Please use HH:MM (24-hour format).");
                return;
            }

            // Update the showtime object
            selectedShow.setShowDate(newDate);
            selectedShow.setShowTime(newTime);
            selectedShow.setRoomID(selectedRoom.getRoomID());

            // Refresh the observable list to trigger UI updates
            ShowtimeData.getInstance().refreshShowtimeList();

            // Refresh the card UI
            if (refreshCallback != null) {
                refreshCallback.run();
            }

            // Show success message
            showSuccess("Showtime updated successfully!");

            closeWindow();

        } catch (Exception e) {
            showError("An error occurred while updating the showtime.");
        }
    }

    /**
     * Validates if the given date string is in the correct format (YYYY-MM-DD).
     *
     * @param date the date string to validate
     * @return true if the date is valid, false otherwise
     */
    private boolean isValidDate(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Validates if the given time string is in the correct format (HH:MM).
     *
     * @param time the time string to validate
     * @return true if the time is valid, false otherwise
     */
    private boolean isValidTime(String time) {
        try {
            LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Closes the edit showtime window.
     */
    @FXML
    private void closeWindow() {
        Stage stage = (Stage) dateField.getScene().getWindow();
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
     * Sets a callback to be executed after the showtime is successfully updated.
     * This is typically used to refresh the UI in the parent view.
     *
     * @param callback the Runnable to execute after update
     */
    public void setOnShowtimeUpdated(Runnable callback) {
        this.refreshCallback = callback;
    }
}