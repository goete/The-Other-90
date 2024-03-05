package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LeaderboardsTest {
    Leaderboards leaderboards;
    Game wordRecollectionEasy;
    Game wordRecollectionMedium;
    Game wordRecollectionHard;
    Game sumEliminationEasy;
    Game sumEliminationMedium;
    Game sumEliminationHard;
    Leaderboard sumEliminationEasyBoard;
    Leaderboard sumEliminationMediumBoard;
    Leaderboard sumEliminationHardBoard;
    Leaderboard wordRecollectionEasyBoard;
    Leaderboard wordRecollectionMediumBoard;
    Leaderboard wordRecollectionHardBoard;
    Player player1;
    Player player2;

    @BeforeEach
    public void beforeEach() throws ClassNotFoundException, NoSuchMethodException, FileNotFoundException {
        this.leaderboards = new Leaderboards();
        this.wordRecollectionEasy = new WordRecollection("Easy");
        this.wordRecollectionMedium = new WordRecollection("Medium");
        this.wordRecollectionHard = new WordRecollection("Hard");

        this.sumEliminationEasy = new SumElimination("Easy");
        this.sumEliminationMedium = new SumElimination("Medium");
        this.sumEliminationHard = new SumElimination("Hard");

        this.player1 = new Player("one");
        this.player2 = new Player("two");

        this.sumEliminationEasyBoard = this.leaderboards.getSumEliminationEasy();
        this.sumEliminationMediumBoard = this.leaderboards.getSumEliminationMedium();
        this.sumEliminationHardBoard = this.leaderboards.getSumEliminationHard();

        this.wordRecollectionEasyBoard = this.leaderboards.getWordRecollectionEasy();
        this.wordRecollectionMediumBoard = this.leaderboards.getWordRecollectionMedium();
        this.wordRecollectionHardBoard = this.leaderboards.getWordRecollectionHard();

    }

    @Test
    public void playerAdding() throws InvocationTargetException, IllegalAccessException {
        player1.setSumEliminationEasyHighScore(1);
        player1.setSumEliminationMediumHighScore(1);
        player1.setSumEliminationHardHighScore(1);

        player1.setWordRecollectionEasyHighScore(1);
        player1.setWordRecollectionMediumHighScore(1);
        player1.setWordRecollectionHardHighScore(1);
        this.leaderboards.addPlayerToLeaderboard(player1, this.sumEliminationMedium);
        assertEquals(player1, this.sumEliminationMediumBoard.getTopNPlayers(1).get(0));
        this.leaderboards.addPlayerToLeaderboard(player1, this.sumEliminationEasy);
        assertEquals(player1, this.sumEliminationEasyBoard.getTopNPlayers(1).get(0));

        this.leaderboards.addPlayerToLeaderboard(player1, this.sumEliminationHard);
        assertEquals(player1, this.sumEliminationHardBoard.getTopNPlayers(1).get(0));

        this.leaderboards.addPlayerToLeaderboard(player1, this.wordRecollectionEasy);
        assertEquals(player1, this.wordRecollectionEasyBoard.getTopNPlayers(1).get(0));

        this.leaderboards.addPlayerToLeaderboard(player1, this.wordRecollectionMedium);
        assertEquals(player1, this.wordRecollectionMediumBoard.getTopNPlayers(1).get(0));

        this.leaderboards.addPlayerToLeaderboard(player1, this.wordRecollectionHard);
        assertEquals(player1, this.wordRecollectionHardBoard.getTopNPlayers(1).get(0));
    }

    @Test
    public void addingToAllTest() throws InvocationTargetException, IllegalAccessException {
        this.leaderboards.addToAllLeaderboards(player1);
        player1.setSumEliminationEasyHighScore(1);
        player1.setSumEliminationMediumHighScore(1);
        player1.setSumEliminationHardHighScore(1);

        player1.setWordRecollectionEasyHighScore(1);
        player1.setWordRecollectionMediumHighScore(1);
        player1.setWordRecollectionHardHighScore(1);

        assertEquals(player1, this.sumEliminationMediumBoard.getTopNPlayers(1).get(0));
        assertEquals(player1, this.sumEliminationEasyBoard.getTopNPlayers(1).get(0));
        assertEquals(player1, this.sumEliminationHardBoard.getTopNPlayers(1).get(0));
        assertEquals(player1, this.wordRecollectionEasyBoard.getTopNPlayers(1).get(0));
        assertEquals(player1, this.wordRecollectionMediumBoard.getTopNPlayers(1).get(0));
        assertEquals(player1, this.wordRecollectionHardBoard.getTopNPlayers(1).get(0));
    }

    @Test
    public void addingWithoutScore() throws InvocationTargetException, IllegalAccessException {
        this.leaderboards.addPlayerToLeaderboard(player1, this.wordRecollectionEasy);
        assertEquals(new ArrayList<Player>(), this.wordRecollectionEasyBoard.getTopNPlayers(1));
    }

    @Test
    public void toJsonTestEmpty() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Player p : this.leaderboards.getAllPlayers()) {
            jsonArray.put(p.toJson());
        }
        json.put("Players", jsonArray);
        assertEquals(json.toString(), this.leaderboards.toJson().toString());

    }

    @Test
    public void toJsonTestWithPlayers() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        player1.setWordRecollectionHardHighScore(1);
        player1.setSumEliminationEasyHighScore(3);
        player2.setSumEliminationEasyHighScore(2);
        player2.setSumEliminationHardHighScore(6);
        this.leaderboards.addToAllLeaderboards(player1);
        this.leaderboards.addToAllLeaderboards(player2);
        for (Player p : this.leaderboards.getAllPlayers()) {
            jsonArray.put(p.toJson());
        }
        json.put("Players", jsonArray);
        assertEquals(json.toString(), this.leaderboards.toJson().toString());

    }

}
