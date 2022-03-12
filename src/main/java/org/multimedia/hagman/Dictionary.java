package org.multimedia.hagman;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import java.util.stream.Collectors;

import com.mashape.unirest.http.exceptions.UnirestException;


public class Dictionary {

    static String dictionaries_path = "./src/main/java/org/multimedia/hangman/medialab/";

    ArrayList<String> words;

    public Dictionary(){
        this.words = new ArrayList<>();
    }


    public void formatWords() {

        for (int i = 0; i < words.size(); i++) {
            String cap_word = words.get(i).toUpperCase();
            String filt_word = cap_word.replaceAll("[^a-zA-Z]", "");
            words.set(i, filt_word);
        }
        words.removeIf(e -> e.length() < 6);
        List<String> unique_words = words.stream().distinct().collect(Collectors.toList());
        words = new ArrayList<String>(unique_words);

    }

    public void validateWords() throws DictionaryException {

        HashMap<String, Integer> word_count = new HashMap<String, Integer>();

        int nine_lettered = 0;
        int total_words = words.size();

        if (total_words < 20)
            throw new UndersizeException();

        for (String w : words)
            word_count.put(w, 0);
        for (String w : words) {
            if (!Functions.isStringUpperCase(w))
                throw new WordLetterException();
            if (w.length() < 6)
                throw new InvalidRangeException();
            if (w.length() >= 9)
                nine_lettered += 1;
            word_count.put(w, word_count.get(w) + 1);

        }
        for (String i : word_count.keySet()) {
            if (word_count.get(i) != 1)
                throw new InvalidCountException();
        }

        double nine_lettered_ratio = (double) nine_lettered / (double) total_words;

        if (nine_lettered_ratio < 0.2)
            throw new UnbalancedException();

    }

    public void createFromOpenLibraryId(String open_library_id)
            throws DictionaryException, ApiException, UnirestException {
        try {
            ApiConsumer.fetchData(open_library_id);
            String description = ApiConsumer.getDescription();
            words = new ArrayList<String>(Arrays.asList(description.split("\\s+")));

            formatWords();
            validateWords(); // Throws dictionary exception
        } catch (Exception e) {
            throw e;
        }
    }
    public static void createDictionaryFile (String dict_id, String open_library_id) throws DictionaryException, ApiException, UnirestException, IOException {

        String filename = "hangman_" + dict_id + ".txt";
        String msg = "File " + filename + " created successfully";
        Dictionary d = new Dictionary();
        Boolean ndop = true;
        try {
            d.createFromOpenLibraryId(open_library_id);
            ArrayList<String> words = d.getWords();
            FileWriter myWriter = new FileWriter(dictionaries_path + filename);
            for (String w : words) {
                myWriter.write(w + "\n");
            }
            myWriter.close();
        } catch (Exception e) {
            ndop = false;
            throw e;
        }

    }

    public void loadFromWordList(ArrayList<String> word_list) throws DictionaryException {

        try {
            this.words = new ArrayList<String>(word_list);
            validateWords();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public HashMap<String,Integer> findStatsPerWordSize(){
        HashMap<String,Integer> hm = new HashMap<String, Integer>();
        hm.put("Small", 0);
        hm.put("Medium", 0);
        hm.put("Large", 0);


        for (String w : words){
            int w_size = w.length();
            if (0 <= w_size && w_size <= 6){
                hm.put("Small", hm.get("Small") + 1);
            }
            else if (7 <= w_size && w_size <= 9) {
                hm.put("Medium", hm.get("Medium") + 1);

            }
            else {
                hm.put("Large", hm.get("Large") + 1);
            }

        }
        System.out.println(hm.toString());
        return hm;
    }

    public String pickWord() {
        int min = 0;
        int max = words.size()-1;
        int random_index = (int) Math.floor(Math.random() * (max - min + 1) + min);
        return words.get(random_index);

    }

    public void openFile(String id) throws DictionaryException, FileNotFoundException {

        String filename = "C:\\Users\\milpr\\IdeaProjects\\hagman\\src\\main\\java\\org\\multimedia\\hagman\\"+ id+".txt";
        Dictionary d = new Dictionary();
        File dict_file = new File(filename);
        Scanner dict_reader = null;
        try {
            dict_reader = new Scanner(dict_file); // throws FileNotFoundException
            while (dict_reader.hasNextLine()) {
                String w = dict_reader.nextLine();
                this.words.add(w);
            }
            validateWords();

            dict_reader.close();
        } catch (Exception e) {
            throw e;
        }

    }

    public void printDictionary() {
            System.out.println(this.words);
    }

    public ArrayList<String> getWords() {
        return this.words;
    }
}
