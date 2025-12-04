package com.example.sdoop2finalproject.Models.ShowRooms;

/**
 * Represents a cinema room/theater in the application.
 * <p>
 * A MovieRoom stores essential details such as its unique ID and seating capacity.
 * It provides getters and setters for modifying these attributes as needed.
 * </p>
 *
 * <p>
 * Two MovieRoom objects are considered equal if they have the same room ID,
 * and {@code hashCode()} is implemented accordingly.
 * </p>
 *
 * author Alejandro
 */
public class MovieRoom {

    /**
     * Unique identifier for the cinema room.
     */
    private int aRoomID;

    /**
     * The seating capacity of the room.
     */
    private int aRoomCapacity;

    /**
     * Creates a new MovieRoom object.
     *
     * @param pRoomID         the unique room ID
     * @param pNumberOfSeats  the number of seats in the room
     */
    public MovieRoom(int pRoomID, int pNumberOfSeats) {
        this.aRoomID = pRoomID;
        this.aRoomCapacity = pNumberOfSeats;
    }

    /**
     * Gets the room ID.
     *
     * @return the room ID
     */
    public int getRoomID() {
        return aRoomID;
    }

    /**
     * Gets the number of seats in the room.
     *
     * @return the seating capacity
     */
    public int getNumberOfSeats() {
        return aRoomCapacity;
    }

    /**
     * Sets the room ID.
     *
     * @param pRoomID the new room ID
     */
    public void setRoomID(int pRoomID) {
        this.aRoomID = pRoomID;
    }

    /**
     * Sets the seating capacity of the room.
     *
     * @param pNumberOfSeats the new number of seats
     */
    public void setNumberOfSeats(int pNumberOfSeats) {
        this.aRoomCapacity = pNumberOfSeats;
    }

    /**
     * Returns a formatted string representation of the room.
     *
     * @return a string in the format "Room X (Y seats)"
     */
    @Override
    public String toString() {
        return "Room " + aRoomID + " (" + aRoomCapacity + " seats)";
    }

    /**
     * Determines equality based on room ID.
     *
     * @param obj the object to compare to
     * @return true if the other object is a MovieRoom with the same ID
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof MovieRoom)) return false;
        MovieRoom other = (MovieRoom) obj;
        return aRoomID == other.aRoomID;
    }

    /**
     * Generates a hash code for this room based on its ID.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(aRoomID);
    }
}
