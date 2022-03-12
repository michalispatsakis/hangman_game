package org.multimedia.hagman;



import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.util.Pair;

public class HelloController {

    private Dictionary selectedDictionary;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void loadDictionary() {
        CreateBox cb = new CreateBox();
        Pair<String,String> p = cb.display();

        try {
            Dictionary d = new Dictionary();
            d.openFile(p.getKey());
            this.selectedDictionary = d;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setContentText("File not found");
            msg.showAndWait();

        }
        }

    @FXML
    protected void createDictionary() {
        CreateBox cb = new CreateBox();
        Pair<String,String> p = cb.displaycreatebox();

        try {
            Dictionary d = new Dictionary();
            d.createDictionaryFile(p.getKey(),p.getValue());
            this.selectedDictionary = d;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setContentText("File not found");
            msg.showAndWait();

        }
    }





}