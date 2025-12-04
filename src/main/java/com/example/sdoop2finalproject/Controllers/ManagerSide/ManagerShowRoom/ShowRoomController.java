package com.example.sdoop2finalproject.Controllers.ManagerSide.ManagerShowRoom;

import com.example.sdoop2finalproject.Models.ShowRooms.MovieRoom;
import com.example.sdoop2finalproject.Models.ShowRooms.RoomData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Displays all the rooms cards and give access to
 * manager to add room.
 * @Author Mounsif
 */
public class ShowRoomController {
    @FXML
    private FlowPane roomFlowPane;

    @FXML
    private Button addRoomButton;

    /**
     * Run automatically when the view is opened
     * Loads all rooms cards and set the action for the Add room button.
     */
    @FXML
    public void initialize() {
        loadRooms();
        addRoomButton.setOnAction(_ -> openAddRoomView());
    }

    /**
     * Displays rooms cards inside a Flowpane.
     */
    private void loadRooms() {
        roomFlowPane.getChildren().clear();

        for (MovieRoom room : RoomData.getInstance().getRooms().getRooms()) {

            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/com/example/sdoop2finalproject/ManagerRoom/show-rooms-card.fxml")
                );
                VBox card = loader.load();

                RoomCardController controller = loader.getController();
                controller.setData(room);

                roomFlowPane.getChildren().add(card);

            } catch (Exception e) {
                System.err.println("Failed to load: " + e.getMessage());
            }
        }
    }

    /**
     * Open the add room view.
     * When the view closes, the room list get a refresh.
     */
    @FXML
    private void openAddRoomView(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sdoop2finalproject/ManagerRoom/add-room.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setOnHidden(_ -> loadRooms());
            stage.show();
        } catch (IOException e) {
            System.err.println("Failed to load: " + e.getMessage());
        }
    }
}
