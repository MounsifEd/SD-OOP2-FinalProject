module com.example.sdoop2finalproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sdoop2finalproject to javafx.fxml;
    exports com.example.sdoop2finalproject;
    exports com.example.sdoop2finalproject.Controllers;
    opens com.example.sdoop2finalproject.Controllers to javafx.fxml;
    exports com.example.sdoop2finalproject.Models;
    opens com.example.sdoop2finalproject.Models to javafx.fxml;
}