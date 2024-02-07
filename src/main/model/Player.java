package model;

public class Player {
    private final String name;
    private int sumEliminationEasyHighScore;
    private int sumEliminationMediumHighScore;
    private int sumEliminationHardHighScore;
    private int wordRecollectionEasyHighScore;
    private int wordRecollectionMediumHighScore;
    private int wordRecollectionHardHighScore;

    // EFFECTS: creates an instance of this class with all high scores set to 0 and the given name
    public Player(String name) {
        this.name = name;
        this.sumEliminationEasyHighScore = 0;
        this.sumEliminationMediumHighScore = 0;
        this.sumEliminationHardHighScore = 0;
        this.wordRecollectionHardHighScore = 0;
        this.wordRecollectionMediumHighScore = 0;
        this.wordRecollectionEasyHighScore = 0;
    }

    public String getName() {
        return name;
    }

    public int getSumEliminationEasyHighScore() {
        return sumEliminationEasyHighScore;
    }

    //EFFECTS: updates the high score for Sum Elimination: Easy if given is greater than current
    //MODIFIES: this
    public void setSumEliminationEasyHighScore(int sumEliminationEasyHighScore) {
        if (this.sumEliminationEasyHighScore < sumEliminationEasyHighScore) {
            this.sumEliminationEasyHighScore = sumEliminationEasyHighScore;
        }
    }

    public int getSumEliminationMediumHighScore() {
        return sumEliminationMediumHighScore;
    }

    //EFFECTS: updates the high score for Sum Elimination: Medium if given is greater than current
    //MODIFIES: this
    public void setSumEliminationMediumHighScore(int sumEliminationMediumHighScore) {
        if (this.sumEliminationMediumHighScore < sumEliminationMediumHighScore) {
            this.sumEliminationMediumHighScore = sumEliminationMediumHighScore;
        }
    }

    public int getSumEliminationHardHighScore() {
        return sumEliminationHardHighScore;
    }

    //EFFECTS: updates the high score for Sum Elimination: Hard if given is greater than current
    //MODIFIES: this
    public void setSumEliminationHardHighScore(int sumEliminationHardHighScore) {
        if (this.sumEliminationHardHighScore < sumEliminationHardHighScore) {
            this.sumEliminationHardHighScore = sumEliminationHardHighScore;
        }
    }

    public int getWordRecollectionEasyHighScore() {
        return wordRecollectionEasyHighScore;
    }

    //EFFECTS: updates the high score for Word Recollection: Easy if given is greater than current
    //MODIFIES: this
    public void setWordRecollectionEasyHighScore(int wordRecollectionEasyHighScore) {
        if (this.wordRecollectionEasyHighScore < wordRecollectionEasyHighScore) {
            this.wordRecollectionEasyHighScore = wordRecollectionEasyHighScore;
        }
    }

    public int getWordRecollectionMediumHighScore() {
        return wordRecollectionMediumHighScore;
    }

    //EFFECTS: updates the high score for Word Recollection: Medium if given is greater than current
    //MODIFIES: this
    public void setWordRecollectionMediumHighScore(int wordRecollectionMediumHighScore) {
        if (this.wordRecollectionMediumHighScore < wordRecollectionMediumHighScore) {
            this.wordRecollectionMediumHighScore = wordRecollectionMediumHighScore;
        }
    }

    public int getWordRecollectionHardHighScore() {
        return wordRecollectionHardHighScore;
    }

    //EFFECTS: updates the high score for Word Recollection: Hard if given is greater than current
    //MODIFIES: this
    public void setWordRecollectionHardHighScore(int wordRecollectionHardHighScore) {
        if (this.wordRecollectionHardHighScore < wordRecollectionHardHighScore) {
            this.wordRecollectionHardHighScore = wordRecollectionHardHighScore;
        }
    }



}
