package org.multimedia.hangman;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PreviousStats {

    static String statsFile = "./src/main/java/org/multimedia/hangman/Stats/stats.txt";

    public static void appendToStatsFile(String newLine) throws Exception {
        try {
            Writer output;
            output = new BufferedWriter(new FileWriter(statsFile, true));
            output.append(newLine + "\n");
            output.close();
        } catch (Exception e) {
            throw e;
        }
    }

    public static ArrayList<String> readStatsFile() {
        ArrayList<String> rows = new ArrayList<String>();
        try {
            File myObj = new File(statsFile);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                rows.add(data);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return rows;
    }
}

