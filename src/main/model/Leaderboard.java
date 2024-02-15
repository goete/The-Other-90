package model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Leaderboard {
    private String name;
    private ArrayList<Player> board;
    private ArrayList<Player> storage;
    private final String gettingMethodNameAsString;
    private final String settingMethodNameAsString;
    private Method gettingMethod;
    private Method settingMethod;


    // REQUIRES: name is [Sum Elimination|Word Recollection] [Easy|Medium|Hard]
    // EFFECTS: makes a leaderboard for given game and difficulty
    public Leaderboard(String name) {
        this.name = name;
        this.board = new ArrayList<>();
        this.storage = new ArrayList<>();
        this.gettingMethodNameAsString = this.convertToCorrectNameGetting();
        this.settingMethodNameAsString = this.convertToCorrectNameSetting();

        try {
            this.gettingMethod = Class.forName("model.Player").getMethod(gettingMethodNameAsString);
            this.settingMethod = Class.forName("model.Player").getMethod(settingMethodNameAsString, int.class);
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private String convertToCorrectNameSetting() {
        return "set" + this.name.replace(" ", "") + "HighScore";

    }

    // EFFECTS: returns the size of the leaderboard
    public int leaderboardSize() {
        return this.board.size();
    }

    // EFFECTS: returns the name of the method to be called on player
    private String convertToCorrectNameGetting() {
        return "get" + this.name.replace(" ", "") + "HighScore";
    }

    // MODIFIES: this
    // EFFECTS: adds the given player to the leaderboard if they
    //          completed the game for the first time
    //          also updates the players high score for that game
    public void addToLeaderboard(Player player, Game game) {
        if (!board.contains(player)) {
            board.add(player);
        }
        try {
            this.settingMethod.invoke(player, game.getCurrentScore());
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }

    // EFFECTS: returns the high score of the player for the correct mode
    public int getSpecificPlayersHighScore(Player player) {
        // this is solely for testing purposes
        // this would be very useless to actually use, outside of tests
        try {
            return (int) this.gettingMethod.invoke(player);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }

    // EFFECTS: returns if the board is empty
    public boolean isEmpty() {
        return this.board.isEmpty();
    }


    // REQUIRES: !this.isEmpty() && n > 0
    // MODIFIES: this.storage
    // EFFECTS: returns a list of the top n players, or all players if n > board.size()
    //          in order such that the highest score is at index 0
    public ArrayList<Player> getTopNPlayers(int n) {
        this.storage = new ArrayList<>();
        if (n > this.board.size()) {
            return this.getNPlayersRecursion(this.board.size(), (ArrayList<Player>) this.board.clone(), this.storage);
        } else {
            return this.getNPlayersRecursion(n, (ArrayList<Player>) this.board.clone(), this.storage);

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

    public String getGettingMethodNameAsString() {
        return gettingMethodNameAsString;
    }


}
