package com.example.sdoop2finalproject.Controllers;

import com.example.sdoop2finalproject.Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Controller for the Edit Movie / Show view.
 * <p>
 * Responsibilities:
 * <ul>
 *     <li>Load movies from the MovieData singleton into the left list.</li>
 *     <li>When a movie is selected, load its fields into the form for editing.</li>
 *     <li>Allow adding, editing and deleting movies and showtime.</li>
 *     <li>Validate user input (simple checks) and show errors using alerts.</li>
 * </ul>
 */
public class EditMovieShowController {

    @FXML private ListView<Movie> movieListView;
    @FXML private TextField movieNameField;
    @FXML private TextField genreField;
    @FXML private TextField releaseDateField;
    @FXML private TextField priceField;
    @FXML private TextArea descriptionArea;
    @FXML private TextField newShowtimeField;
    @FXML private ListView<MovieShow> showtimeListView;
    @FXML private Button addMovieBtn;
    @FXML private Button editMovieBtn;
    @FXML private Button deleteMovieBtn;
    @FXML private Button addShowtimeBtn;
    @FXML private Button saveBtn;
    @FXML private Button cancelBtn;

    private final ObservableList<Movie> movies = FXCollections.observableArrayList();
    private final ObservableList<MovieShow> showtimes = FXCollections.observableArrayList();
    private final MovieData movieData = MovieData.getInstance();
    private final ShowtimeData showtimeData = ShowtimeData.getInstance();
    private int nextMovieID = 100; // Start from 100 to avoid conflicts with test data

