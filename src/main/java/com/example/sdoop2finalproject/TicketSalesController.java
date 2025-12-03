package com.example.sdoop2finalproject;

import com.example.sdoop2finalproject.Models.Movie.Movie;
import com.example.sdoop2finalproject.Models.Movie.MovieData;
import com.example.sdoop2finalproject.Models.Showtimes.MovieShow;
import com.example.sdoop2finalproject.Models.Showtimes.ShowtimeData;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Controller for TicketSale-view.fxml.
 * Provides simple filtering by movie and showtime, populates the table
 * with available showtimes. Price/Ticket and Tickets Sold are placeholders (0),
 * as there is no sales data source in the current project.
 */
public class TicketSalesController {
    @FXML private Button goBackButton;

    @FXML private ComboBox<String> movieComboBox;
    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker endDatePicker;
    @FXML private ComboBox<String> showtimeComboBox;

    @FXML private Button applyFiltersButton;
    @FXML private Button clearFiltersButton;
    @FXML private Button allTimeButton;

    @FXML private TableView<TicketSaleRow> salesTableView;
    @FXML private TableColumn<TicketSaleRow, String> movieColumn;
    @FXML private TableColumn<TicketSaleRow, String> dateColumn;
    @FXML private TableColumn<TicketSaleRow, String> showtimeColumn;
    @FXML private TableColumn<TicketSaleRow, Number> ticketsSoldColumn;
    @FXML private TableColumn<TicketSaleRow, Number> pricePerTicketColumn;
    @FXML private TableColumn<TicketSaleRow, Number> totalRevenueColumn;

    @FXML private Label totalTicketsLabel;
    @FXML private Label totalRevenueLabel;

    private final ObservableList<TicketSaleRow> masterRows = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Setup columns
        movieColumn.setCellValueFactory(c -> c.getValue().movie());
        dateColumn.setCellValueFactory(c -> c.getValue().date());
        showtimeColumn.setCellValueFactory(c -> c.getValue().showtime());
        ticketsSoldColumn.setCellValueFactory(c -> c.getValue().ticketsSold());
        pricePerTicketColumn.setCellValueFactory(c -> c.getValue().pricePerTicket());
        totalRevenueColumn.setCellValueFactory(c -> c.getValue().totalRevenue());

        // Populate movie list
        populateMovies();

        // When a movie is selected, populate showtimes filter options
        movieComboBox.valueProperty().addListener((obs, ov, nv) -> populateShowtimesForSelectedMovie(nv));

        // Load the master rows from current showtimes (with placeholder sales values)
        loadAllRows();

        // Default: show all time
        salesTableView.setItems(FXCollections.observableArrayList(masterRows));
        updateTotals(salesTableView.getItems());
    }

    private void populateMovies() {
        List<String> names = MovieData.getInstance()
                .getMovies()
                .getMovies()
                .stream()
                .map(Movie::getaMovieName)
                .collect(Collectors.toList());
        movieComboBox.setItems(FXCollections.observableArrayList(names));
    }

    private void populateShowtimesForSelectedMovie(String movieName) {
        if (movieName == null || movieName.isBlank()) {
            showtimeComboBox.getItems().clear();
            return;
        }
        Movie movie = findMovieByName(movieName);
        if (movie == null) {
            showtimeComboBox.getItems().clear();
            return;
        }
        List<String> times = ShowtimeData.getInstance()
                .getShows()
                .getShowByMovieID(movie.getaMovieID())
                .stream()
                .map(MovieShow::getAshowTime)
                .distinct()
                .collect(Collectors.toList());
        showtimeComboBox.setItems(FXCollections.observableArrayList(times));
    }

    private void loadAllRows() {
        masterRows.clear();
        var movies = MovieData.getInstance().getMovies().getMovies();
        for (Movie movie : movies) {
            var shows = ShowtimeData.getInstance().getShows().getShowByMovieID(movie.getaMovieID());
            for (MovieShow show : shows) {
                // Placeholder: no sales source in project, set tickets/price to 0
                int tickets = 0;
                double price = 0.0;
                double revenue = tickets * price;

                masterRows.add(new TicketSaleRow(
                        movie.getaMovieName(),
                        show.getShowDate(),
                        show.getAshowTime(),
                        tickets,
                        price,
                        revenue
                ));
            }
        }
    }

    private Movie findMovieByName(String name) {
        for (Movie m : MovieData.getInstance().getMovies().getMovies()) {
            if (Objects.equals(m.getaMovieName(), name)) return m;
        }
        return null;
    }

    @FXML
    private void handleApplyFilters() {
        String selectedMovie = movieComboBox.getValue();
        String selectedTime = showtimeComboBox.getValue();

        List<TicketSaleRow> filtered = new ArrayList<>(masterRows);

        if (selectedMovie != null && !selectedMovie.isBlank()) {
            filtered = filtered.stream()
                    .filter(r -> r.movie().get().equals(selectedMovie))
                    .collect(Collectors.toList());
        }
        if (selectedTime != null && !selectedTime.isBlank()) {
            filtered = filtered.stream()
                    .filter(r -> r.showtime().get().equals(selectedTime))
                    .collect(Collectors.toList());
        }

        salesTableView.setItems(FXCollections.observableArrayList(filtered));
        updateTotals(filtered);
    }

    @FXML
    private void handleClearFilters() {
        movieComboBox.getSelectionModel().clearSelection();
        showtimeComboBox.getItems().clear();
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);
        salesTableView.setItems(FXCollections.observableArrayList(masterRows));
        updateTotals(masterRows);
    }

    @FXML
    private void handleShowAllTime() {
        salesTableView.setItems(FXCollections.observableArrayList(masterRows));
        updateTotals(masterRows);
    }

    @FXML
    private void handleGoBack() {
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        stage.close();
    }

    private void updateTotals(List<TicketSaleRow> rows) {
        int totalTickets = rows.stream().mapToInt(r -> r.ticketsSold().get()).sum();
        double totalRevenue = rows.stream().mapToDouble(r -> r.totalRevenue().get()).sum();
        totalTicketsLabel.setText(String.valueOf(totalTickets));
        totalRevenueLabel.setText(String.format("%.2f$", totalRevenue));
    }

    // Row model for the table
    public static class TicketSaleRow {
        private final SimpleStringProperty movie = new SimpleStringProperty();
        private final SimpleStringProperty date = new SimpleStringProperty();
        private final SimpleStringProperty showtime = new SimpleStringProperty();
        private final SimpleIntegerProperty ticketsSold = new SimpleIntegerProperty();
        private final SimpleDoubleProperty pricePerTicket = new SimpleDoubleProperty();
        private final SimpleDoubleProperty totalRevenue = new SimpleDoubleProperty();

        public TicketSaleRow(String movie, String date, String showtime, int ticketsSold, double pricePerTicket, double totalRevenue) {
            this.movie.set(movie);
            this.date.set(date);
            this.showtime.set(showtime);
            this.ticketsSold.set(ticketsSold);
            this.pricePerTicket.set(pricePerTicket);
            this.totalRevenue.set(totalRevenue);
        }

        public SimpleStringProperty movie() { return movie; }
        public SimpleStringProperty date() { return date; }
        public SimpleStringProperty showtime() { return showtime; }
        public SimpleIntegerProperty ticketsSold() { return ticketsSold; }
        public SimpleDoubleProperty pricePerTicket() { return pricePerTicket; }
        public SimpleDoubleProperty totalRevenue() { return totalRevenue; }
    }
}
