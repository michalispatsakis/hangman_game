package org.multimedia.hangman;

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
    public void DisplayDictionaryStats(int letter6, int letter79, int letter10){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Dictionary Statistics");
        window.setMinWidth(300);
        Label label1 = new Label();
        label1.setText("The number of 6 letter words are :" + Integer.toString(letter6));
        Label label2 = new Label();
        label2.setText("The number of 7-9 letter words are :" + Integer.toString(letter79));
        Label label3 = new Label();
        label3.setText("The number of 10 or more letter words are :" + Integer.toString(letter10));



        submit = new Button("OK");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                window.close();
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1,label2,label3,submit);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();


    }

    public void StartError(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Start Error");
        window.setMinWidth(400);
        Label label1 = new Label();
        label1.setText("You have to load/create a dictionary");


        submit = new Button("OK");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                window.close();
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1,submit);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();


    }
    public void displayDictStatsError(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Dictionary Statistics Error");
        window.setMinWidth(400);
        Label label1 = new Label();
        label1.setText("You first have to load a dictionary to see the statistics");


        submit = new Button("OK");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                window.close();
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1,submit);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();


    }

    public void WonGame(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("You Won The Game");
        window.setMinWidth(400);
        Label label1 = new Label();
        label1.setText("You can load/create a new dictionary or start again");


        submit = new Button("OK");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                window.close();
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1,submit);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }
    public void ShowSolution(String word){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Solution");
        window.setMinWidth(400);
        Label label1 = new Label();
        label1.setText("The word is: " + word);


        submit = new Button("OK");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                window.close();
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1,submit);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }
    public void ShowSolutionError(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Solution Error");
        window.setMinWidth(400);
        Label label1 = new Label();
        label1.setText("You have to first Start a game in order to see the solution ");


        submit = new Button("OK");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                window.close();
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1,submit);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }


}
