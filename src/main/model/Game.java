package model;

public abstract class Game {
    protected final String difficulty;
    protected int currentScore;
    private final String name;
    private final int roundTimeInSeconds;
    private long timeThatRoundStarted;


    // REQUIRES: difficulty is one of Easy, Medium, Hard
    // MODIFIES: this
    // EFFECTS: makes an instance of the game
    public Game(String difficulty, String name) {
        this.difficulty = difficulty;
        this.currentScore = 0;
        this.name = name;
        resetTime();
        if (this.difficulty.equals("Easy")) {
            this.roundTimeInSeconds = 20;
        } else if (this.difficulty.equals("Medium")) {
            this.roundTimeInSeconds = 15;
        } else {
            this.roundTimeInSeconds = 10;
        }
    }

    public String getDifficulty() {
        return this.difficulty;
    }

    public int getRoundTimeInSeconds() {
        return roundTimeInSeconds;
    }

    public long getTimeThatRoundStarted() {
        return timeThatRoundStarted;
    }

    // MODIFIES: this
    // EFFECTS: sets the timer to the current time to compare to
    public void resetTime() {
        this.timeThatRoundStarted = System.currentTimeMillis();
    }

    // EFFECTS: returns if the time for a given round has past
    public boolean isRoundOver() {
        return (this.timeThatRoundStarted + (this.roundTimeInSeconds * 1000L) <= System.currentTimeMillis());
    }

    // MODIFIES: this
    // EFFECTS: adds one to current score
    public void addOneToScore() {
        this.currentScore++;
    }

    public int getCurrentScore() {
        return this.currentScore;
    }

    // REQUIRES: 0 <= min <= max
    // EFFECTS: returns a random number
    protected int randomNumberGenerator(int min, int max) {
        //Min + (int)(Math.random() * ((Max - Min) + 1)) gives number [Min, Max]
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    public String getName() {
        return name;
    }

    public abstract void resetGame();
}
