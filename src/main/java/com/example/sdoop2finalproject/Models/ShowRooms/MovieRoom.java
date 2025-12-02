package com.example.sdoop2finalproject.Models.ShowRooms;

/**
 * Represents a cinema room/theater within the application.
 * Stores information such as the room ID, name, and seating capacity.
 *
 * <p>This class provides getters and setters for all attributes,
 * and implements {@code equals()} and {@code hashCode()} based on the room ID.</p>
 *
 * @author Alejandro
 */
public class MovieRoom {

    /** Unique identifier for the cinema room. */
    private int aRoomID;  // Remove 'final' to allow updates

    /** The seating capacity of the room. */
    private int aRoomCapacity;  // Remove 'final' to allow updates

    /**
     * Creates a new MovieRoom object.
     *
     * @param pRoomID unique room ID
     * @param pNumberOfSeats number of seats in the room
     */
    public MovieRoom(int pRoomID, int pNumberOfSeats) {
        this.aRoomID = pRoomID;
        this.aRoomCapacity = pNumberOfSeats;
    }

    /** @return the room ID */
    public int getRoomID() {
        return aRoomID;
    }

    /** @return number of seats in the room */
    public int getNumberOfSeats() {
        return aRoomCapacity;
    }

    /** Sets the room ID */
    public void setRoomID(int pRoomID) {
        this.aRoomID = pRoomID;
    }

    /** Sets the number of seats */
    public void setNumberOfSeats(int pNumberOfSeats) {
        this.aRoomCapacity = pNumberOfSeats;
    }

    @Override
    public String toString() {
        return "Room " + aRoomID + " (" + aRoomCapacity + " seats)";
    }

    /** Two rooms are equal if they have the same ID. */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof MovieRoom)) return false;
        MovieRoom other = (MovieRoom) obj;
        return aRoomID == other.aRoomID;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(aRoomID);
    }
}