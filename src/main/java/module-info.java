module com.example.sdoop2finalproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sdoop2finalproject to javafx.fxml;
    exports com.example.sdoop2finalproject;
}