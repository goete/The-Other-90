package model;

public class Game {
    protected final String difficulty;
    protected int currentScore;

    // THE TIMER YOU BUFFOON

    // REQUIRES: difficulty is one of Easy, Medium, Hard
    public Game(String difficulty) {
        this.difficulty = difficulty;
        this.currentScore = 0;
    }

    public String getDifficulty() {
        return this.difficulty;
    }

    public void addOneToScore() {
        this.currentScore++;
    }

    public int getCurrentScore() {
        return this.currentScore;
    }
}