module com.example.sdoop2finalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.sdoop2finalproject to javafx.fxml;
    exports com.example.sdoop2finalproject;
    // Allow FXMLLoader to access controllers in the root Controllers package
    exports com.example.sdoop2finalproject.Controllers;
    opens com.example.sdoop2finalproject.Controllers to javafx.fxml;
    exports com.example.sdoop2finalproject.Models.Movie;
    opens com.example.sdoop2finalproject.Models.Movie to javafx.fxml;
    exports com.example.sdoop2finalproject.Models.Showtimes;
    opens com.example.sdoop2finalproject.Models.Showtimes to javafx.fxml;
    exports com.example.sdoop2finalproject.Models.ShowRooms;
    opens com.example.sdoop2finalproject.Models.ShowRooms to javafx.fxml;
    exports com.example.sdoop2finalproject.Controllers.ClientSide;
    opens com.example.sdoop2finalproject.Controllers.ClientSide to javafx.fxml;
    exports com.example.sdoop2finalproject.Controllers.ManagerSide.ManagerShowtime;
    opens com.example.sdoop2finalproject.Controllers.ManagerSide.ManagerShowtime to javafx.fxml;
    exports com.example.sdoop2finalproject.Controllers.ManagerSide.ManagerMovie;
    opens com.example.sdoop2finalproject.Controllers.ManagerSide.ManagerMovie to javafx.fxml;
    exports com.example.sdoop2finalproject.Controllers.ManagerSide.ManagerShowRoom;
    opens com.example.sdoop2finalproject.Controllers.ManagerSide.ManagerShowRoom to javafx.fxml;
}