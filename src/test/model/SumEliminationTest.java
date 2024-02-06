package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SumEliminationTest {
    SumElimination sumEasy;
    SumElimination sumMed;
    SumElimination sumHard;

    @BeforeEach
    public void beforeEach() {
        sumEasy = new SumElimination("Easy");
        sumMed = new SumElimination("Medium");
        sumHard = new SumElimination("Hard");
    }

    @Test
    public void setUpEasy() {
        assertEquals("Easy", this.sumEasy.getDifficulty());
        assertEquals(1, this.sumEasy.getLowerBound());
        assertEquals(100, this.sumEasy.getUpperBound());
        assertEquals(3, this.sumEasy.getLowerNumberOfGiven());
        assertEquals(3, this.sumEasy.getUpperNumberOfGiven());
        assertEquals(1, this.sumEasy.getLowerNumberToGetRidOf());
        assertEquals(1, this.sumEasy.getUpperNumberToGetRidOf());
        assertEquals(0, this.sumEasy.getTarget());

    }

    @Test
    public void setUpMedium() {
        assertEquals("Medium", this.sumMed.getDifficulty());
        assertEquals(1, this.sumMed.getLowerBound());
        assertEquals(1000, this.sumMed.getUpperBound());
        assertEquals(4, this.sumMed.getLowerNumberOfGiven());
        assertEquals(5, this.sumMed.getUpperNumberOfGiven());
        assertEquals(1, this.sumMed.getLowerNumberToGetRidOf());
        assertEquals(2, this.sumMed.getUpperNumberToGetRidOf());
        assertEquals(0, this.sumMed.getTarget());

    }

    @Test
    public void setUpHard() {
        assertEquals("Hard", this.sumHard.getDifficulty());
        assertEquals(-1000, this.sumHard.getLowerBound());
        assertEquals(1000, this.sumHard.getUpperBound());
        assertEquals(4, this.sumHard.getLowerNumberOfGiven());
        assertEquals(6, this.sumHard.getUpperNumberOfGiven());
        assertEquals(0, this.sumHard.getLowerNumberToGetRidOf());
        assertEquals(4, this.sumHard.getUpperNumberToGetRidOf());
        assertEquals(0, this.sumHard.getTarget());

    }

    @Test
    public void correctSum() {
        sumEasy.setTarget(5);
        assertEquals(true, sumEasy.isSumCorrect(5));
        assertEquals(false, sumEasy.isSumCorrect(15));
    }

    @Test
    public void listingNumbersTest() {
        ArrayList<Integer> helper = new ArrayList<>();
        helper.add(1);
        helper.add(2);
        helper.add(3);
        helper.add(43);
        helper.add(4);
        helper.add(5);
        assertEquals("1, 2, 3, 43, 4, 5", this.sumEasy.listOutTheNumbers(helper));
    }

    @Test
    public void correctAnswerTest() {
        this.sumEasy.setTarget(10);
        assertTrue(this.sumEasy.isAnswerCorrect("1 4 5"));
        assertTrue(this.sumEasy.isAnswerCorrect("1 4 3 2"));
        assertFalse(this.sumEasy.isAnswerCorrect("3  4"));
    }


}
