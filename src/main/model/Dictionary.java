package model;

import java.io.File;
import java.util.Scanner;


public class Dictionary {
    private String[] dictionary;
    private int size;
    private Scanner input = null;
    private final int numOfWords;
    private final String fileName;

    // REQUIRES: difficulty is one of Easy, Medium, Hard
    public Dictionary(String difficulty) {
        if (difficulty.equals("Easy")) {
            this.numOfWords = 463;
            this.fileName = "data/WordsForEasy";
        } else if (difficulty.equals("Medium")) {
            this.numOfWords = 897;
            this.fileName = "data/WordsForMedium";
        } else {
            this.numOfWords = 1310;
            this.fileName = "data/WordsForHard";
        }
        this.dictionary = new String[this.numOfWords];
        this.size = 0;
        try {
            this.input = new Scanner(new File(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (this.input.hasNext()) {
            this.dictionary[this.size] = this.input.next();
            this.size++;
            this.input.nextLine();
        }
    }

    public String[] getDictionary() {
        return dictionary;
    }

    public int getSize() {
        return size;
    }

    public int getNumOfWords() {
        return numOfWords;
    }

    public String getFileName() {
        return fileName;
    }
}
