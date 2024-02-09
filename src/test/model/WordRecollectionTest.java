package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordRecollectionTest {
    WordRecollection gameEasy;
    WordRecollection gameMedium;
    WordRecollection gameHard;


    @BeforeEach
    public void beforeEach() {
        this.gameEasy = new WordRecollection("Easy");
        this.gameHard = new WordRecollection("Hard");
        this.gameMedium = new WordRecollection("Medium");
    }

    @Test
    public void testDifficulty() {
        assertEquals("Easy", this.gameEasy.getDifficulty());
        assertEquals("Medium", this.gameMedium.getDifficulty());
        assertEquals("Hard", this.gameHard.getDifficulty());
    }

    @Test
    public void wordsAreActuallyReal() {
        Dictionary dictionaryEasy = new Dictionary("Easy");
        Dictionary dictionaryMedium = new Dictionary("Medium");
        Dictionary dictionaryHard = new Dictionary("Hard");

        assertTrue(dictionaryEasy.contains(gameEasy.getNextWord()));
        assertTrue(dictionaryMedium.contains(gameMedium.getNextWord()));
        assertTrue(dictionaryHard.contains(gameHard.getNextWord()));

    }

    @Test
    public void keepsTheWords() {
        assertTrue(gameEasy.getWordsFound().contains(gameEasy.getNextWord()));
        assertTrue(gameEasy.getWordsFound().contains(gameEasy.getNextWord()));
        assertTrue(gameEasy.getWordsFound().contains(gameEasy.getNextWord()));
        assertEquals(3, gameEasy.getWordsFound().size());
    }

    @Test
    public void oneOfTwoPlaces() {
        assertTrue(gameEasy.getWordsFound().contains(gameEasy.getNextWord()));
        assertTrue(gameEasy.getWordsFound().contains(gameEasy.getNextWord()));
        assertTrue(gameEasy.getWordsFound().contains(gameEasy.getNextWord()));
        assertEquals(3, gameEasy.getWordsFound().size());
        String hold = gameEasy.getNextWord();
        assertTrue(gameEasy.getWordsFound().contains(hold) | gameEasy.getWordsFound().contains(hold));
        hold = gameEasy.getNextWord();
        assertTrue(gameEasy.getWordsFound().contains(hold) | gameEasy.getWordsFound().contains(hold));
        hold = gameEasy.getNextWord();
        assertTrue(gameEasy.getWordsFound().contains(hold) | gameEasy.getWordsFound().contains(hold));

    }
}
