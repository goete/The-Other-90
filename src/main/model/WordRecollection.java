package model;

import java.util.ArrayList;

public class WordRecollection extends Game {
    private Dictionary dictionary;
    private ArrayList<String> wordsFound;
    private String mostRecentNewWord;

    // REQUIRES: difficulty is one of Easy, Medium, Hard
    // MODIFIES: this
    // EFFECTS: makes a WordRecollection
    public WordRecollection(String difficulty) {
        super(difficulty);
        this.dictionary = new Dictionary(difficulty);
        this.wordsFound = new ArrayList<>();
        this.mostRecentNewWord = null;
    }


    public ArrayList<String> getWordsFound() {
        return this.wordsFound;
    }

    // MODIFIES: this
    // EFFECTS: returns the next word for the player to determine
    public String getNextWord() {
        if (this.wordsFound.size() >= 3 && super.randomNumberGenerator(0, 100) >= 30) {
            return this.wordsFound.get(super.randomNumberGenerator(0, this.wordsFound.size() - 1));
        } else {
            this.mostRecentNewWord = this.dictionary.getRandomAndRemove();
            this.wordsFound.add(this.mostRecentNewWord);
            return this.mostRecentNewWord;
        }
    }


}
