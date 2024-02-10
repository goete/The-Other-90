package model;

import java.util.ArrayList;

public class Leaderboard {
    private String name;
    private ArrayList<Player> board;
    private ArrayList<Player> storage;

    // name is [Sum Elimination|Word Recollection] [Easy|Medium|Hard]
    public Leaderboard(String name) {
        this.name = name;
        this.board = new ArrayList<>();
        this.storage = new ArrayList<>();
    }

    // REQUIRES: player just completed game
    public void addToLeaderboard(Player player) {
        if (!board.contains(player)) {
            board.add(player);
        }
    }

    private void sortByScore() {

    }

    public boolean isEmpty() {
        return this.board.isEmpty();
    }


    // REQUIRES: !this.isEmpty()
    // EFFECTS: returns a list of the top n players, or all players if n > board.size()
    public ArrayList<Player> getTopNPlayers(int n) {
        this.storage = new ArrayList<>();
        Player hold;
        for (int i = 0; i < n && i < this.board.size(); i++) {
            hold = this.board.get(0);
            for (int j = 0; j < this.board.size(); j++) {

            }
            this.storage.add(hold);
        }
        //stub
        return this.storage;
    }


}
