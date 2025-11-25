package com.example.sdoop2finalproject.Controllers;

import com.example.sdoop2finalproject.Models.MovieShow;
import javafx.fxml.FXML;

import javafx.scene.text.Text;

/**
 * Controller for a single showtime card.
 * Controller for the showtime-card view.
 * @Author Mounsif
 */
public class ShowtimeCardController {
    /**
     * Display the date.
     */
    @FXML
    private Text dateText;

    /**
     * Display the time.
     */
    @FXML
    private Text timeText;

    /**
     * Display the room number.
     */
    @FXML
    private Text roomText;


    /**
     * Change UI components.
     * @param pShow the showtime displayed in the row.
     */
    public void setData(MovieShow pShow) {
        dateText.setText(pShow.getShowDate());
        timeText.setText(pShow.getAshowTime());
        roomText.setText("Room " + pShow.getRoomID());
    }
}

