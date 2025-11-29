package com.example.sdoop2finalproject.Controllers.ManagerSide.ManagerShowRoom;

import com.example.sdoop2finalproject.Models.ShowRooms.MovieRoom;
import com.example.sdoop2finalproject.Models.ShowRooms.RoomData;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/**
 * Displays room information and buttons as a card for each room object.
 * @Author Mounsif
 */
public class RoomCardController {

    @FXML
    private Text roomNameText;

    @FXML
    private Text roomCapacityText;

    @FXML
    private Button editRoomButton;

    @FXML
    private Button removeRoomButton;

    private MovieRoom aRoom;

    /**
     * Run automatically when the card is loaded.
     * give actions for edit and delete buttons.
     */
    public void initialize() {
        removeRoomButton.setOnAction(e -> deleteRoom());
        editRoomButton.setOnAction(e -> editRoom());
    }

    /**
     * Fills the room card with its information.
     * @param pRoom the room that the card represents.
     */
    public void setData(MovieRoom pRoom) {
        this.aRoom = pRoom;
        roomNameText.setText("Room " + pRoom.getRoomID());
        roomCapacityText.setText("Seats: " + pRoom.getNumberOfSeats());
    }

    /**
     * It deletes the room for Roomdata and deletes the card.
     */
    private void deleteRoom() {
        RoomData.getInstance().getRooms().removeRoom(aRoom);

        VBox card = (VBox) removeRoomButton.getParent().getParent();

        FlowPane parentContainer = (FlowPane) card.getParent();

        parentContainer.getChildren().remove(card);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ShowRoom Removed");
        alert.setHeaderText(null);
        alert.setContentText("ShowRoom was successfully removed.");
        alert.showAndWait();
    }

    private void editRoom() {

    }
}
