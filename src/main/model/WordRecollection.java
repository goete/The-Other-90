package model;

import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class WordRecollection extends Game {
    private Dictionary dictionary;
    private ArrayList<String> wordsFound;
    private String mostRecentNewWord;
    private boolean seenLastWordBefore;

    // REQUIRES: difficulty is one of Easy, Medium, Hard
    // MODIFIES: this
    // EFFECTS: makes a WordRecollection
    public WordRecollection(String difficulty) throws FileNotFoundException {
        super(difficulty, "Word Recollection");
        this.dictionary = new Dictionary(difficulty);
        this.wordsFound = new ArrayList<>();
        this.mostRecentNewWord = null;
    }

    // MODIFIES: this
    // EFFECTS: resets all the needed parts
    public void resetGame() throws FileNotFoundException {
        this.dictionary = new Dictionary(difficulty);
        this.wordsFound = new ArrayList<>();
        this.mostRecentNewWord = null;
        super.resetTime();
    }


    public ArrayList<String> getWordsFound() {
        return this.wordsFound;
    }

    // MODIFIES: this
    // EFFECTS: returns the next word for the player to determine and resets round timer
    public String getNextWord() {
        super.resetTime();
        if (this.dictionary.isEmpty() | (this.wordsFound.size() >= 3
                && super.randomNumberGenerator(0, 100) >= 85)) { // Frequency: 15%
            this.seenLastWordBefore = true;
            return this.wordsFound.get(super.randomNumberGenerator(0, this.wordsFound.size() - 1));
        } else {
            this.mostRecentNewWord = this.dictionary.getRandomAndRemove();
            this.wordsFound.add(this.mostRecentNewWord);
            this.seenLastWordBefore = false;
            return this.mostRecentNewWord;
        }
    }

    public boolean isSeenLastWordBefore() {
        return seenLastWordBefore;
    }

    public Dictionary getDictionary() {
        return this.dictionary;
    }

}
