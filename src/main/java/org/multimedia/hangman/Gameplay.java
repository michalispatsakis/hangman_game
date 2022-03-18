package org.multimedia.hangman;



import java.util.*;

import org.javatuples.Pair;


class SortHashMapValue {
    public static LinkedHashMap<String, Integer> Sort_Hash_Table(LinkedHashMap<String, Integer> map) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, (Comparator) (o1, o2) -> ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue()));
        LinkedHashMap sortedHashMap = new LinkedHashMap();
        ListIterator li = list.listIterator(list.size());
        while(li.hasPrevious()) {
            Map.Entry entry = (Map.Entry) li.previous();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

}
public class Gameplay {

    private Dictionary Dict;
    int  choices,wrong_choices;
    int mistakes;
    private String selected_word;
    private ArrayList<LinkedHashMap<String, Integer>> ListofHashMaps;
    private ArrayList <String> word_pool;
    private int Points;
    ArrayList<Character> current_word;


    public Gameplay(Dictionary d){
        this.Dict = d;
        this.mistakes = 0;
        this.SelectWord();
        this.word_pool = new ArrayList<>(d.getWords());
        this.Points = 0;
        this.choices=0;
        this.wrong_choices = 0;

        current_word = new ArrayList<Character>();
        for (int i = 0; i < selected_word.length(); i++)
            current_word.add('_');
    }
    public int getChoices(){
        return this.choices;
    }
    public String returnword(){
        return  this.selected_word;
    }
    public ArrayList<Character> getCurrent_word(){ return this.current_word;}
    public int getWrongChoices(){
        return this.wrong_choices;
    }
    public int getPoints(){return this.Points;}
    public int CandidateWords(){return this.word_pool.size();}



    public ArrayList<LinkedHashMap<String, Integer>> returnHashMaps() {
        return this.ListofHashMaps;
    }

    public void Possibilities(String c , LinkedHashMap<String, Integer> map){
        int sum = 0;
        double pos = 0.0;
        for(int n:map.values()){
            sum += n;
        }
        int val = map.get(c);
        double d1 = val;
        double d2 = sum;
        pos = (d1 / d2);
        if (pos >= 0.6){
            this.Points += 5;
        }
        else if (pos >= 0.4){
            this.Points += 10;
        }
        else if (pos >= 0.25){
            this.Points += 15;
        }
        else this.Points += 30;
    }

   public void SelectWord(){
       ArrayList<String> DictList = Dict.getWords();
       int cnt1 = DictList.size();
       Random rand = new Random();
       int upperbound = cnt1;
       int int_random = rand.nextInt(upperbound);
       String chosen_w = DictList.get(int_random);

       this.selected_word = "BETRAYAL";

   }

    public void Initialize (){

        ArrayList<String> Removedwords = new ArrayList<>();

        List<LinkedHashMap<String, Integer>> map_list = new ArrayList<>();
        for(int i=0; i<this.selected_word.length();i++){
            LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
            map_list.add(map);
        }

        for (String candidateword : word_pool) {
            Boolean Belongs = false;
            if (candidateword.length() == this.selected_word.length()) {

                Belongs = true;
                for (int j = 0; j < this.selected_word.length(); j++) {
                    String sn = String.valueOf(candidateword.charAt(j));
                    LinkedHashMap<String, Integer> HashMap = map_list.get(j);
                    if (HashMap.get(sn) == null) {
                        HashMap.put(sn, 1);
                    } else {
                        int count = HashMap.get(sn);
                        HashMap.put(sn, count + 1);
                        map_list.set(j, HashMap);
                    }
                }
            }

            if (Belongs == false) {
                Removedwords.add(candidateword);
            }
        }
                for( String word : Removedwords){
                    this.word_pool.remove(word);
                }
                int cnt = 0;
                for (LinkedHashMap<String, Integer> Map : map_list) {
                    LinkedHashMap<String, Integer> SortedHashMap = SortHashMapValue.Sort_Hash_Table(Map);
                    map_list.set(cnt, SortedHashMap);
                    cnt++;
                }
                this.ListofHashMaps = new ArrayList<LinkedHashMap<String, Integer>>(map_list);
                LinkedHashMap<String,Integer> test = map_list.get(0);
                System.out.println(test);

    }



