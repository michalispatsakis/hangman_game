module org.multimedia.hagman {
    requires javafx.controls;
    requires javafx.fxml;
    requires unirest.java;
    requires com.google.gson;
    requires javatuples;


    opens org.multimedia.hangman to javafx.fxml;
    exports org.multimedia.hangman;
}