package model;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;


public class SumElimination extends Game {

    private final int lowerBound;
    private final int upperBound;
    private final int upperNumberOfGiven;
    private final int lowerNumberOfGiven;
    private final int lowerNumberToKeep;
    private final int upperNumberToKeep;
    private ArrayList<Integer> numbers;
    private int target;

    // REQUIRES: difficulty is one of Easy, Medium, Hard
    // EFFECTS: sets the bounds to the correct values based on difficulty
    public SumElimination(String difficulty) {
        super(difficulty, "Sum Elimination");
        resetGame();
        if (difficulty.equals("Easy")) {
            this.lowerBound = 1;
            this.upperBound = 20;
            this.lowerNumberOfGiven = this.upperNumberOfGiven = 3;
            this.lowerNumberToKeep = 1;
            this.upperNumberToKeep = 2;
        } else if (difficulty.equals("Medium")) {
            this.lowerBound = 1;
            this.upperBound = 100;
            this.lowerNumberOfGiven = 4;
            this.upperNumberOfGiven = 5;
            this.lowerNumberToKeep = 1;
            this.upperNumberToKeep = 4;
        } else {
            this.lowerBound = -100;
            this.upperBound = 100;
            this.lowerNumberOfGiven = this.upperNumberToKeep = 4;
            this.upperNumberOfGiven = 6;
            this.lowerNumberToKeep = 0;
        }
    }

    // MODIFIES: this
    // EFFECTS: resets the values to the starting values
    public void resetGame() {
        this.numbers = new ArrayList<>();
        this.target = 0;
        super.resetTime();
    }

    // MODIFIES: this
    // EFFECTS: Returns the list of the numbers for the round shuffled and resets the time for round start
    public ArrayList<Integer> getNextNumbers() {
        //Min + (int)(Math.random() * ((Max - Min) + 1)) gives number [Min, Max]
        this.numbers = new ArrayList<>();
        for (int i = 0; i < super.randomNumberGenerator(this.lowerNumberOfGiven, this.upperNumberOfGiven); i++) {
            this.numbers.add(super.randomNumberGenerator(this.lowerBound, this.upperBound));

        }
        this.target = 0;
        for (int n = 0; n < super.randomNumberGenerator(this.lowerNumberToKeep, this.upperNumberToKeep); n++) {
            this.target += this.numbers.get(n);
        }
        Collections.shuffle(numbers);
        super.resetTime();
        return this.numbers;
    }


    public int getTarget() {
        return this.target;
    }


    // MODIFIES: this
    // EFFECTS: sets the target
    public void setTarget(int target) {
        //for testing purposes only
        this.target = target;
    }

    // EFFECTS: returns if the given number matches the target
    public boolean isSumCorrect(int answerSum) {
        return (this.target == answerSum);
    }

    // REQUIRES: Collection.size() >= 1
    // EFFECTS: returns the
    public String listOutTheNumbers(ArrayList<Integer> collection) {
        StringBuilder hold = new StringBuilder();
        for (int n : collection) {
            if (hold.toString().isEmpty()) {
                hold.append(n);
            } else {
                hold.append(",").append(n);
            }
        }
        return hold.toString();
    }

    // REQUIRES: String with numbers separated by comma (has guard against however)
    // EFFECTS: returns whether the answer was correct
    public boolean isAnswerCorrect(String answer) {
        if (answer.matches("[,|\\d|-]+")) {

            return this.target == addAnswer(answer.split(","));
        } else {
            return false;
        }
    }

    // REQUIRES: An array list where each number is contained in this.numbers
    // EFFECTS: returns whether the answer was correct
    public boolean isAnswerCorrect(ArrayList<Integer> answer) {
        for (Integer i : answer) {
            if (this.numbers.contains(i)) {
                this.numbers.remove(i);
            }
        }
        int hold = 0;
        for (int n : this.numbers) {
            hold += n;
        }
        return hold == this.target;
    }

    // REQUIRES: string array where each position is an integer
    // EFFECTS: returns the sum of the array terms
    private int addAnswer(String[] splitByComma) {
        for (String s : splitByComma) {
            if (this.numbers.contains(Integer.parseInt(s))) {
                this.numbers.remove(Integer.valueOf(Integer.parseInt(s)));
            }
        }
        int hold = 0;
        for (int n : this.numbers) {
            hold += n;
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

    public int getLowerNumberToKeep() {
        return lowerNumberToKeep;
    }

    public int getUpperNumberToKeep() {
        return upperNumberToKeep;
    }

    public ArrayList<Integer> getNumbers() {
        return numbers;
    }
}
