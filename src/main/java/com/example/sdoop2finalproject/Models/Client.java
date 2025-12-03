package com.example.sdoop2finalproject.Models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Client model representing a customer of the cinema.
 * Clients can register, log in, purchase tickets, and view their purchased tickets.
 */
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    private int clientId;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String phoneNumber;
    private boolean active = true;
    private LocalDateTime registrationDate = LocalDateTime.now();
    private LocalDateTime lastLoginDate;

    // Using ticket IDs to avoid coupling to a Ticket model.
    private List<Integer> purchasedTicketIds = new ArrayList<>();

    public Client() {
    }

    public Client(String firstName, String lastName, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Client(int clientId, String firstName, String lastName, String email, String username, String password, String phoneNumber) {
        this(firstName, lastName, email, username, password);
        this.clientId = clientId;
        this.phoneNumber = phoneNumber;
    }

    // Getters and setters
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public List<Integer> getPurchasedTicketIds() {
        return purchasedTicketIds;
    }

    public void setPurchasedTicketIds(List<Integer> purchasedTicketIds) {
        this.purchasedTicketIds = purchasedTicketIds;
    }

    // Business helpers
    public void addTicketId(int ticketId) {
        this.purchasedTicketIds.add(ticketId);
    }

    public void removeTicketId(Integer ticketId) {
        this.purchasedTicketIds.remove(ticketId);
    }

    public int getTotalTicketsPurchased() {
        return purchasedTicketIds.size();
    }

    public String getFullName() {
        return (firstName == null ? "" : firstName) + " " + (lastName == null ? "" : lastName);
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", active=" + active +
                ", registrationDate=" + registrationDate +
                ", totalTickets=" + purchasedTicketIds.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        return clientId == client.clientId &&
                Objects.equals(email, client.email) &&
                Objects.equals(username, client.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, email, username);
    }
}
