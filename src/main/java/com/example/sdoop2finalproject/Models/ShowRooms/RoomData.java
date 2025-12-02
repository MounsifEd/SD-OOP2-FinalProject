package com.example.sdoop2finalproject.Models.ShowRooms;

import javafx.collections.FXCollections;

/**
 * @Author Mounsif
 */
public class RoomData {
    private static final RoomData instance = new RoomData();
    private final RoomCollection roomCollection = new RoomCollection();

    private RoomData() {
        loadTestRooms();
    }

    public static RoomData getInstance() {
        return instance;
    }

    public RoomCollection getRooms() {
        return roomCollection;
    }

    private void loadTestRooms() {
        roomCollection.addRoom(new MovieRoom(1, 220));
        roomCollection.addRoom(new MovieRoom(2, 120));
        roomCollection.addRoom(new MovieRoom(3, 40));
        roomCollection.addRoom(new MovieRoom(4, 150));
    }

    public void refreshRoomList() {
        var list = roomCollection.getRooms();
        var copy = FXCollections.observableArrayList(list);
        list.setAll(copy);
    }
}
