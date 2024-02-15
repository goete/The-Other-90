package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary {
    private ArrayList<String> dictionary;
    private final String fileName;

    // REQUIRES: difficulty is one of Easy, Medium, Hard
    public Dictionary(String difficulty) throws FileNotFoundException {
        // While this could be written in less lines, this adds a guard to the Scanner
        if (difficulty.equals("Easy")) {
            this.fileName = "data/WordsForEasy";
        } else if (difficulty.equals("Medium")) {
            this.fileName = "data/WordsForMedium";
        } else {
            this.fileName = "data/WordsForHard";
        }
        this.dictionary = new ArrayList<>();
        Scanner input = new Scanner(new File(fileName));

        // Works because files end with an empty line
        while (input.hasNext()) {
            this.dictionary.add(input.next());
            input.nextLine();
        }
    }

    public ArrayList<String> getDictionary() {
        return dictionary;
    }

    public int getNumOfWords() {
        return this.dictionary.size();
    }

    public String getFileName() {
        return fileName;
    }

    public boolean isEmpty() {
        return this.dictionary.size() == 0;
    }

    public boolean contains(String word) {
        return this.dictionary.contains(word);
    }

    public String getRandomAndRemove() {
        return this.dictionary.remove(this.randomNumberGenerator());
    }

    public int randomNumberGenerator() {
        //Min + (int)(Math.random() * ((Max - Min) + 1)) gives number [Min, Max]
        // Min = 0, Max = dictionary.size() - 1
        return (int) (Math.random() * ((this.dictionary.size() - 1) + 1));
    }
}
