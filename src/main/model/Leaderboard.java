package model;

import model.events.Event;
import model.events.EventLog;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Leaderboard {
    private final String name;
    private final ArrayList<Player> board;
    private ArrayList<Player> storage;
    private final String gettingMethodNameAsString;
    private final Method gettingMethod;
    private final Method settingMethod;


    // REQUIRES: name is [Sum Elimination|Word Recollection] [Easy|Medium|Hard]
    // EFFECTS: makes a leaderboard for given game and difficulty
    public Leaderboard(String name) {
        this.name = name;
        this.board = new ArrayList<>();
        this.storage = new ArrayList<>();
        this.gettingMethodNameAsString = this.convertToCorrectNameGetting();

        try {
            this.gettingMethod = Class.forName("model.Player").getMethod(gettingMethodNameAsString);
            this.settingMethod = Class.forName("model.Player").getMethod(this.convertToCorrectNameSetting(), int.class);
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    // EFFECTS: returns the correct name for the setter method to be called on player
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
    public void addToLeaderboard(Player player, Game game) throws InvocationTargetException, IllegalAccessException {
        if (!board.contains(player)) {
            board.add(player);
        }
        this.settingMethod.invoke(player, game.getCurrentScore());
    }

    // MODIFIES: this
    // EFFECTS: adds given player to leaderboard
    // USAGE: using to reload the leaderboards once the players have been loaded in
    public void addToLeaderboard(Player player) {
        if (!board.contains(player)) {
            board.add(player);
        }
    }

    // EFFECTS: returns the high score of the player for the correct mode
    public int getSpecificPlayersHighScore(Player player) throws InvocationTargetException, IllegalAccessException {
        // this is solely for testing purposes
        // this would be very useless to actually use, outside of tests
        return (int) this.gettingMethod.invoke(player);
    }

    // EFFECTS: returns if the board is empty
    public boolean isEmpty() {
        return this.board.isEmpty();
    }


    // REQUIRES: !this.isEmpty() && n > 0
    // MODIFIES: this.storage
    // EFFECTS: returns a list of the top n players, or all players if n > board.size()
    //          in order such that the highest score is at index 0
    //          Also logs the event
    public ArrayList<Player> getTopNPlayers(int n) throws InvocationTargetException, IllegalAccessException {
        this.storage = new ArrayList<>();
        if (n > this.board.size()) {
            EventLog.getInstance().logEvent(new Event(
                    this.name + "'s top " + this.board.size() + " players was accessed"));
            return this.getNPlayersRecursion(this.board.size(), (ArrayList<Player>) this.board.clone(), this.storage);
        } else {
            EventLog.getInstance().logEvent(new Event(
                    this.name + "'s top " + n + " players was accessed"));
            return this.getNPlayersRecursion(n, (ArrayList<Player>) this.board.clone(), this.storage);
        }


    }

    // REQUIRES: n > 0
    // EFFECTS: through recursion, returns the top remaining player in the leaderboard to be added, then adds the next
    //          n - 1 top players
    //          If the highest player has a score of 0, it stops returning
    private ArrayList<Player> getNPlayersRecursion(
            int n, ArrayList<Player> toGoThrough, ArrayList<Player> correctOrder) throws InvocationTargetException,
            IllegalAccessException {
        Player hold = toGoThrough.get(0);
        for (Player player : toGoThrough) {
            if (this.getSpecificPlayersHighScore(player) > this.getSpecificPlayersHighScore(hold)) {
                hold = player;
            }
        }
        if (this.getSpecificPlayersHighScore(hold) == 0) {
            return correctOrder;
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
