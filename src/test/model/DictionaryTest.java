package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void sizeTesting() {
        assertEquals(463, this.dictionaryEasy.getSize());
        assertEquals(897, this.dictionaryMedium.getSize());
        assertEquals(1310, this.dictionaryHard.getSize());
    }

    @Test
    public void fileNameTest() {
        assertEquals("data/WordsForEasy", this.dictionaryEasy.getFileName());
        assertEquals("data/WordsForMedium", this.dictionaryMedium.getFileName());
        assertEquals("data/WordsForHard", this.dictionaryHard.getFileName());
    }

    @Test
    public void testingFirstWord() {
        assertEquals("the", this.dictionaryEasy.getDictionary()[0]);
        assertEquals("the", this.dictionaryMedium.getDictionary()[0]);
        assertEquals("the", this.dictionaryHard.getDictionary()[0]);
    }

    @Test
    public void testingLastWord() {
        assertEquals("trademark", this.dictionaryHard.getDictionary()[this.dictionaryHard.getNumOfWords() - 1]);
        assertEquals("together", this.dictionaryMedium.getDictionary()[this.dictionaryMedium.getNumOfWords() - 1]);
        assertEquals("flow", this.dictionaryEasy.getDictionary()[this.dictionaryEasy.getNumOfWords() - 1]);
    }
}
