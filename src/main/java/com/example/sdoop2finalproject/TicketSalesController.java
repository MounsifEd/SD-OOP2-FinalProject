package com.example.sdoop2finalproject;

import com.example.sdoop2finalproject.Models.Movie.Movie;
import com.example.sdoop2finalproject.Models.Movie.MovieData;
import com.example.sdoop2finalproject.Models.Showtimes.MovieShow;
import com.example.sdoop2finalproject.Models.Showtimes.ShowtimeData;
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

import java.util.List;
import java.util.stream.Collectors;

/**
 * TicketSalesController
 *
 * A small, easy-to-read controller for the Ticket Sales screen.
 * It lets you filter showtimes by Movie and Time using two dropdowns.
 * Dates are displayed but not used in filtering.
 *
 * Author: pascale
 */
public class TicketSalesController {

    // "All" options for dropdowns
    private static final String ALL_MOVIES = "All Movies";
    private static final String ALL_TIMES  = "All Times";

    // UI references
    @FXML private Button goBackButton;

    @FXML private ComboBox<String> movieComboBox;
    @FXML private DatePicker startDatePicker; // UI-only (not used for filtering)
    @FXML private DatePicker endDatePicker;   // UI-only (not used for filtering)
    @FXML private ComboBox<String> showtimeComboBox;

    @FXML private Button applyFiltersButton;
    @FXML private Button clearFiltersButton;

    @FXML private TableView<TicketSaleRow> salesTableView;
    @FXML private TableColumn<TicketSaleRow, String> movieColumn;
    @FXML private TableColumn<TicketSaleRow, String> dateColumn;
    @FXML private TableColumn<TicketSaleRow, String> showtimeColumn;
    @FXML private TableColumn<TicketSaleRow, Number> ticketsSoldColumn;

    @FXML private Label totalTicketsLabel;

    // Backing data for the table
    private final ObservableList<TicketSaleRow> masterRows = FXCollections.observableArrayList();

    /**
     * Sets up the table, loads data, configures the filters,
     * and shows everything by default.
     */
    @FXML
    private void initialize() {
        setupColumns();
        loadData();
        setupFilters();
        attachListeners();
        showAllRows();
    }

    // ----- Setup helpers -----

    /**
     * Wire up table columns to our row model.
     */
    private void setupColumns() {
        movieColumn.setCellValueFactory(c -> c.getValue().movie());
        dateColumn.setCellValueFactory(c -> c.getValue().date());
        showtimeColumn.setCellValueFactory(c -> c.getValue().showtime());
        ticketsSoldColumn.setCellValueFactory(c -> c.getValue().ticketsSold());
    }

    /**
     * Load all showtimes from the data layer into a simple table model.
     */
    private void loadData() {
        masterRows.clear();

        var movies = MovieData.getInstance().getMovies().getMovies();
        for (Movie movie : movies) {
            var shows = ShowtimeData.getInstance()
                    .getShows()
                    .getShowByMovieID(movie.getaMovieID());

            for (MovieShow show : shows) {
                // There is no real sales source wired here, so tickets default to 0
                masterRows.add(new TicketSaleRow(
                        movie.getaMovieName(),
                        show.getShowDate(),
                        show.getAshowTime(),
                        0
                ));
            }
        }
    }

    /**
     * Fill dropdowns and pick sensible defaults.
     */
    private void setupFilters() {
        populateMovies();
        movieComboBox.getSelectionModel().select(ALL_MOVIES);

        populateShowtimesForSelectedMovie(ALL_MOVIES);
        showtimeComboBox.getSelectionModel().select(ALL_TIMES);
    }

    /**
     * Keep the time list in sync when the movie selection changes.
     */
    private void attachListeners() {
        movieComboBox.valueProperty().addListener((obs, oldVal, newVal) ->
                populateShowtimesForSelectedMovie(newVal));
    }

    // ----- Dropdown population -----

    /**
     * Put all movie names into the movie dropdown,
     * with an "All Movies" option at the top.
     */
    private void populateMovies() {
        var names = MovieData.getInstance()
                .getMovies()
                .getMovies()
                .stream()
                .map(Movie::getaMovieName)
                .collect(Collectors.toList());

        var items = FXCollections.<String>observableArrayList();
        items.add(ALL_MOVIES);
        items.addAll(names);
        movieComboBox.setItems(items);
    }