    /**
     * Called automatically by JavaFX after FXML elements are injected.
     * Sets up list views and event listeners.
     */
    @FXML
    public void initialize() {
        // populate movies from MovieData singleton
        movies.setAll(movieData.getMovies().getMovies());
        movieListView.setItems(movies);

        // Find the highest movie ID to generate new IDs
        for (Movie m : movies) {
            if (m.getMovieID() >= nextMovieID) {
                nextMovieID = m.getMovieID() + 1;
            }
        }

        // showtime list is filled when a movie is selected
        showtimeListView.setItems(showtimes);

        // When a movie is clicked, load it into the form
        movieListView.getSelectionModel().selectedItemProperty().addListener((obs, oldM, newM) -> {
            if (newM != null) {
                loadMovieIntoForm(newM);
            }
        });

        // When a showtime is double-clicked, let user remove it quickly (confirmation)
        showtimeListView.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                MovieShow selected = showtimeListView.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    boolean yes = confirm("Delete showtime", "Remove selected showtime?");
                    if (yes) {
                        showtimeData.getShows().removeShow(selected);
                        showtimes.remove(selected);
                    }
                }
            }
        });

        // hook up buttons to methods (in case FXML didn't set onAction)
        addMovieBtn.setOnAction(e -> onAddMovie());
        editMovieBtn.setOnAction(e -> onEditMovie());
        deleteMovieBtn.setOnAction(e -> onDeleteMovie());
        addShowtimeBtn.setOnAction(e -> onAddShowtime());
        saveBtn.setOnAction(e -> onSave());
        cancelBtn.setOnAction(e -> onCancel());

        // ensure cell factories show readable text
        movieListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Movie item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getMovieName());
            }
        });

        showtimeListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(MovieShow item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getMovie().getMovieName() + " - " +
                            item.getShowDate() + " at " + item.getAshowTime() +
                            " (Room " + item.getRoomID() + ")");
                }
            }
        });
    }

    // ---------------------- Movie operations ----------------------------------

    /**
     * Clears fields and prepares the form to add a new movie.
     */
    @FXML
    private void onAddMovie() {
        movieListView.getSelectionModel().clearSelection();
        clearMovieFields();
        showtimes.clear();
    }

    /**
     * Loads selected movie data into the form for editing. If no movie selected shows an alert.
     */
    @FXML
    private void onEditMovie() {
        Movie selected = movieListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            alert("No movie selected", "Please select a movie to edit.");
            return;
        }
        loadMovieIntoForm(selected);
    }

    /**
     * Deletes the selected movie after confirmation. This removes associated showtimes too.
     */
    @FXML
    private void onDeleteMovie() {
        Movie selected = movieListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            alert("No movie selected", "Please select a movie to delete.");
            return;
        }
        boolean yes = confirm("Delete movie", "Are you sure you want to delete '" + selected.getMovieName() + "'?");
        if (!yes) return;

        // remove movie from the data stores
        movieData.getMovies().removeMovie(selected);

        // Remove associated showtimes
        ObservableList<MovieShow> allShows = showtimeData.getShows().getShowList();
        allShows.removeIf(show -> show.getMovie().getMovieID() == selected.getMovieID());

        movies.remove(selected);
        clearMovieFields();
        showtimes.clear();
    }

    // ---------------------- Showtime operations -------------------------------

    /**
     * Adds a new showtime for the currently selected movie in the form.
     * Expected simple input: "YYYY-MM-DD HH:MM" (no strict parsing here).
     */
    @FXML
    private void onAddShowtime() {
        String raw = newShowtimeField.getText();
        if (raw == null || raw.isBlank()) {
            alert("Empty showtime", "Please enter a showtime (e.g. 2025-12-01 18:30)");
            return;
        }

        Movie movieInForm = getMovieFromForm();
        if (movieInForm == null) {
            alert("No movie loaded", "You must load or create a movie before adding a showtime.");
            return;
        }

        try {
            // Parse the input: "YYYY-MM-DD HH:MM"
            String[] parts = raw.trim().split(" ");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Format should be: YYYY-MM-DD HH:MM");
            }

            String date = parts[0];
            String time = parts[1];

            // Generate a unique showtime ID
            int newShowID = generateShowtimeID();

            // Default room number (could be made configurable)
            int roomNumber = 1;

            // create a MovieShow and add to singleton
            MovieShow newShow = new MovieShow(newShowID, time, date, roomNumber, movieInForm);
            showtimeData.getShows().addShow(newShow);
            showtimes.add(newShow);
            newShowtimeField.clear();

            alert("Showtime added", "Showtime added successfully!");
        } catch (Exception ex) {
            alert("Invalid format", "Please use format: YYYY-MM-DD HH:MM (e.g., 2025-12-01 18:30)");
        }
    }

    // ---------------------- Save / Cancel ------------------------------------

    /**
     * Save current form: if movie exists update it; if not, create a new movie.
     * Basic validation applied (name non-empty, price numeric).
     */
    @FXML
    private void onSave() {
        try {
            String name = movieNameField.getText();
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Movie name is required.");
            }

            String genre = genreField.getText();
            if (genre == null || genre.isBlank()) {
                genre = "Unknown";
            }

            String release = releaseDateField.getText();
            if (release == null || release.isBlank()) {
                release = "Unknown";
            }

            double price = 0.0;
            if (priceField.getText() != null && !priceField.getText().isBlank()) {
                try {
                    price = Double.parseDouble(priceField.getText());
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("Price must be a number (e.g. 12.99)");
                }
            }

            String desc = descriptionArea.getText();
            if (desc == null) {
                desc = "";
            }

            Movie selected = movieListView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                // Create a new movie
                Movie created = new Movie(nextMovieID++, name, release, genre,
                        "/com/example/sdoop2finalproject/img/default.jpg",
                        price, desc);
                movieData.getMovies().addMovie(created);
                movies.add(created);
                movieListView.getSelectionModel().select(created);
                alert("Movie created", "Movie '" + created.getMovieName() + "' was created.");
            } else {
                // Update existing movie
                selected.setMovieName(name);
                selected.setGenre(genre);
                selected.setReleaseDate(release);
                selected.setPrice(price);
                selected.setDescription(desc);

                // Refresh the list view
                movieListView.refresh();
                alert("Movie updated", "Movie '" + name + "' was updated.");
            }

        } catch (IllegalArgumentException ex) {
            alert("Validation error", ex.getMessage());
        } catch (Exception ex) {
            alert("Save failed", "An unexpected error happened: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Cancel edits: reload selected movie or clear fields.
     */
    @FXML
    private void onCancel() {
        Movie selected = movieListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            loadMovieIntoForm(selected);
        } else {
            clearMovieFields();
        }
    }

    // ---------------------- Helper methods ----------------------------------

    /**
     * Loads a movie into the form and its showtimes into the showtime list.
     */
    private void loadMovieIntoForm(Movie m) {
        movieNameField.setText(m.getMovieName());
        genreField.setText(m.getGenre());
        releaseDateField.setText(m.getReleaseDate());
        priceField.setText(String.valueOf(m.getPrice()));
        descriptionArea.setText(m.getDescription());

        // load related showtimes from ShowtimeData
        showtimes.setAll(showtimeData.getShows().getShowByMovieID(m.getMovieID()));
    }

    /**
     * Builds a Movie instance from current form values if the movie already exists in the list.
     * Returns null if the form does not represent a known movie.
     */
    private Movie getMovieFromForm() {
        Movie selected = movieListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            return null;
        }
        return movieData.getMovies().findByID(selected.getMovieID());
    }

    /**
     * Clears all movie input fields.
     */
    private void clearMovieFields() {
        movieNameField.clear();
        genreField.clear();
        releaseDateField.clear();
        priceField.clear();
        descriptionArea.clear();
    }

    /**
     * Generates a unique showtime ID.
     */
    private int generateShowtimeID() {
        int maxID = 0;
        for (MovieShow show : showtimeData.getShows().getShowList()) {
            if (show.getShowID() > maxID) {
                maxID = show.getShowID();
            }
        }
        return maxID + 1;
    }

    // ---------------------- UI helpers --------------------------------------

    /**
     * Shows an information alert dialog.
     */
    private void alert(String title, String message) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(message);
        a.showAndWait();
    }

    /**
     * Shows a confirmation dialog and returns true if user clicks OK.
     */
    private boolean confirm(String title, String message) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(message);
        return a.showAndWait().filter(r -> r == ButtonType.OK).isPresent();
    }
}