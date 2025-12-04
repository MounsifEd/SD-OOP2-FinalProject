package com.example.sdoop2finalproject.Models;

import java.io.Serializable;
import java.util.Objects;

/**
 * Simplified Client model aligned with sign-up fields.
 *
 * Identifier strategy: Email is the current unique identifier.
 * clientId is kept for future transactional features.
 */
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Future transactional identifier (e.g., for tickets/orders).
     * Not used as a unique key right now.
     */
    private int clientId;

    private String firstName;
    private String lastName;

    /**
     * Current unique identifier used for login and equality.
     */
    private String email;

    /**
     * Note: In production, do not store raw passwords. Use hashing/salting.
     */
    private String password;


    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    /**
     * Equality is currently based on email, which acts as the unique identifier for clients.
     * This may change once a persistent clientId is introduced and populated.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return Objects.equals(email, client.email);
    }

    /**
     * Hash code aligned with equals(): based on email for the current phase.
     */
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
