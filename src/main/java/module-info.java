module com.example.diehlweatherappuidesign {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires java.desktop;


    opens com.example.diehlweatherappuidesign to javafx.fxml;
    exports com.example.diehlweatherappuidesign;
}