package com.example.sdoop2finalproject.Models.ShowRooms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @Author Mounsif
 */
public class RoomCollection {
    private final ObservableList<MovieRoom> aRooms = FXCollections.observableArrayList();

    /** @return list of all rooms */
    public ObservableList<MovieRoom> getRooms() {
        return aRooms;
    }

    /** Adds a room */
    public void addRoom(MovieRoom room) {
        aRooms.add(room);
    }

    /** Removes a room */
    public void removeRoom(MovieRoom room) {
        aRooms.remove(room);
    }

    /** Finds room by ID */
    public MovieRoom findRoomByID(int id) {
        return aRooms.stream()
                .filter(r -> r.getRoomID() == id)
                .findFirst()
                .orElse(null);
    }
}
