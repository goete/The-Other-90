package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {
    Game easy;
    Game med;
    Game hard;

    @BeforeEach
    public void beforeEach() {
        this.easy = new Game("Easy");
        this.med = new Game("Medium");
        this.hard = new Game("Hard");
    }

    @Test
    public void getDiffTests() {
        assertEquals("Easy", this.easy.getDifficulty());
        assertEquals("Medium", this.med.getDifficulty());
        assertEquals("Hard", this.hard.getDifficulty());
    }

    @Test
    public void scoring() {
        assertEquals(0, this.easy.getCurrentScore());
        this.easy.addOneToScore();
        assertEquals(1, this.easy.getCurrentScore());
        this.easy.addOneToScore();
        assertEquals(2, this.easy.getCurrentScore());
    }

    @Test
    public void boundRandomTests() {
        assertTrue(easy.randomNumberGenerator(0, 10) <= 10 && easy.randomNumberGenerator(0, 10) >= 0);
        assertTrue(easy.randomNumberGenerator(0, 100) <= 100 && easy.randomNumberGenerator(0, 100) >= 0);
        assertTrue(easy.randomNumberGenerator(10, 10) == 10 && easy.randomNumberGenerator(0, 0) == 0);


    }
}
