package com.example.sdoop2finalproject.Controllers.ManagerSide.ManagerShowtime;

import com.example.sdoop2finalproject.Models.ShowRooms.MovieRoom;
import com.example.sdoop2finalproject.Models.ShowRooms.RoomData;
import com.example.sdoop2finalproject.Models.Showtimes.MovieShow;
import com.example.sdoop2finalproject.Models.Showtimes.ShowtimeData;
import com.example.sdoop2finalproject.Models.Movie.Movie;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        if (date.isEmpty() || time.isEmpty() || room == null) return;

        int nextID = ShowtimeData.getInstance().getShows().getShowList().size() + 1;

        MovieShow show = new MovieShow(nextID, time, date, room.getRoomID(), aMovie);

        ShowtimeData.getInstance().getShows().addShow(show);

        ((Stage) dateField.getScene().getWindow()).close();
    }

    /**
     * Close the add showtime view.
     */
    @FXML
    private void cancel() {
        ((Stage) dateField.getScene().getWindow()).close();
    }
}
