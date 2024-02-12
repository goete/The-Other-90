package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeaderboardTest {
    Leaderboard sumEliminationEasy;
    Leaderboard sumEliminationMedium;
    Leaderboard wordRecollectionHard;

    @BeforeEach
    public void beforeEach() {
        this.sumEliminationEasy = new Leaderboard("Sum Elimination Easy");
        this.sumEliminationMedium = new Leaderboard("Sum Elimination Medium");
        this.wordRecollectionHard = new Leaderboard("Word Recollection Hard");
    }

    @Test
    public void isEmpty() {
        assertTrue(this.sumEliminationEasy.isEmpty());
    }

    @Test
    public void namingCorrectMethodTest() {
        assertEquals("getSumEliminationEasyHighScore", this.sumEliminationEasy.getMethodNameAsString());
        assertEquals("getSumEliminationMediumHighScore", this.sumEliminationMedium.getMethodNameAsString());
        assertEquals("getWordRecollectionHardHighScore", this.wordRecollectionHard.getMethodNameAsString());
    }

    @Test
    public void getSpecificPlayersHighScoreTest() {
        Player player = new Player("john");
        player.setSumEliminationEasyHighScore(12);
        assertEquals(12, this.sumEliminationEasy.getSpecificPlayersHighScore(player));
        assertEquals(0, this.sumEliminationMedium.getSpecificPlayersHighScore(player));
        player.setSumEliminationMediumHighScore(20);
        assertEquals(20, this.sumEliminationMedium.getSpecificPlayersHighScore(player));
        assertEquals(0, this.wordRecollectionHard.getSpecificPlayersHighScore(player));
        player.setWordRecollectionHardHighScore(100);
        assertEquals(100, this.wordRecollectionHard.getSpecificPlayersHighScore(player));

    }

}
