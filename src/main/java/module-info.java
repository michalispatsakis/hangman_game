module org.multimedia.hagman {
    requires javafx.controls;
    requires javafx.fxml;
    requires unirest.java;
    requires com.google.gson;


    opens org.multimedia.hagman to javafx.fxml;
    exports org.multimedia.hagman;
}