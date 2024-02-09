package model;

import java.util.ArrayList;

public class Leaderboard {
    String name;
    ArrayList<Player> board;

    // name is [Sum Elimination|Word Recollection] [Easy|Medium|Hard]
    public Leaderboard(String name) {
        this.name = name;
        this.board = new ArrayList<>();
    }

    // REQUIRES: player just completed game
    public void addToLeaderboard(Player player) {
        if (!board.contains(player)) {
            board.add(player);
        }
    }

    private void sortByScore() {

    }


}
