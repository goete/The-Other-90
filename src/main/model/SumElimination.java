package model;


import java.util.ArrayList;
import java.lang.Math;
import java.util.Collections;


public class SumElimination extends Game {

    private final int lowerBound;
    private final int upperBound;
    private final int upperNumberOfGiven;
    private final int lowerNumberOfGiven;
    private final int lowerNumberToGetRidOf;
    private final int upperNumberToGetRidOf;
    private ArrayList<Integer> numbers;
    private int target;

    // REQUIRES: difficulty is one of Easy, Medium, Hard
    public SumElimination(String difficulty) {
        super(difficulty);
        this.numbers = new ArrayList<>();
        this.target = 0;
        if (difficulty.equals("Easy")) {
            this.lowerBound = 1;
            this.upperBound = 100;
            this.lowerNumberOfGiven = 3;
            this.upperNumberOfGiven = 3;
            this.lowerNumberToGetRidOf = 1;
            this.upperNumberToGetRidOf = 1;
        } else if (difficulty.equals("Medium")) {
            this.lowerBound = 1;
            this.upperBound = 1000;
            this.lowerNumberOfGiven = 4;
            this.upperNumberOfGiven = 5;
            this.lowerNumberToGetRidOf = 1;
            this.upperNumberToGetRidOf = 2;
        } else {
            this.lowerBound = -1000;
            this.upperBound = 1000;
            this.lowerNumberOfGiven = 4;
            this.upperNumberOfGiven = 6;
            this.lowerNumberToGetRidOf = 0;
            this.upperNumberToGetRidOf = 4;
        }
    }


    // MODIFIES: this
    // EFFECTS: Returns the list of the numbers for the round shuffled
    public ArrayList<Integer> getNextNumbers() {
        //Min + (int)(Math.random() * ((Max - Min) + 1)) gives number [Min, Max]
        this.numbers = new ArrayList<>();
        for (int i = 0; i < this.randomNumberGenerator(this.lowerNumberOfGiven, this.upperNumberOfGiven); i++) {
            this.numbers.add(this.randomNumberGenerator(this.lowerBound, this.upperBound));

        }
        for (int n = 0; n < this.randomNumberGenerator(this.lowerNumberToGetRidOf, this.upperNumberToGetRidOf); n++) {
            this.target += this.numbers.get(n);
        }
        Collections.shuffle(numbers);
        return this.numbers;
    }

    private int randomNumberGenerator(int min, int max) {
        //Min + (int)(Math.random() * ((Max - Min) + 1)) gives number [Min, Max]
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    public int getTarget() {
        return this.target;
    }


    public void setTarget(int target) {
        //for testing purposes only
        this.target = target;
    }

    public boolean isSumCorrect(int answerSum) {
        return (this.target == answerSum);
    }

    // REQUIRES: Collection.size() >= 1
    public String listOutTheNumbers(ArrayList<Integer> collection) {
        String hold = null;
        for (int n : collection) {
            if (hold == null) {
                hold = "" + n;
            } else {
                hold += ", " + n;
            }
        }
        return hold;
    }

    // REQUIRES: String with numbers separated by space(s) (has guard against however)
    // EFFECTS: returns whether the answer was correct
    public boolean isAnswerCorrect(String answer) {
        if (answer.matches("[\\d | \\s]+")) {
            return this.target == addAnswer(answer.split("\\s+"));
        } else {
            return false;
        }
    }

    // REQUIRES: string array where each position is a integer
    // EFFECTS: returns the sum of the array terms
    private int addAnswer(String[] splitBySpace) {
        int hold = 0;
        for (String s : splitBySpace) {
            hold += Integer.parseInt(s);
        }
        return hold;
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public int getUpperNumberOfGiven() {
        return upperNumberOfGiven;
    }

    public int getLowerNumberOfGiven() {
        return lowerNumberOfGiven;
    }

    public int getLowerNumberToGetRidOf() {
        return lowerNumberToGetRidOf;
    }

    public int getUpperNumberToGetRidOf() {
        return upperNumberToGetRidOf;
    }
}
