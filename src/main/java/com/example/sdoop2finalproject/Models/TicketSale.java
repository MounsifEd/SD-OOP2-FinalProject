package com.example.sdoop2finalproject.Models;

import java.time.LocalDate;

public class TicketSale {
    private String movieTitle;
    private LocalDate date;
    private String showtime;
    private String room;
    private int ticketsSold;
    private double pricePerTicket;
    private double totalRevenue;

    public TicketSale(String movieTitle, LocalDate date, String showtime, int ticketsSold, double pricePerTicket) {
        this.movieTitle = movieTitle;
        this.date = date;
        this.showtime = showtime;
        this.ticketsSold = ticketsSold;
        this.pricePerTicket = pricePerTicket;
        this.totalRevenue = ticketsSold * pricePerTicket;
    }

    // Overloaded constructor including room for client ticket entries
    public TicketSale(String movieTitle, LocalDate date, String showtime, int ticketsSold, double pricePerTicket, String room) {
        this(movieTitle, date, showtime, ticketsSold, pricePerTicket);
        this.room = room;
    }

    // Getters and Setters
    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getShowtime() {
        return showtime;
    }

    public void setShowtime(String showtime) {
        this.showtime = showtime;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(int ticketsSold) {
        this.ticketsSold = ticketsSold;
        this.totalRevenue = this.ticketsSold * this.pricePerTicket;
    }

    public double getPricePerTicket() {
        return pricePerTicket;
    }

    public void setPricePerTicket(double pricePerTicket) {
        this.pricePerTicket = pricePerTicket;
        this.totalRevenue = this.ticketsSold * this.pricePerTicket;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    @Override
    public String toString() {
        return String.format("%s on %s at %s: %d tickets sold, $%.2f total",
                movieTitle, date, showtime, ticketsSold, totalRevenue);
    }
}
