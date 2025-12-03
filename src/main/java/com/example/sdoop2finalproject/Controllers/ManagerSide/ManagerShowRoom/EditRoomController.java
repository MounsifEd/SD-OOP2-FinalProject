package com.example.sdoop2finalproject.Controllers.ManagerSide.ManagerShowRoom;

import com.example.sdoop2finalproject.Models.ShowRooms.MovieRoom;
import com.example.sdoop2finalproject.Models.ShowRooms.RoomData;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for the edit room view.
 * Allows users to modify room ID and seating capacity of an existing movie room.
 *
 * @author Alejandro
 */
public class EditRoomController {

    /** Text field for entering/editing the room ID */
    @FXML
    private TextField roomNumber;

    /** Text field for entering/editing the room capacity */
    @FXML
    private TextField roomCapacity;

    /** The room object being edited */
    private MovieRoom selectedRoom;

    /** Callback to refresh the UI after successful update */
    private Runnable refreshCallback;

    /**
     * Initializes the controller.
     * Called automatically after the FXML file has been loaded.
     */
    @FXML
    private void initialize() {}

    /**
     * Sets the room to be edited and populates the text fields with its current data.
     *
     * @param room the MovieRoom object to edit
     */
    public void setRoom(MovieRoom room) {
        this.selectedRoom = room;
        roomNumber.setText(String.valueOf(room.getRoomID()));
        roomCapacity.setText(String.valueOf(room.getNumberOfSeats()));
    }

    /**
     * Updates the room with new values from the text fields.
     * Validates input, updates the room object, refreshes the data,
     * and closes the edit window on success.
     */
    @FXML
    private void updateRoom() {
        if (selectedRoom == null) {
            showError("Error: No room loaded!");
            return;
        }

        try {
            int newRoomID = Integer.parseInt(roomNumber.getText().trim());
            int newCapacity = Integer.parseInt(roomCapacity.getText().trim());

            // Validate capacity
            if (newCapacity <= 0) {
                showError("Capacity must be greater than 0.");
                return;
            }

            // Update existing object
            selectedRoom.setRoomID(newRoomID);
            selectedRoom.setNumberOfSeats(newCapacity);

            // Notify RoomCollection to refresh observable list
            RoomData.getInstance().refreshRoomList();

            // Refresh the card UI
            if (refreshCallback != null) {
                refreshCallback.run();
            }

            // Show success message
            showSuccess("Room updated successfully!");

            closeWindow();

        } catch (NumberFormatException e) {
            showError("Room number and capacity must be valid numbers.");
        }
    }

    /**
     * Closes the edit room window.
     */
    @FXML
    private void closeWindow() {
        Stage stage = (Stage) roomNumber.getScene().getWindow();
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
     * Sets a callback to be executed after the room is successfully updated.
     * This is typically used to refresh the UI in the parent view.
     *
     * @param callback the Runnable to execute after update
     */
    public void setOnRoomUpdated(Runnable callback) {
        this.refreshCallback = callback;
    }
}