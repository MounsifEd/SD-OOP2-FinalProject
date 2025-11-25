package com.example.sdoop2finalproject.Models;

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
    private int aRoomID;

    /** The display name of the cinema room. */
    private String aRoomName;

    /** The seating capacity of the cinema room. */
    private int aNumberOfSeats;

    /**
     * Constructs a MovieRoom object with the provided details.
     *
     * @param pRoomID the unique ID of the room
     * @param pRoomName the name assigned to the room
     * @param pNumberOfSeats the number of seats available in this room
     */
    public MovieRoom(int pRoomID, String pRoomName, int pNumberOfSeats) {
        this.aRoomID = pRoomID;
        this.aRoomName = pRoomName;
        this.aNumberOfSeats = pNumberOfSeats;
    }

    /**
     * Retrieves the unique room ID.
     *
     * @return the room ID
     */
    public int getRoomID() {
        return aRoomID;
    }

    /**
     * Duplicate getter (legacy naming).
     *
     * @return the room ID
     */
    public int getaRoomID() {
        return aRoomID;
    }

    /**
     * Updates the room ID.
     *
     * @param pRoomID the new room ID
     */
    public void setRoomID(int pRoomID) {
        this.aRoomID = pRoomID;
    }

    /**
     * Retrieves the room name.
     *
     * @return the room name
     */
    public String getRoomName() {
        return aRoomName;
    }

    /**
     * Updates the name of the room.
     *
     * @param pRoomName the new room name
     */
    public void setRoomName(String pRoomName) {
        this.aRoomName = pRoomName;
    }

    /**
     * Retrieves the number of seats in the room.
     *
     * @return the seating capacity
     */
    public int getNumberOfSeats() {
        return aNumberOfSeats;
    }

    /**
     * Duplicate getter (legacy naming).
     *
     * @return the seating capacity
     */
    public int getaNumberOfSeats() {
        return aNumberOfSeats;
    }

    /**
     * Updates the seating capacity for the room.
     *
     * @param pNumberOfSeats the new seating capacity
     */
    public void setNumberOfSeats(int pNumberOfSeats) {
        this.aNumberOfSeats = pNumberOfSeats;
    }

    /**
     * Returns a user-readable string representation of the MovieRoom.
     *
     * @return formatted room information including ID, name, and seats
     */
    @Override
    public String toString() {
        return "Room " + aRoomID + ": " + aRoomName + " (" + aNumberOfSeats + " seats)";
    }

    /**
     * Determines if two MovieRoom objects are equal.
     * Rooms are considered equal if their room IDs match.
     *
     * @param obj the object being compared
     * @return true if the room IDs match, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        MovieRoom room = (MovieRoom) obj;
        return aRoomID == room.aRoomID;
    }

    /**
     * Generates a hash code based on the room ID.
     *
     * @return the hash code for this room
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(aRoomID);
    }
}
