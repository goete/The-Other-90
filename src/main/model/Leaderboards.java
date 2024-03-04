package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Leaderboards implements Writable {
    private final Leaderboard sumEliminationEasy;
    private final Leaderboard sumEliminationMedium;
    private final Leaderboard sumEliminationHard;
    private final Leaderboard wordRecollectionEasy;
    private final Leaderboard wordRecollectionMedium;
    private final Leaderboard wordRecollectionHard;
    private final ArrayList<Player> allPlayers;

    // EFFECTS: initializes all the leaderboards
    public Leaderboards() throws ClassNotFoundException, NoSuchMethodException {
        this.allPlayers = new ArrayList<>();
        this.sumEliminationEasy = new Leaderboard("Sum Elimination Easy");
        this.sumEliminationMedium = new Leaderboard("Sum Elimination Medium");
        this.sumEliminationHard = new Leaderboard("Sum Elimination Hard");
        this.wordRecollectionEasy = new Leaderboard("Word Recollection Easy");
        this.wordRecollectionMedium = new Leaderboard("Word Recollection Medium");
        this.wordRecollectionHard = new Leaderboard("Word Recollection Hard");

    }

    // MODIFIES: this
    // EFFECT: adds the player to the correct game and to master list if not there
    public void addPlayerToLeaderboard(Player player, Game game) throws InvocationTargetException,
            IllegalAccessException {
        if (!this.allPlayers.contains(player)) {
            this.allPlayers.add(player);
        }
        if (game.getName().equals("Word Recollection")) {
            wordRecollectionAdding(player, game);
        } else {
            sumEliminationAdding(player, game);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds the player to the correct difficulty
    private void sumEliminationAdding(Player player, Game game) throws InvocationTargetException,
            IllegalAccessException {
        if (game.getDifficulty().equals("Easy")) {
            this.sumEliminationEasy.addToLeaderboard(player, game);
        } else if (game.getDifficulty().equals("Medium")) {
            this.sumEliminationMedium.addToLeaderboard(player, game);
        } else {
            this.sumEliminationHard.addToLeaderboard(player, game);
        }
    }


    // MODIFIES: this
    // EFFECTS: adds the player to the correct difficulty
    private void wordRecollectionAdding(Player player, Game game) throws InvocationTargetException,
            IllegalAccessException {
        if (game.getDifficulty().equals("Easy")) {
            this.wordRecollectionEasy.addToLeaderboard(player, game);
        } else if (game.getDifficulty().equals("Medium")) {
            this.wordRecollectionMedium.addToLeaderboard(player, game);
        } else {
            this.wordRecollectionHard.addToLeaderboard(player, game);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds player to leaderboards all at once and to master list if not there
    public void addToAllLeaderboards(Player player) {
        if (!this.allPlayers.contains(player)) {
            this.allPlayers.add(player);
        }
        this.sumEliminationEasy.addToLeaderboard(player);
        this.sumEliminationMedium.addToLeaderboard(player);
        this.sumEliminationHard.addToLeaderboard(player);
        this.wordRecollectionEasy.addToLeaderboard(player);
        this.wordRecollectionMedium.addToLeaderboard(player);
        this.wordRecollectionHard.addToLeaderboard(player);
    }

    public Leaderboard getSumEliminationEasy() {
        return sumEliminationEasy;
    }

    public Leaderboard getSumEliminationMedium() {
        return sumEliminationMedium;
    }

    public Leaderboard getSumEliminationHard() {
        return sumEliminationHard;
    }

    public Leaderboard getWordRecollectionEasy() {
        return wordRecollectionEasy;
    }

    public Leaderboard getWordRecollectionMedium() {
        return wordRecollectionMedium;
    }

    public Leaderboard getWordRecollectionHard() {
        return wordRecollectionHard;
    }

    public ArrayList<Player> getAllPlayers() {
        return allPlayers;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Players", leaderboardsToJson());
        return json;
    }

    // EFFECTS: returns all players in any leaderboard ever as a JSON array
    private JSONArray leaderboardsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Player p : allPlayers) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

}
