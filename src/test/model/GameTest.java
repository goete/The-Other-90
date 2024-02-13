package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    Game easyWordRecollection;
    Game mediumWordRecollection;
    Game hardWordRecollection;

    Game easySumElimination;
    Game mediumSumElimination;
    Game hardSumElimination;

    @BeforeEach
    public void beforeEach() {
        this.easyWordRecollection = new WordRecollection("Easy");
        this.mediumWordRecollection = new WordRecollection("Medium");
        this.hardWordRecollection = new WordRecollection("Hard");

        this.easySumElimination = new SumElimination("Easy");
        this.mediumSumElimination = new SumElimination("Medium");
        this.hardSumElimination = new SumElimination("Hard");

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

    @Test
    public void timingTest() {
        //The timing won't be exactly equal due to runtimes, however they should be to a certain degree
        assertEquals(Math.round((float) System.currentTimeMillis() / 100), Math.round((float) this.easyWordRecollection.getTimeThatRoundStarted() / 100));
    }

    @Test
    public void roundTimeTests() {
        assertEquals(20, this.easyWordRecollection.getRoundTimeInSeconds());
        assertEquals(15, this.mediumWordRecollection.getRoundTimeInSeconds());
        assertEquals(10, this.hardSumElimination.getRoundTimeInSeconds());
    }

    @Test
    public void resettingTimerTests() {
        long holdingFirst = this.easyWordRecollection.getTimeThatRoundStarted();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.easyWordRecollection.resetTime();
        assertNotEquals(this.easyWordRecollection.getTimeThatRoundStarted(), holdingFirst);
    }

    @Test
    public void roundOverTests() throws InterruptedException {
        this.hardWordRecollection.resetTime();
        assertFalse(this.hardWordRecollection.isRoundOver());
        Thread.sleep(10000);
        assertTrue(this.hardWordRecollection.isRoundOver());
    }
}
