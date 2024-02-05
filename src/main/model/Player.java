package model;

import java.util.ArrayList;

public class Player {
    private String name;
    private int sumEliminationEasyHighScore;
    private int sumEliminationMediumHighScore;
    private int sumEliminationHardHighScore;
    private int wordRecollectionEasyHighScore;
    private int wordRecollectionMediumHighScore;
    private int wordRecollectionHardHighScore;

    public Player(String name) {
        this.name = name;
        this.sumEliminationEasyHighScore = 0;
        this.sumEliminationMediumHighScore = 0;
        this.sumEliminationHardHighScore = 0;
        this.wordRecollectionHardHighScore = 0;
        this.wordRecollectionMediumHighScore = 0;
        this.wordRecollectionEasyHighScore = 0;
    }


    public String getName() {
        return name;
    }

    public int getSumEliminationEasyHighScore() {
        return sumEliminationEasyHighScore;
    }

    public void setSumEliminationEasyHighScore(int sumEliminationEasyHighScore) {
        if (this.sumEliminationEasyHighScore < sumEliminationEasyHighScore) {
            this.sumEliminationEasyHighScore = sumEliminationEasyHighScore;
        }
    }

    public int getSumEliminationMediumHighScore() {
        return sumEliminationMediumHighScore;
    }

    public void setSumEliminationMediumHighScore(int sumEliminationMediumHighScore) {
        if (this.sumEliminationMediumHighScore < sumEliminationMediumHighScore) {
            this.sumEliminationMediumHighScore = sumEliminationMediumHighScore;
        }
    }

    public int getSumEliminationHardHighScore() {
        return sumEliminationHardHighScore;
    }

    public void setSumEliminationHardHighScore(int sumEliminationHardHighScore) {
        if (this.sumEliminationHardHighScore < sumEliminationHardHighScore) {
            this.sumEliminationHardHighScore = sumEliminationHardHighScore;
        }
    }

    public int getWordRecollectionEasyHighScore() {
        return wordRecollectionEasyHighScore;
    }

    public void setWordRecollectionEasyHighScore(int wordRecollectionEasyHighScore) {
        if (this.wordRecollectionEasyHighScore < wordRecollectionEasyHighScore) {
            this.wordRecollectionEasyHighScore = wordRecollectionEasyHighScore;
        }
    }

    public int getWordRecollectionMediumHighScore() {
        return wordRecollectionMediumHighScore;
    }

    public void setWordRecollectionMediumHighScore(int wordRecollectionMediumHighScore) {
        if (this.wordRecollectionMediumHighScore < wordRecollectionMediumHighScore) {
            this.wordRecollectionMediumHighScore = wordRecollectionMediumHighScore;
        }
    }

    public int getWordRecollectionHardHighScore() {
        return wordRecollectionHardHighScore;
    }

    public void setWordRecollectionHardHighScore(int wordRecollectionHardHighScore) {
        if (this.wordRecollectionHardHighScore < wordRecollectionHardHighScore) {
            this.wordRecollectionHardHighScore = wordRecollectionHardHighScore;
        }
    }

    public int getSumOfWordRecollection() {
        return this.wordRecollectionEasyHighScore
                + this.wordRecollectionHardHighScore
                + this.wordRecollectionMediumHighScore;
    }

    public int getSumOfSumElimination() {
        return this.sumEliminationEasyHighScore
                + this.sumEliminationMediumHighScore
                + this.sumEliminationHardHighScore;
    }

    public int getSumOfTotalHighScores() {
        return this.getSumOfWordRecollection() + this.getSumOfSumElimination();
    }


}
