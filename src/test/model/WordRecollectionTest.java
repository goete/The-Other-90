package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class WordRecollectionTest {
    WordRecollection gameEasy;
    WordRecollection gameMedium;
    WordRecollection gameHard;


    @BeforeEach
    public void beforeEach() throws FileNotFoundException {
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
    public void wordsAreActuallyReal() throws FileNotFoundException {
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

    @Test
    public void testingLastWord() {
        ArrayList<String> holding = new ArrayList<>();
        String store;
        holding.add(gameEasy.getNextWord());
        assertFalse(gameEasy.isSeenLastWordBefore());
        holding.add(gameEasy.getNextWord());
        assertFalse(gameEasy.isSeenLastWordBefore());
        holding.add(gameEasy.getNextWord());
        assertFalse(gameEasy.isSeenLastWordBefore());
        // since random, test a lot of times to make sure it behaves
        for (int i = 0; i < 100; i++) {
            store = gameEasy.getNextWord();
            assertEquals(holding.contains(store), gameEasy.isSeenLastWordBefore());
            holding.add(store);
        }
        assertFalse(gameEasy.getWordsFound().isEmpty());
    }

    @Test
    public void resettingGame() throws FileNotFoundException {
        ArrayList<String> holding = new ArrayList<>();
        String store;

        // emptying dictionary
        for (int i = 0; i < 100; i++) {
            store = gameEasy.getNextWord();
            assertEquals(holding.contains(store), gameEasy.isSeenLastWordBefore());
            holding.add(store);
        }
        assertFalse(gameEasy.getWordsFound().isEmpty());
        int size = this.gameEasy.getDictionary().getNumOfWords();
        gameEasy.resetGame();
        assertTrue(gameEasy.getWordsFound().isEmpty());
        assertNotEquals(this.gameEasy.getDictionary().getNumOfWords(), size);


    }
}
