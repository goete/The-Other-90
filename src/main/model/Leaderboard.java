package model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Leaderboard {
    private String name;
    private ArrayList<Player> board;
    private ArrayList<Player> storage;
    private final String methodNameAsString;
    private Method method;


    // REQUIRES: name is [Sum Elimination|Word Recollection] [Easy|Medium|Hard]
    // EFFECTS: makes a leaderboard for given game and difficulty
    public Leaderboard(String name) {
        this.name = name;
        this.board = new ArrayList<>();
        this.storage = new ArrayList<>();
        this.methodNameAsString = this.convertToCorrectName();

        try {
            this.method = Class.forName("model.Player").getMethod(methodNameAsString);
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    // EFFECTS: returns the name of the method to be called on player
    private String convertToCorrectName() {
        return "get" + this.name.replace(" ", "") + "HighScore";
    }

    // MODIFIES: this
    // EFFECTS: adds the given player to the leaderboard if they
    //          completed the game for the first time
    public void addToLeaderboard(Player player) {
        if (!board.contains(player)) {
            board.add(player);
        }
    }

    // EFFECTS: returns the high score of the player for the correct mode
    public int getSpecificPlayersHighScore(Player player) {
        // this is solely for testing purposes
        // this would be very useless to actually use, outside of tests
        try {
            return (int) this.method.invoke(player);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }

    // EFFECTS: returns if the board is empty
    public boolean isEmpty() {
        return this.board.isEmpty();
    }


    // REQUIRES: !this.isEmpty()
    // MODIFIES: this.storage
    // EFFECTS: returns a list of the top n players, or all players if n > board.size()
    //          in order such that the highest score is at index 0
    public ArrayList<Player> getTopNPlayers(int n) {
        this.storage = new ArrayList<>();
        if (n > this.board.size()) {
            return this.getNPlayersRecursion(this.board.size(), this.board, this.storage);
        } else {
            return this.getNPlayersRecursion(n, this.board, this.storage);

        }


    }

    private ArrayList<Player> getNPlayersRecursion(
            int n, ArrayList<Player> toGoThrough, ArrayList<Player> correctOrder) {
        Player hold = toGoThrough.get(0);
        for (Player player : toGoThrough) {
            if (this.getSpecificPlayersHighScore(player) > this.getSpecificPlayersHighScore(hold)) {
                hold = player;
            }
        }
        toGoThrough.remove(hold);
        correctOrder.add(hold);
        if (n == 1) {
            return correctOrder;
        } else {
            return this.getNPlayersRecursion(n - 1, toGoThrough, correctOrder);
        }
    }

    public String getMethodNameAsString() {
        return methodNameAsString;
    }


}
