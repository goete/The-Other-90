package model;

import java.util.ArrayList;

public class Leaderboards {
    ArrayList<Leaderboard> leaderboards;
    Leaderboard sumEliminationEasy;
    Leaderboard sumEliminationMedium;
    Leaderboard sumEliminationHard;
    Leaderboard wordRecollectionEasy;
    Leaderboard wordRecollectionMedium;
    Leaderboard wordRecollectionHard;

    public Leaderboards() {
        this.leaderboards = new ArrayList<>();

        this.sumEliminationEasy = new Leaderboard("Sum Elimination Easy");
        this.sumEliminationMedium = new Leaderboard("Sum Elimination Medium");
        this.sumEliminationHard = new Leaderboard("Sum Elimination Hard");
        this.wordRecollectionEasy = new Leaderboard("Word Recollection Easy");
        this.wordRecollectionMedium = new Leaderboard("Word Recollection Medium");
        this.wordRecollectionHard = new Leaderboard("Word Recollection Hard");

        this.leaderboards.add(this.sumEliminationEasy);
        this.leaderboards.add(this.sumEliminationMedium);
        this.leaderboards.add(this.sumEliminationHard);
        this.leaderboards.add(this.wordRecollectionEasy);
        this.leaderboards.add(this.wordRecollectionMedium);
        this.leaderboards.add(this.wordRecollectionHard);
    }
}
