package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {
    Game easyWordRecollection;
    Game mediumWordRecollection;
    Game hardWordRecollection;

    Game easySumElimination;
    Game mediumSumElimination;
    Game hardSumElimination;

    @BeforeEach
    public void beforeEach() {
        this.easyWordRecollection = new Game("Easy", "Word Recollection");
        this.mediumWordRecollection = new Game("Medium", "Word Recollection");
        this.hardWordRecollection = new Game("Hard", "Word Recollection");

        this.easySumElimination = new Game("Easy", "Sum Elimination");
        this.mediumSumElimination = new Game("Medium", "Sum Elimination");
        this.hardSumElimination = new Game("Hard", "Sum Elimination");

    }

    @Test
    public void getDiffTests() {
        assertEquals("Easy", this.easyWordRecollection.getDifficulty());
        assertEquals("Medium", this.mediumWordRecollection.getDifficulty());
        assertEquals("Hard", this.hardWordRecollection.getDifficulty());
    }

    @Test
    public void scoring() {
        assertEquals(0, this.easyWordRecollection.getCurrentScore());
        this.easyWordRecollection.addOneToScore();
        assertEquals(1, this.easyWordRecollection.getCurrentScore());
        this.easyWordRecollection.addOneToScore();
        assertEquals(2, this.easyWordRecollection.getCurrentScore());
    }

    @Test
    public void boundRandomTests() {
        assertTrue(easyWordRecollection.randomNumberGenerator(0, 10) <= 10 && easyWordRecollection.randomNumberGenerator(0, 10) >= 0);
        assertTrue(easyWordRecollection.randomNumberGenerator(0, 100) <= 100 && easyWordRecollection.randomNumberGenerator(0, 100) >= 0);
        assertTrue(easyWordRecollection.randomNumberGenerator(10, 10) == 10 && easyWordRecollection.randomNumberGenerator(0, 0) == 0);

    }

    @Test
    public void nameTest() {
        assertEquals("Word Recollection", this.easyWordRecollection.getName());
        assertEquals("Word Recollection", this.mediumWordRecollection.getName());
        assertEquals("Word Recollection", this.hardWordRecollection.getName());

        assertEquals("Sum Elimination", this.hardSumElimination.getName());
        assertEquals("Sum Elimination", this.mediumSumElimination.getName());
        assertEquals("Sum Elimination", this.easySumElimination.getName());

    }
}