    /**
     * For the selected movie (or all), collect distinct showtimes and
     * put them in the time dropdown, including an "All Times" option.
     */
    private void populateShowtimesForSelectedMovie(String movieName) {
        List<String> times;

        if (movieName == null || movieName.isBlank() || ALL_MOVIES.equals(movieName)) {
            times = MovieData.getInstance()
                    .getMovies()
                    .getMovies()
                    .stream()
                    .flatMap(m -> ShowtimeData.getInstance()
                            .getShows()
                            .getShowByMovieID(m.getaMovieID())
                            .stream())
                    .map(MovieShow::getAshowTime)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
        } else {
            var movie = findMovieByName(movieName);
            times = (movie == null) ? List.of()
                    : ShowtimeData.getInstance()
                    .getShows()
                    .getShowByMovieID(movie.getaMovieID())
                    .stream()
                    .map(MovieShow::getAshowTime)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
        }

        var timeItems = FXCollections.<String>observableArrayList();
        timeItems.add(ALL_TIMES);
        timeItems.addAll(times);
        showtimeComboBox.setItems(timeItems);
    }

    // ----- Actions -----

    /**
     * Filter the table by the selected movie and/or time.
     * Choosing an "All" option leaves that filter out.
     */
    @FXML
    private void handleApplyFilters() {
        String selectedMovie = movieComboBox.getValue();
        String selectedTime  = showtimeComboBox.getValue();

        boolean byMovie = selectedMovie != null && !selectedMovie.isBlank() && !ALL_MOVIES.equals(selectedMovie);
        boolean byTime  = selectedTime  != null && !selectedTime.isBlank()  && !ALL_TIMES.equals(selectedTime);

        var filtered = masterRows.stream()
                .filter(r -> !byMovie || r.movie().get().equals(selectedMovie))
                .filter(r -> !byTime  || r.showtime().get().equals(selectedTime))
                .collect(Collectors.toList());

        updateTable(filtered);
    }

    /**
     * Reset filters to "All" and show everything again.
     * Date pickers are just cleared since they're for display only.
     */
    @FXML
    private void handleClearFilters() {
        movieComboBox.getSelectionModel().select(ALL_MOVIES);
        populateShowtimesForSelectedMovie(ALL_MOVIES);
        showtimeComboBox.getSelectionModel().select(ALL_TIMES);

        // Dates are UI-only
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);

        showAllRows();
    }

    /**
     * Close the window and go back.
     */
    @FXML
    private void handleGoBack() {
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        stage.close();
    }

    // ----- Small utilities -----

    private void showAllRows() {
        updateTable(masterRows);
    }

    private void updateTable(List<TicketSaleRow> rows) {
        salesTableView.setItems(FXCollections.observableArrayList(rows));
        updateTotalTickets(rows);
    }

    private void updateTotalTickets(List<TicketSaleRow> rows) {
        int total = rows.stream().mapToInt(r -> r.ticketsSold().get()).sum();
        totalTicketsLabel.setText(String.valueOf(total));
    }

    private Movie findMovieByName(String name) {
        for (Movie m : MovieData.getInstance().getMovies().getMovies()) {
            if (m.getaMovieName().equals(name)) return m;
        }
        return null;
    }

    /**
     * Simple row model for the table.
     * We only keep what's shown: movie, date, time, and a tickets count.
     */
    public static class TicketSaleRow {
        private final SimpleStringProperty movie     = new SimpleStringProperty();
        private final SimpleStringProperty date      = new SimpleStringProperty();
        private final SimpleStringProperty showtime  = new SimpleStringProperty();
        private final SimpleIntegerProperty ticketsSold = new SimpleIntegerProperty();

        public TicketSaleRow(String movie, String date, String showtime, int ticketsSold) {
            this.movie.set(movie);
            this.date.set(date);
            this.showtime.set(showtime);
            this.ticketsSold.set(ticketsSold);
        }

        public SimpleStringProperty movie()       { return movie; }
        public SimpleStringProperty date()        { return date; }
        public SimpleStringProperty showtime()    { return showtime; }
        public SimpleIntegerProperty ticketsSold(){ return ticketsSold; }
    }
}
