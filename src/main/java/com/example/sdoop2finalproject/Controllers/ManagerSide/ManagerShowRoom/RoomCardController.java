package com.example.sdoop2finalproject.Controllers.ManagerSide.ManagerShowRoom;

import com.example.sdoop2finalproject.Models.ShowRooms.MovieRoom;
import com.example.sdoop2finalproject.Models.ShowRooms.RoomData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Controller for individual room card components.
 * Displays room information and provides edit and delete functionality
 * for each movie room in the system.
 *
 * <p>Each card shows the room ID and seating capacity, with buttons
 * to edit or remove the room.</p>
 *
 * @author Mounsif
 */
public class RoomCardController {

    /** Text element displaying the room name/ID */
    @FXML
    private Text roomNameText;

    /** Text element displaying the room's seating capacity */
    @FXML
    private Text roomCapacityText;

    /** Button to open the edit room dialog */
    @FXML
    private Button editRoomButton;

    /** Button to remove the room from the system */
    @FXML
    private Button removeRoomButton;

    /** The MovieRoom object this card represents */
    private MovieRoom aRoom;

    /**
     * Initializes the controller after the FXML file has been loaded.
     * Sets up event handlers for the edit and delete buttons.
     */
    public void initialize() {
        removeRoomButton.setOnAction(_ -> deleteRoom());
        editRoomButton.setOnAction(_ -> editRoom());
    }

    /**
     * Populates the card with room information.
     * Updates the displayed room name and seating capacity.
     *
     * @param pRoom the MovieRoom object to display on this card
     */
    public void setData(MovieRoom pRoom) {
        this.aRoom = pRoom;
        roomNameText.setText("Room " + pRoom.getRoomID());
        roomCapacityText.setText("Seats: " + pRoom.getNumberOfSeats());
    }

    /**
     * Deletes the room from the system and removes the card from the UI.
     * Removes the room from RoomData, removes the card from its parent container,
     * and displays a confirmation dialog to the user.
     */
    private void deleteRoom() {
        // Remove from data model
        RoomData.getInstance().getRooms().removeRoom(aRoom);

        // Navigate up the scene graph to find the card VBox
        VBox card = (VBox) removeRoomButton.getParent().getParent();

        // Get the FlowPane container holding all cards
        FlowPane parentContainer = (FlowPane) card.getParent();

        // Remove this card from the container
        parentContainer.getChildren().remove(card);

        // Show confirmation dialog
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ShowRoom Removed");
        alert.setHeaderText(null);
        alert.setContentText("ShowRoom was successfully removed.");
        alert.showAndWait();
    }

    /**
     * Refreshes the card's UI elements with the current room data.
     * Called after the room has been edited to reflect updated information.
     */
    private void refreshUI() {
        roomNameText.setText("Room " + aRoom.getRoomID());
        roomCapacityText.setText("Seats: " + aRoom.getNumberOfSeats());
    }

    /**
     * Opens the edit room dialog window.
     * Loads the edit-room.fxml view, passes the current room data to the
     * EditRoomController, and sets up a callback to refresh the card UI
     * after successful updates.
     *
     * <p>If an error occurs while loading the FXML, the exception is
     * printed to the console.</p>
     */
    private void editRoom() {
        try {
            // Load the edit room FXML
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/sdoop2finalproject/ManagerRoom/edit-room.fxml")
            );
            Parent root = loader.load();

            // Get the controller and configure it
            EditRoomController controller = loader.getController();

            // Pass current room to edit window
            controller.setRoom(aRoom);

            // Pass a callback so the card can refresh UI after update
            controller.setOnRoomUpdated(this::refreshUI);

            // Create and show the edit window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Edit Showroom");
            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}