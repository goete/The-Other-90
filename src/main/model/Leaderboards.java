package model;

import java.lang.reflect.InvocationTargetException;

public class Leaderboards {
    private final Leaderboard sumEliminationEasy;
    private final Leaderboard sumEliminationMedium;
    private final Leaderboard sumEliminationHard;
    private final Leaderboard wordRecollectionEasy;
    private final Leaderboard wordRecollectionMedium;
    private final Leaderboard wordRecollectionHard;

    // EFFECTS: initializes all the leaderboards
    public Leaderboards() throws ClassNotFoundException, NoSuchMethodException {
        this.sumEliminationEasy = new Leaderboard("Sum Elimination Easy");
        this.sumEliminationMedium = new Leaderboard("Sum Elimination Medium");
        this.sumEliminationHard = new Leaderboard("Sum Elimination Hard");
        this.wordRecollectionEasy = new Leaderboard("Word Recollection Easy");
        this.wordRecollectionMedium = new Leaderboard("Word Recollection Medium");
        this.wordRecollectionHard = new Leaderboard("Word Recollection Hard");
    }

    // MODIFIES: this
    // EFFECT: adds the player to the correct game
    public void addPlayerToLeaderboard(Player player, Game game) throws InvocationTargetException,
            IllegalAccessException {
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

    public void addToAllLeaderboards(Player player) {
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
}
