package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LeaderboardTest {
    Leaderboard sumEliminationEasy;
    Leaderboard sumEliminationMedium;
    Leaderboard wordRecollectionHard;
    Game wordRecollectionHardGame;
    Game sumEliminationEasyGame;
    Game sumEliminationMediumGame;


    @BeforeEach
    public void beforeEach() throws ClassNotFoundException, NoSuchMethodException, FileNotFoundException {
        this.sumEliminationEasy = new Leaderboard("Sum Elimination Easy");
        this.sumEliminationMedium = new Leaderboard("Sum Elimination Medium");
        this.wordRecollectionHard = new Leaderboard("Word Recollection Hard");

        this.wordRecollectionHardGame = new WordRecollection("Hard");
        this.sumEliminationEasyGame = new SumElimination("Easy");
        this.sumEliminationMediumGame = new SumElimination("Medium");
    }

    @Test
    public void isEmpty() {
        assertTrue(this.sumEliminationEasy.isEmpty());
    }

    @Test
    public void namingCorrectMethodTest() {
        assertEquals("getSumEliminationEasyHighScore", this.sumEliminationEasy.getGettingMethodNameAsString());
        assertEquals("getSumEliminationMediumHighScore", this.sumEliminationMedium.getGettingMethodNameAsString());
        assertEquals("getWordRecollectionHardHighScore", this.wordRecollectionHard.getGettingMethodNameAsString());
    }

    @Test
    public void getSpecificPlayersHighScoreTest() throws InvocationTargetException, IllegalAccessException {
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

    @Test
    public void addingToLeaderboardTest() throws InvocationTargetException, IllegalAccessException {
        Player player1 = new Player("one");
        Player player2 = new Player("two");

        player1.setWordRecollectionHardHighScore(2);
        player2.setWordRecollectionHardHighScore(3);
        assertTrue(this.wordRecollectionHard.isEmpty());
        this.wordRecollectionHard.addToLeaderboard(player1, wordRecollectionHardGame);
        assertFalse(this.wordRecollectionHard.isEmpty());
        assertEquals(1, this.wordRecollectionHard.leaderboardSize());
        this.wordRecollectionHard.addToLeaderboard(player2, wordRecollectionHardGame);
        assertEquals(2, this.wordRecollectionHard.leaderboardSize());
        this.wordRecollectionHard.addToLeaderboard(player1, wordRecollectionHardGame);
        assertEquals(2, this.wordRecollectionHard.leaderboardSize());
    }

    @Test
    public void gettingTopPlayers() throws InvocationTargetException, IllegalAccessException {
        Player player1 = new Player("one");
        Player player2 = new Player("two");
        Player player3 = new Player("three");
        ArrayList<Player> full = new ArrayList<>();
        ArrayList<Player> single = new ArrayList<>();
        full.add(player1);
        full.add(player2);
        single.add(player1);
        player1.setWordRecollectionHardHighScore(3);
        player2.setWordRecollectionHardHighScore(2);
        player3.setWordRecollectionHardHighScore(10);

        this.wordRecollectionHard.addToLeaderboard(player1, wordRecollectionHardGame);

        assertEquals(single, this.wordRecollectionHard.getTopNPlayers(1));
        assertFalse(this.wordRecollectionHard.isEmpty());
        assertEquals(single, this.wordRecollectionHard.getTopNPlayers(10));

        this.wordRecollectionHard.addToLeaderboard(player2, wordRecollectionHardGame);

        assertEquals(single, this.wordRecollectionHard.getTopNPlayers(1));
        assertEquals(full, this.wordRecollectionHard.getTopNPlayers(2));
        assertEquals(full, this.wordRecollectionHard.getTopNPlayers(10));

        full.add(0, player3);

        this.wordRecollectionHard.addToLeaderboard(player3, wordRecollectionHardGame);
        assertEquals(full.subList(0, 1), this.wordRecollectionHard.getTopNPlayers(1));
        assertEquals(full, this.wordRecollectionHard.getTopNPlayers(3));
        assertEquals(full, this.wordRecollectionHard.getTopNPlayers(10));
    }

}
