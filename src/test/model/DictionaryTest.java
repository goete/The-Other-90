package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DictionaryTest {
    Dictionary dictionaryEasy;
    Dictionary dictionaryMedium;
    Dictionary dictionaryHard;
    Dictionary dictionaryEasy2;
    Dictionary dictionaryMedium2;
    Dictionary dictionaryHard2;

    @BeforeEach
    public void beforeEach() {
        this.dictionaryEasy = new Dictionary("Easy");
        this.dictionaryMedium = new Dictionary("Medium");
        this.dictionaryHard = new Dictionary("Hard");

        this.dictionaryEasy2 = new Dictionary("Easy");
        this.dictionaryMedium2 = new Dictionary("Medium");
        this.dictionaryHard2 = new Dictionary("Hard");
    }

    @Test
    public void fileNameTest() {
        assertEquals("data/WordsForEasy", this.dictionaryEasy.getFileName());
        assertEquals("data/WordsForMedium", this.dictionaryMedium.getFileName());
        assertEquals("data/WordsForHard", this.dictionaryHard.getFileName());
    }

    @Test()
    public void changingSize() {
        int originalSize = this.dictionaryEasy.getNumOfWords();
    }

    @Test
    public void testingFirstWord() {
        assertEquals("the", this.dictionaryEasy.getDictionary().get(0));
        assertEquals("the", this.dictionaryMedium.getDictionary().get(0));
        assertEquals("the", this.dictionaryHard.getDictionary().get(0));
    }

    @Test
    public void testingLastWord() {
        assertEquals("trademark", this.dictionaryHard.getDictionary().get(this.dictionaryHard.getNumOfWords() - 1));
        assertEquals("together", this.dictionaryMedium.getDictionary().get(this.dictionaryMedium.getNumOfWords() - 1));
        assertEquals("flow", this.dictionaryEasy.getDictionary().get(this.dictionaryEasy.getNumOfWords() - 1));
    }

    @Test
    public void emptyTest() {
        assertFalse(this.dictionaryEasy.isEmpty());
        while (this.dictionaryEasy.getNumOfWords() > 0) {
            this.dictionaryEasy.getRandomAndRemove();
        }
        assertTrue(this.dictionaryEasy.isEmpty());
    }

    @Test
    public void containingTesting() {
        assertTrue(this.dictionaryEasy.contains("the"));
        assertTrue(this.dictionaryMedium.contains("the"));
        assertTrue(this.dictionaryHard.contains("the"));

        assertFalse(this.dictionaryEasy.contains("i am not in there good try though"));
        assertFalse(this.dictionaryMedium.contains("i am not in there good try though"));
        assertFalse(this.dictionaryHard.contains("i am not in there good try though"));

    }

    @Test
    public void gettingWordAndChecking() {
        assertFalse(this.dictionaryEasy.contains(this.dictionaryEasy.getRandomAndRemove()));
        assertFalse(this.dictionaryMedium.contains(this.dictionaryMedium.getRandomAndRemove()));
        assertFalse(this.dictionaryHard.contains(this.dictionaryHard.getRandomAndRemove()));
    }

    @Test
    public void testingGettingWords() {
        assertFalse(this.dictionaryEasy.contains(this.dictionaryEasy.getRandomAndRemove()));
        assertTrue(this.dictionaryEasy2.contains(this.dictionaryEasy.getRandomAndRemove()));
        assertFalse(this.dictionaryMedium.contains(this.dictionaryMedium.getRandomAndRemove()));
        assertTrue(this.dictionaryMedium2.contains(this.dictionaryMedium.getRandomAndRemove()));
        assertFalse(this.dictionaryHard.contains(this.dictionaryHard.getRandomAndRemove()));
        assertTrue(this.dictionaryHard2.contains(this.dictionaryHard.getRandomAndRemove()));
    }

    @Test
    public void boundRandomTests() {
        assertTrue(this.dictionaryEasy.randomNumberGenerator() >= 0);
        assertTrue(this.dictionaryEasy.randomNumberGenerator() < this.dictionaryEasy.getNumOfWords());

        assertTrue(this.dictionaryMedium.randomNumberGenerator() >= 0);
        assertTrue(this.dictionaryMedium.randomNumberGenerator() < this.dictionaryMedium.getNumOfWords());

        assertTrue(this.dictionaryHard.randomNumberGenerator() >= 0);
        assertTrue(this.dictionaryHard.randomNumberGenerator() < this.dictionaryHard.getNumOfWords());

    }


}
