package com.example.sdoop2finalproject;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class MovieCardController {
    @FXML
    private ImageView posterView;
    @FXML
    private Text movieTitle;
    @FXML
    private Text movieGenre;
    @FXML
    private Text movieRelease;

    private Movie movie;
}
