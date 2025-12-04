package com.example.sdoop2finalproject.Controllers.ManagerSide.ManagerShowtime;

import com.example.sdoop2finalproject.Models.ShowRooms.MovieRoom;
import com.example.sdoop2finalproject.Models.ShowRooms.RoomData;
import com.example.sdoop2finalproject.Models.Showtimes.MovieShow;
import com.example.sdoop2finalproject.Models.Showtimes.ShowtimeData;
import com.example.sdoop2finalproject.Models.Movie.Movie;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Controller responsible to add new showtimes
 * See if the fields are correctly filled, if yes, it creates and add a showtime object
 * @Author Mounsif
 */
public class AddShowtimeController {

    @FXML
    private TextField dateField;

    @FXML
    private TextField timeField;

    @FXML
    private ComboBox<MovieRoom> roomComboBox;

    private Movie aMovie;

    /**
     * Sets the movie that the showtime is being created.
     * @param movie the movie the manager want to add a showtime.
     */
    public void setMovie(Movie movie) {
        this.aMovie = movie;
    }

    /**
     * Run automatically the view when opened.
     * Fills the room combobox with available movie rooms.
     */
    @FXML
    public void initialize() {
        roomComboBox.getItems().addAll(RoomData.getInstance().getRooms().getRooms());
    }

    /**
     * Method called when the manager click on add showtime button.
     * See if all field are filled.
     * Create and add it to ShowtimeData.
     */
    @FXML
    private void addShowtime() {

        String date = dateField.getText().trim();
        String time = timeField.getText().trim();
        MovieRoom room = roomComboBox.getValue();
        //****
        if (date.isEmpty() || time.isEmpty() || room == null) {
            showError("Please fill out all fields");
            return;
        }

        if (!isValidDate(date)){
            showError("The date cannot be in the past or please enter a valid date.(YYYY-MM-DD)");
            return;
        }

        if (!isValidTime(time)){
            showError("Please enter a valid time.(HH:MM)");
            return;
        }
        int nextID = ShowtimeData.getInstance().getShows().getShowList().size() + 1;

        MovieShow show = new MovieShow(nextID, time, date, room.getRoomID(), aMovie);

        ShowtimeData.getInstance().getShows().addShow(show);

        ((Stage) dateField.getScene().getWindow()).close();
    }

    /**
     * Validates if the given date string is in the correct format (YYYY-MM-DD).
     *
     * @param pDate the date string to validate
     * @return true if the date is valid, false otherwise
     */
    private boolean isValidDate(String pDate) {
        try {
            LocalDate.parse(pDate, DateTimeFormatter.ISO_LOCAL_DATE);

            LocalDate date = LocalDate.parse(pDate);
            return !date.isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    /**
     * Validates if the given time string is in the correct format (HH:MM).
     *
     * @param pTime the time string to validate
     * @return true if the time is valid, false otherwise
     */
    private boolean isValidTime(String pTime) {
        try {
            LocalTime.parse(pTime, DateTimeFormatter.ofPattern("HH:mm"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Shows the error alert with the message given.
     * @param msg message to display in the content of the alert.
     */
    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    /**
     * Close the add showtime view.
     */
    @FXML
    private void cancel() {
        ((Stage) dateField.getScene().getWindow()).close();
    }
}
