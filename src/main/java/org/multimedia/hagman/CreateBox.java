package org.multimedia.hagman;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.util.Pair;

public class CreateBox {

    Stage window;
    Button submit;

    public Pair<String, String> display(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Load Dictionary");
        window.setMinWidth(400);
        Label label1 = new Label();
        label1.setText("Insert Dictionary ID");
        TextField dictionaryid = new TextField();
        TextField openlibraryid = new TextField();
        Label label2 = new Label();
        submit = new Button("Submit id");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                window.close();
            }
        });
        label2.setText("Insert OpenLibrary ID)");
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1,dictionaryid,submit);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        Pair<String, String> p = new Pair<>(dictionaryid.getText(),openlibraryid.getText() );

        return p;
    }

    public Pair<String, String> displaycreatebox(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Create Dictionary");
        window.setMinWidth(400);
        Label label1 = new Label();
        label1.setText("Insert Dictionary ID");
        TextField dictionaryid = new TextField();
        TextField openlibraryid = new TextField();
        Label label2 = new Label();
        submit = new Button("Submit id");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                window.close();
            }
        });
        label2.setText("Insert OpenLibrary ID)");
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1,dictionaryid,label2,openlibraryid,submit);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        Pair<String, String> p = new Pair<>(dictionaryid.getText(),openlibraryid.getText() );

        return p;
    }

}
