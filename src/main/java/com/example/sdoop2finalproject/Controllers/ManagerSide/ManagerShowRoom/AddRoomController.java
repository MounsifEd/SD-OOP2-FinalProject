package com.example.sdoop2finalproject.Controllers.ManagerSide.ManagerShowRoom;

import com.example.sdoop2finalproject.Models.ShowRooms.MovieRoom;
import com.example.sdoop2finalproject.Models.ShowRooms.RoomData;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *Controller responsible to add new rooms.
 * See if the fields are correctly filled, if yes, it creates and add a room object
 * @Author Mounsif
 */
public class AddRoomController {
    @FXML
    private TextField roomNumber;

    @FXML
    private TextField roomCapacity;



    /**
     * Method called when the manager click on the add button.
     * See if both fields are filled,
     * make sure that the inputs are only positive numbers,
     * send an alert if trying to add an existing room.
     */
    @FXML
    private void addRoom() {
        int id;
        int capacity;
        String numberText = roomNumber.getText().trim();
        String capacityText = roomCapacity.getText().trim();

        if (numberText.isEmpty() || capacityText.isEmpty()) {
            showError("Please fill both fields.");
            return;
        }

        try {
            id = Integer.parseInt(numberText);
            capacity = Integer.parseInt(capacityText);
        } catch (NumberFormatException e) {
            showError("Room number and capacity must be valid numbers.");
            return;
        }

        if (id <= 0 || capacity <= 0) {
            showError("Invalid room number or  room capacity. Please enter positive number.");
            return;
        }

        for (MovieRoom room : RoomData.getInstance().getRooms().getRooms()) {
            if (room.getRoomID() == id) {
                showError("This room already exists.");
                return;
            }
        }

        MovieRoom newRoom = new MovieRoom(id, capacity);
        RoomData.getInstance().getRooms().addRoom(newRoom);

        ((Stage) roomNumber.getScene().getWindow()).close();
    }

    /**
     * Close the add room view when clicking on the cancel button.
     */
    @FXML
    private void closeWindow() {
        ((Stage) roomNumber.getScene().getWindow()).close();
    }

    /**
     * Shows the error alert with the message given.
     * @param msg message to display in the content of the alert.
     */
    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
