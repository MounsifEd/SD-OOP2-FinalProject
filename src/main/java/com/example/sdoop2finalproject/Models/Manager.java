package com.example.sdoop2finalproject.Models;

import java.io.Serializable;
import java.util.Objects;

/**
 * Simplified Manager model.
 * Only holds username and password needed for manager authentication.
 */
public class Manager implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    // Minimal helpers
    @Override
    public String toString() {
        return "Manager{username='" + username + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manager)) return false;
        Manager manager = (Manager) o;
        return Objects.equals(username, manager.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