    public void Update_Word(Pair <Integer, String> pair){
        Boolean Answer = false;
        ArrayList<String> RemovedWords = new ArrayList<>();

        List<LinkedHashMap<String, Integer>> map_list = new ArrayList<>();
        for(int i=0; i<this.selected_word.length();i++){
            LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
            map_list.add(map);
        }
        int index = pair.getValue0();
        String letter = pair.getValue1();
        if(Character.toString(this.selected_word.charAt(index)).equals(letter) ){
            Answer = true;
            System.out.println("Im correct choice");
        }
        else{
            this.mistakes ++;
        }

        if(Answer == true) {
            System.out.println("again");
            for (String candidateword : word_pool) {
                Boolean Belongs = false;
                if (candidateword.length() == this.selected_word.length()) {
                    if (Character.toString(candidateword.charAt(index)).equals(letter)) {
                        Belongs = true;
                        for (int j = 0; j < this.selected_word.length(); j++) {
                            String sn = String.valueOf(candidateword.charAt(j));
                            LinkedHashMap<String, Integer> HashMap = map_list.get(j);
                            if (HashMap.get(sn) == null) {
                                HashMap.put(sn, 1);
                            } else {
                                int count = HashMap.get(sn);
                                HashMap.put(sn, count + 1);
                                map_list.set(j, HashMap);
                            }
                        }
                    }
                }
                    if (Belongs == false) {
                        RemovedWords.add(candidateword);
                    }
                }
                    int cnt = 0;
                    for (LinkedHashMap<String, Integer> Map : map_list) {
                        LinkedHashMap<String, Integer> SortedHashMap = SortHashMapValue.Sort_Hash_Table(Map);
                        map_list.set(cnt, SortedHashMap);
                        cnt++;
                    }
                    this.ListofHashMaps = new ArrayList<LinkedHashMap<String, Integer>>(map_list);
            for(String word : RemovedWords){

                this.word_pool.remove(word);
            }
        }
        else{
            for (String candidateword : word_pool) {
                System.out.println(candidateword);
                Boolean Belongs = true;
                if (candidateword.length() == this.selected_word.length()) {

                    if (Character.toString(candidateword.charAt(index)).equals(letter)) {
                        Belongs = false;

                    }

                    if (Belongs == true) {
                        for (int j = 0; j < this.selected_word.length(); j++) {
                            String sn = String.valueOf(candidateword.charAt(j));
                            LinkedHashMap<String, Integer> HashMap = map_list.get(j);
                            if (HashMap.get(sn) == null) {
                                HashMap.put(sn, 1);
                            } else {
                                int count = HashMap.get(sn);
                                HashMap.put(sn, count + 1);
                                map_list.set(j, HashMap);
                            }
                        }
                        int cnt = 0;
                        for (LinkedHashMap<String, Integer> Map : map_list) {
                            LinkedHashMap<String, Integer> SortedHashMap = SortHashMapValue.Sort_Hash_Table(Map);
                            map_list.set(cnt, SortedHashMap);
                            cnt++;
                        }
                        this.ListofHashMaps = new ArrayList<LinkedHashMap<String, Integer>>(map_list);
                    }
                    if(Belongs == false){
                        RemovedWords.add(candidateword);

                    }
                }
            }
        }
        for(String word : RemovedWords){

            this.word_pool.remove(word);
        }
    }


    public void Points_Update ( LinkedHashMap<String, Integer> map, Pair<Integer, String> pair) {
        boolean flag = false;
        int m = pair.getValue0();
        if (Objects.equals(pair.getValue1(), String.valueOf(selected_word.charAt(m)))){
            flag = true;
        }
        if (flag) {
            Possibilities(pair.getValue1(), map);
        }
        else {
            Points -= 15;
            if(Points <=0 )
                Points = 0;
        }

    }


    /**
     * This method is used for calculating the success rate of the chosen characters in the active game.
     * @param WordList an arraylist of strings of the current state of the word shown in the game.
     * @param mistakes int of the mistakes made in game.
     * @return double success rate.
     */

    public double Success_Rate_In_Game(ArrayList<String> WordList, int mistakes){
        int total = 0;
        int sum1 = 0;
        for (String s : WordList){
            if (!Objects.equals(s, "_")){
                sum1 ++;
            }
        }
        total = mistakes + sum1;
        return (sum1 / total);
    }

    public Boolean pickLetter(int index, Character choice) throws ChoiceException, ArithmeticException {
        System.out.println(choice);
        try {
            if (index < 0 || index >= this.selected_word.length()) {
                throw new OutOfRangeException();
            }
            LinkedHashMap<String, Integer> lhm = ListofHashMaps.get(index);
            for (String letter : lhm.keySet()) {
                if ( (Character.toString(choice)).equals(letter)) {
                    System.out.println("Hello3");
                    Character right_letter = this.selected_word.charAt(index);
                    Character previous = current_word.get(index);
                    if (previous != '_')
                        throw new LetterAlreadyFound();

                    if (right_letter == choice) {
                        correctChoice(index,choice);
                        choices+=1;
                        return true;
                    }
                    else {
                        wrongChoice(index,choice);
                        choices+=1;
                        return false;
                    }
                }
            }
            throw new LetterException();
        } catch (Exception e) {
            throw e;
        }


    }
    public void correctChoice(int index, Character choice) throws ArithmeticException {
        try {
            LinkedHashMap<String, Integer> lhm = ListofHashMaps.get(index);
            Pair pair = new Pair<>(index,Character.toString(choice));
            Points_Update(lhm,pair);

            ArrayList<String> bad_words = new ArrayList<String>();

            Update_Word(pair);
            current_word.set(index, choice);

        } catch (ArithmeticException e) {
            throw e;
        }
        System.out.println("Correct choice!");
        System.out.println("Score " + this.Points);

    }
    public void wrongChoice(int index, Character choice) {
        LinkedHashMap<String, Integer> lhm = ListofHashMaps.get(index);
        Pair pair = new Pair<>(index,Character.toString(choice));
        Points_Update(lhm,pair);
        Update_Word(pair);
        wrong_choices+=1;
//        printSets();
        System.out.println("Wrong choice!");
        System.out.println("Score " + this.Points);

    }
    public int gameStatus(){
        System.out.println(wrong_choices);
        if (wrong_choices == 6) {
            for (int i = 0; i < current_word.size(); i++) {
                if (current_word.get(i) == '_')
                    return -1;
            }
        }
        else {
            for (int i=0; i<current_word.size(); i++) {
                if (current_word.get(i) == '_')
                    return 0;
            }
        }
        return  1;
    }

    /**
     * This is the main method that runs the run method.
     * @param args
     */


    /**
     * This method calls the functions of this class.
     * @param args
     * @throws Exception if an error has occurred.
     */

}


