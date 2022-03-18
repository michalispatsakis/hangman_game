package org.multimedia.hangman;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.util.Pair;


import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class HelloController {

    private  int cellWidth = 30;
    private  int cellHeight = 30;
    private  int cellSpacing = 10;
    private  double paneHeight;
    private  double paneWidth;
    private  double hboxWidth;
    private  Boolean gameRunning = false;
    private Gameplay game_state = null;
    private int selectedCellIndex = 0;
    private String selectedword;
    private ArrayList<TextField> cells = new ArrayList<TextField>();
    private ImageView imageView;
    private Image image;


    private ArrayList<Button> bl;
    private ListView<Button> lv;

    private Dictionary selectedDictionary;

    @FXML
    private AnchorPane wordPresentationPane;
    @FXML
    private Label choiceDescriber;
    @FXML
    private AnchorPane positionMapsPane;
    @FXML
    private Label welcomeText;
    @FXML
    private Button pickLetterButton;
    @FXML
    private SplitPane mainPane;
    @FXML
    public Label scoreLabel;
    @FXML
    public Label remainingWordsLabel;
    @FXML
    public Label ratioLabel;
    @FXML
    public Label choicesLabel;


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
    protected void DictionaryStats(){
        if(this.selectedDictionary != null) {
            int words6 =Calculate6Words();
            int words79 = Calculate79Words();
            int words10 = Calculate10Words();
            CreateBox Box = new CreateBox();
            Box.DisplayDictionaryStats(words6, words79, words10);
        }
        else{
            CreateBox Box = new CreateBox();
            Box.displayDictStatsError();

        }
    }
    @FXML
    protected void PrevStats(){
        if(this.selectedDictionary != null) {
            ArrayList<String> data = new ArrayList<>();
            data = PreviousStats.readStatsFile();
            System.out.println(data);
            CreateBox Box = new CreateBox();
        }
        else{
            CreateBox Box = new CreateBox();
            Box.displayDictStatsError();

        }
    }

    @FXML
    protected void startDictionary() {
        selectedCellIndex = 0;

        if (this.selectedDictionary != null) {
             this.game_state = new Gameplay(this.selectedDictionary);
             this.selectedword = game_state.returnword();
             this.gameRunning = true;
             game_state.Initialize();
             renderMiscellaneous();
            if (imageView != null) {
                imageView.setVisible(false);
            }
             String word = game_state.returnword();
             ArrayList<LinkedHashMap<String,Integer>> HashMaps = game_state.returnHashMaps();
             System.out.println(word);
             renderword(word);
             renderPossibleCharacters();
             renderStats();

            Scene scene = positionMapsPane.getScene();
            scene.focusOwnerProperty().addListener(
                    (prop, oldNode, newNode) -> {
                        for (int i=0; i<cells.size(); i++){
                            if (cells.get(i).isFocused() && selectedCellIndex != i) {
                                choiceDescriber.setText("Select Letter "+ String.valueOf(i+1));
                                selectedCellIndex=i;
                                renderPossibleCharacters();
                                return;
                            }
                        }
                    });

        }
        else {
            CreateBox cb = new CreateBox();
            cb.StartError();
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
    @FXML
    protected void initialize(){
        mainPane.setDisable(true);
        pickLetterButton.setVisible(false);
        choiceDescriber.setVisible(false);
        scoreLabel.setVisible(false);
        remainingWordsLabel.setVisible(false);
        ratioLabel.setVisible(false);
        choicesLabel.setVisible(false);
    }
    public void renderMiscellaneous(){
        mainPane.setDisable(false);
        pickLetterButton.setVisible(true);
        choiceDescriber.setVisible(true);
        scoreLabel.setVisible(true);
        remainingWordsLabel.setVisible(true);
        ratioLabel.setVisible(true);
        choicesLabel.setVisible(true);
    }

    @FXML
    protected void renderword(String word) {
        HBox box = new HBox();
        box.setSpacing(10);
        for (int i=0; i< word.length(); i++){
            TextField letterCell = new TextField();
            letterCell.setPrefWidth(cellWidth);
            letterCell.setPrefHeight(cellHeight);
            letterCell.setEditable(false);
            letterCell.setAlignment(Pos.CENTER);
            letterCell.setFont(new Font(15));
            letterCell.setCursor(Cursor.HAND);
            box.getChildren().add(letterCell);
            cells.add(letterCell);
        }

        wordPresentationPane.getChildren().add(box);
        paneHeight = wordPresentationPane.getHeight();
        paneWidth = wordPresentationPane.getWidth();
        hboxWidth = ((cellWidth+cellSpacing) * word.length() - cellSpacing) ;
        double leftOffset = (paneWidth - hboxWidth)/2;
        wordPresentationPane.setTopAnchor(box, 2*paneHeight/3 );
        wordPresentationPane.setLeftAnchor(box, leftOffset);
        cells.get(selectedCellIndex).requestFocus();
    }

    public void renderPossibleCharacters(){
        ArrayList<LinkedHashMap<String, Integer>> AllMaps = game_state.returnHashMaps();
        LinkedHashMap<String,Integer> lhm =AllMaps.get(selectedCellIndex);
        if (lv!=null) lv.getItems().clear();
        bl = new ArrayList<Button>();
        for (String c: lhm.keySet()){
            Button b = new Button(c);
            b.setPrefWidth(35);
            b.setOnAction(event -> pickLetter(c.charAt(0)));
            Font f = new Font("Monospaced"  , 15);
            b.setAlignment(Pos.CENTER);
            b.setFont(f);
            bl.add(b);
        }
        ObservableList<Button> ol = FXCollections.observableList(bl);
        lv = new ListView<Button>(ol);
        lv.setPrefHeight(457);
        positionMapsPane.getChildren().add(lv);
    }
    public void renderCellsBasedOnCurrentWord(){
        ArrayList<Character> curr = game_state.getCurrent_word();
        for (int i=0; i< curr.size(); i++){
            if (curr.get(i) == '_') continue;
            else {
                TextField tf = cells.get(i);
                tf.setText(curr.get(i).toString());
                tf.setDisable(true);
                tf.setOpacity(0.8);
            }
        }
    }

    public int Calculate6Words(){
        ArrayList<String> allwords = new ArrayList<>(selectedDictionary.getWords());
        int cnt =0;
        for(String word: allwords){
            if(word.length() == 6)
                cnt++;
        }
        return cnt;
    }
    public int Calculate79Words(){
        ArrayList<String> allwords = new ArrayList<>(selectedDictionary.getWords());
        int cnt =0;
        for(String word: allwords){
            if(word.length() >= 7 && word.length() <= 9)
                cnt++;
        }
        return cnt;
    }
    public int Calculate10Words(){
        ArrayList<String> allwords = new ArrayList<>(selectedDictionary.getWords());
        int cnt =0;
        for(String word: allwords){
            if(word.length() >= 10)
                cnt++;
        }
        return cnt;
    }

    public void pickLetter(Character c){
        String position = Character.toString(choiceDescriber.getText().charAt(choiceDescriber.getText().length()-1));
        String newText = "Pick Letter " + c.toString() + " For Position " + position.toString();
        choiceDescriber.setText(newText);
        cells.get(selectedCellIndex).requestFocus();

    }

    public void renderHangedMan(){
        if (imageView!=null) imageView.setVisible(false);
        try { image = new Image(new FileInputStream("src/main/java/org/multimedia/hangman/img/hanging_"+(game_state.getWrongChoices())+".png")); }
        catch( Exception e){}
        imageView = new ImageView(image);
        imageView.setX(paneWidth/3);
        imageView.setFitHeight(100);
        wordPresentationPane.getChildren().add(imageView);
    }
    public void stopGame(){
        gameRunning = false;
        mainPane.setDisable(true);
        imageView.setOpacity(0.4);
    }
    public void exitGame(){
        System.exit(0);
    }
    public void gameLost(){
        renderHangedMan();
        renderStats();
        stopGame();
        String row = game_state.returnword() + "," + game_state.getChoices() + ",COMPUTER";
//        try {
//            FileIO.appendToStatsFile(row);
//        }
//        catch (Exception e){
//            System.out.println(e.getMessage());
//        }
    }
    public void renderStats(){
        int score = game_state.getPoints();
        int words_remaining = game_state.CandidateWords();
        int choices = game_state.getChoices();
        int wrong_choices = game_state.getWrongChoices();
        double ratio;
        if (choices != 0)  ratio = ((double)(choices-wrong_choices)/(double) choices)*100;
        else ratio = 0.00;
        String ratio_str = String.format("%.2f",ratio);
        scoreLabel.setText("Score: " + score);
        remainingWordsLabel.setText("Words Remaining: " + words_remaining);
        ratioLabel.setText("Success Ratio: "+ String.valueOf(ratio_str) + "%");
        choicesLabel.setText("Attempts: " + choices);
    }


    public void makeChoice(Event e){
        if (gameRunning && game_state.gameStatus() == 0){
            Character c = choiceDescriber.getText().charAt(12);
            int index = selectedCellIndex;

            try {
                game_state.pickLetter(index,c); // Returns Boolean ( Correct , False )
                ArrayList<Character> curr = game_state.getCurrent_word();
                for (int i=selectedCellIndex; i<curr.size(); i++){
                    if (curr.get(i) == '_') {
                        selectedCellIndex = i;
                        cells.get(i).requestFocus();
                        break;
                    }
                }
                String info;
                switch (game_state.gameStatus()) {
                    case -1:
                        cells.clear();
                        info = "Word: " + this.selectedword + " Attempts: " + game_state.getChoices() + " Won by: computer";
                        PreviousStats.appendToStatsFile(info);
                        gameLost();
                        break;
                    case 0:
                        renderPossibleCharacters();
                        renderHangedMan();
                        renderCellsBasedOnCurrentWord();
                        renderStats();
                        break;
                    case 1:
                        cells.clear();
                        CreateBox Victory = new CreateBox();
                        Victory.WonGame();
                        renderStats();
                        info = "Word: " + this.selectedword + " Attempts: " + game_state.getChoices() + " Won by: player";
                        PreviousStats.appendToStatsFile(info);
                        stopGame();
                        break;
                }
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
            }
            e.consume();
        }

    }
    public void Solution() throws Exception {
        String info;
        if (this.selectedword != null ) {
            info = "Word: " + this.selectedword + " Attempts: " + game_state.getChoices() + " Won by: player";
            PreviousStats.appendToStatsFile(info);
            CreateBox Box = new CreateBox();
            Box.ShowSolution(this.selectedword);
            gameLost();
            stopGame();
        }
        else{
            CreateBox Box = new CreateBox();
            Box.ShowSolutionError();
        }
    }
    }
