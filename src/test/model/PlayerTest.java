package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {
    Player playerOne;
    Player playerTwo;
    Player john;

    @BeforeEach
    public void beforeEach() {
        this.john = new Player("John");
        this.playerOne = new Player("player one");
        this.playerTwo = new Player("player two");
    }

    @Test
    public void setUpTest() {
        assertEquals("John", john.getName());
        assertEquals(0, john.getSumEliminationEasyHighScore());
        assertEquals(0, john.getSumEliminationHardHighScore());
        assertEquals(0, john.getSumEliminationMediumHighScore());
        assertEquals(0, john.getWordRecollectionEasyHighScore());
        assertEquals(0, john.getWordRecollectionMediumHighScore());
        assertEquals(0, john.getWordRecollectionHardHighScore());
    }

    @Test
    public void settingHighScoresSumTest() {
        playerOne.setSumEliminationEasyHighScore(5);
        assertEquals(5, playerOne.getSumEliminationEasyHighScore());
        playerOne.setSumEliminationHardHighScore(5);
        assertEquals(5, playerOne.getSumEliminationHardHighScore());
        playerOne.setSumEliminationMediumHighScore(5);
        assertEquals(5, playerOne.getSumEliminationMediumHighScore());

    }

    @Test
    public void settingHighScoresWordRecollectionTest() {
        playerOne.setWordRecollectionEasyHighScore(5);
        assertEquals(5, playerOne.getWordRecollectionEasyHighScore());
        playerOne.setWordRecollectionMediumHighScore(5);
        assertEquals(5, playerOne.getWordRecollectionMediumHighScore());
        playerOne.setWordRecollectionHardHighScore(5);
        assertEquals(5, playerOne.getWordRecollectionHardHighScore());
    }

    @Test
    public void settingBothHighScoresSumTest() {
        playerOne.setWordRecollectionEasyHighScore(5);
        assertEquals(5, playerOne.getWordRecollectionEasyHighScore());
        playerOne.setWordRecollectionMediumHighScore(5);
        assertEquals(5, playerOne.getWordRecollectionMediumHighScore());
        playerOne.setWordRecollectionHardHighScore(5);
        assertEquals(5, playerOne.getWordRecollectionHardHighScore());

        playerOne.setSumEliminationEasyHighScore(5);
        assertEquals(5, playerOne.getSumEliminationEasyHighScore());
        playerOne.setSumEliminationHardHighScore(5);
        assertEquals(5, playerOne.getSumEliminationHardHighScore());
        playerOne.setSumEliminationMediumHighScore(5);
        assertEquals(5, playerOne.getSumEliminationMediumHighScore());
    }

    @Test
    public void settingBothHighScoresWithLowerSecondAttempts() {
        playerOne.setWordRecollectionEasyHighScore(5);
        assertEquals(5, playerOne.getWordRecollectionEasyHighScore());
        playerOne.setWordRecollectionMediumHighScore(5);
        assertEquals(5, playerOne.getWordRecollectionMediumHighScore());
        playerOne.setWordRecollectionHardHighScore(5);
        assertEquals(5, playerOne.getWordRecollectionHardHighScore());

        playerOne.setSumEliminationEasyHighScore(5);
        assertEquals(5, playerOne.getSumEliminationEasyHighScore());
        playerOne.setSumEliminationHardHighScore(5);
        assertEquals(5, playerOne.getSumEliminationHardHighScore());
        playerOne.setSumEliminationMediumHighScore(5);
        assertEquals(5, playerOne.getSumEliminationMediumHighScore());

        playerOne.setWordRecollectionEasyHighScore(1);
        assertEquals(5, playerOne.getWordRecollectionEasyHighScore());
        playerOne.setWordRecollectionMediumHighScore(1);
        assertEquals(5, playerOne.getWordRecollectionMediumHighScore());
        playerOne.setWordRecollectionHardHighScore(1);
        assertEquals(5, playerOne.getWordRecollectionHardHighScore());

        playerOne.setSumEliminationEasyHighScore(1);
        assertEquals(5, playerOne.getSumEliminationEasyHighScore());
        playerOne.setSumEliminationHardHighScore(1);
        assertEquals(5, playerOne.getSumEliminationHardHighScore());
        playerOne.setSumEliminationMediumHighScore(1);
        assertEquals(5, playerOne.getSumEliminationMediumHighScore());
    }

    @Test
    public void higherLowerHigherHighScoreSetting() {
        playerOne.setWordRecollectionEasyHighScore(5);
        assertEquals(5, playerOne.getWordRecollectionEasyHighScore());
        playerOne.setWordRecollectionMediumHighScore(5);
        assertEquals(5, playerOne.getWordRecollectionMediumHighScore());
        playerOne.setWordRecollectionHardHighScore(5);
        assertEquals(5, playerOne.getWordRecollectionHardHighScore());

        playerOne.setSumEliminationEasyHighScore(5);
        assertEquals(5, playerOne.getSumEliminationEasyHighScore());
        playerOne.setSumEliminationHardHighScore(5);
        assertEquals(5, playerOne.getSumEliminationHardHighScore());
        playerOne.setSumEliminationMediumHighScore(5);
        assertEquals(5, playerOne.getSumEliminationMediumHighScore());

        playerOne.setWordRecollectionEasyHighScore(1);
        assertEquals(5, playerOne.getWordRecollectionEasyHighScore());
        playerOne.setWordRecollectionMediumHighScore(1);
        assertEquals(5, playerOne.getWordRecollectionMediumHighScore());
        playerOne.setWordRecollectionHardHighScore(1);
        assertEquals(5, playerOne.getWordRecollectionHardHighScore());

        playerOne.setSumEliminationEasyHighScore(1);
        assertEquals(5, playerOne.getSumEliminationEasyHighScore());
        playerOne.setSumEliminationHardHighScore(1);
        assertEquals(5, playerOne.getSumEliminationHardHighScore());
        playerOne.setSumEliminationMediumHighScore(1);
        assertEquals(5, playerOne.getSumEliminationMediumHighScore());

        playerOne.setWordRecollectionEasyHighScore(10);
        assertEquals(10, playerOne.getWordRecollectionEasyHighScore());
        playerOne.setWordRecollectionMediumHighScore(10);
        assertEquals(10, playerOne.getWordRecollectionMediumHighScore());
        playerOne.setWordRecollectionHardHighScore(10);
        assertEquals(10, playerOne.getWordRecollectionHardHighScore());

        playerOne.setSumEliminationEasyHighScore(10);
        assertEquals(10, playerOne.getSumEliminationEasyHighScore());
        playerOne.setSumEliminationHardHighScore(10);
        assertEquals(10, playerOne.getSumEliminationHardHighScore());
        playerOne.setSumEliminationMediumHighScore(10);
        assertEquals(10, playerOne.getSumEliminationMediumHighScore());

    }

}