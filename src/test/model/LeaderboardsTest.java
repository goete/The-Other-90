package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LeaderboardsTest {
    Leaderboards leaderboards;
    Game getWordRecollectionEasy;
    Game wordRecollectionMedium;
    Game wordRecollectionHard;
    Game sumEliminationEasy;
    Game sumEliminationMedium;
    Game sumEliminationHard;
    Player player1;
    Player player2;

    @BeforeEach
    public void beforeEach() {
        this.leaderboards = new Leaderboards();
        this.getWordRecollectionEasy = new WordRecollection("Easy");
        this.wordRecollectionMedium = new WordRecollection("Medium");
        this.wordRecollectionHard = new WordRecollection("Hard");

        this.sumEliminationEasy = new SumElimination("Easy");
        this.sumEliminationMedium = new SumElimination("Medium");
        this.sumEliminationHard = new SumElimination("Hard");


    }

    @Test
    public void playerAdding() {
    }
}
