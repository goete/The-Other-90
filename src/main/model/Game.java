package model;

public class Game {
    protected final String difficulty;
    protected int currentScore;
    private final String name;

    // THE TIMER YOU BUFFOON

    // REQUIRES: difficulty is one of Easy, Medium, Hard
    // EFFECTS: makes an instance of the game
    public Game(String difficulty, String name) {
        this.difficulty = difficulty;
        this.currentScore = 0;
        this.name = name;
    }

    public String getDifficulty() {
        return this.difficulty;
    }

    // MODIFIES: this
    // EFFECTS: adds one to current score
    public void addOneToScore() {
        this.currentScore++;
    }

    public int getCurrentScore() {
        return this.currentScore;
    }

    // REQUIRES: min < max
    // EFFECTS: returns a random number
    protected int randomNumberGenerator(int min, int max) {
        //Min + (int)(Math.random() * ((Max - Min) + 1)) gives number [Min, Max]
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    public String getName() {
        return name;
    }
}
