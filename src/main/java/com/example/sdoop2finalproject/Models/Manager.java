package com.example.sdoop2finalproject.Models;

import com.example.sdoop2finalproject.Models.Movie.Movie;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Manager model representing a staff user with elevated privileges.
 * Managers can log in, manage movies/rooms, and track ticket sales per movie.
 *
 * Note: This model intentionally excludes position, department, and hire date.
 */
public class Manager implements Serializable {
    private static final long serialVersionUID = 1L;

    private int managerId;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private boolean active = true;
    private LocalDateTime lastLoginDate;

    // The IDs of movies/rooms the manager is responsible for.
    private final Set<Integer> managedMovieIds = new HashSet<>();
    private final Set<Integer> managedRoomIds = new HashSet<>();

    // Aggregated sales metrics by movieId.
    private final Map<Integer, Integer> ticketsSoldByMovie = new HashMap<>();
    private final Map<Integer, Double> revenueByMovie = new HashMap<>();

    public Manager() {
    }

    public Manager(String firstName, String lastName, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }


    // Getters and setters
    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Note: In production, do not store raw passwords. Use hashing/salting.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Note: In production, do not store raw passwords. Use hashing/salting.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Set<Integer> getManagedMovieIds() {
        return managedMovieIds;
    }

    public Set<Integer> getManagedRoomIds() {
        return managedRoomIds;
    }

    public Map<Integer, Integer> getTicketsSoldByMovie() {
        return ticketsSoldByMovie;
    }

    public Map<Integer, Double> getRevenueByMovie() {
        return revenueByMovie;
    }

    // Management helpers
    public void addManagedMovieId(int movieId) {
        managedMovieIds.add(movieId);
    }

    public void removeManagedMovieId(int movieId) {
        managedMovieIds.remove(movieId);
    }

    public void addManagedMovie(Movie movie) {
        if (movie != null) {
            managedMovieIds.add(movie.getaMovieID());
        }
    }

    public void removeManagedMovie(Movie movie) {
        if (movie != null) {
            managedMovieIds.remove(movie.getaMovieID());
        }
    }

    public boolean canManageMovieId(int movieId) {
        return managedMovieIds.contains(movieId);
    }

    public boolean canManageMovie(Movie movie) {
        return movie != null && managedMovieIds.contains(movie.getaMovieID());
    }

    public void addManagedRoomId(int roomId) {
        managedRoomIds.add(roomId);
    }

    public void removeManagedRoomId(int roomId) {
        managedRoomIds.remove(roomId);
    }

    public boolean canManageRoomId(int roomId) {
        return managedRoomIds.contains(roomId);
    }

    // Sales tracking helpers
    public void recordSaleForMovie(int movieId, int ticketsSold, double revenue) {
        if (ticketsSold < 0 || revenue < 0) return;
        ticketsSoldByMovie.merge(movieId, ticketsSold, Integer::sum);
        revenueByMovie.merge(movieId, revenue, Double::sum);
    }

    public int getTicketsSoldForMovie(int movieId) {
        return ticketsSoldByMovie.getOrDefault(movieId, 0);
    }

    public int getTicketsSoldForMovie(Movie movie) {
        return movie == null ? 0 : getTicketsSoldForMovie(movie.getaMovieID());
    }

    public double getRevenueForMovie(int movieId) {
        return revenueByMovie.getOrDefault(movieId, 0.0);
    }

    public double getRevenueForMovie(Movie movie) {
        return movie == null ? 0.0 : getRevenueForMovie(movie.getaMovieID());
    }

    public int getTotalTicketsSoldAllMovies() {
        return ticketsSoldByMovie.values().stream().mapToInt(Integer::intValue).sum();
    }

    public double getTotalRevenueAllMovies() {
        return revenueByMovie.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    public String getFullName() {
        return (firstName == null ? "" : firstName) + " " + (lastName == null ? "" : lastName);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "managerId=" + managerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", active=" + active +
                ", managedMovies=" + managedMovieIds.size() +
                ", managedRooms=" + managedRoomIds.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manager manager)) return false;
        return managerId == manager.managerId &&
                Objects.equals(email, manager.email) &&
                Objects.equals(username, manager.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(managerId, email, username);
    }
}
