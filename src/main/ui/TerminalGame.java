package ui;

import model.*;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;

public class TerminalGame {
    private Scanner scanner;
    private boolean gameContinues;
    private boolean currentGame;
    private boolean gameInProgress;
    private String gameMode;
    private String nextInput;
    private String gameDifficulty;
    private WordRecollection wordRecollectionGame;
    private SumElimination sumEliminationGame;
    private Leaderboards leaderboard;
    private ArrayList<Integer> collectionOfNumbers;
    private boolean highScoreViewing;
    private Player player;
    private String playerAnswer;


    public TerminalGame() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
            IllegalAccessException, FileNotFoundException {
        scanner = new Scanner(System.in);
        gameContinues = true;
        leaderboard = new Leaderboards();
        collectionOfNumbers = new ArrayList<>();
        highScoreViewing = false;

        // EVERYTHING ABOVE HERE
        gameMechanics();

    }

    private void gameMechanics() throws InvocationTargetException, IllegalAccessException, FileNotFoundException {
        System.out.println("What is your player name?");
        player = new Player(scanner.next());
        while (gameContinues) {
            gameMode = "";
            System.out.println("Type Word to play Word Recollection");
            System.out.println("Type Sum to play Sum Elimination");
            System.out.println("Type HS to view high scores");
            nextInput = scanner.next();
            if (nextInput.equals("Word")) {
                gameMode = "Word Recollection ";
            } else if (nextInput.equals("HS")) {
                highScoreViewing = true;
                highScoreViewing();
            } else {
                gameMode = "Sum Elimination ";
            }
            if (!highScoreViewing) {
                difficultySelectingAndContinuing();
            }
        }
    }

    private void difficultySelectingAndContinuing() throws InvocationTargetException, IllegalAccessException,
            FileNotFoundException {
        System.out.println("Type Easy, Medium, or Hard to choose your difficulty");
        nextInput = scanner.next();
        if (nextInput.equals("Easy")) {
            gameDifficulty = "Easy";
        } else if (nextInput.equals("Medium")) {
            gameDifficulty = "Medium";
        } else {
            gameDifficulty = "Hard";
        }
        handleGameStarting();
        System.out.println("Say YES to quit game, anything else to play a new game");
        if (scanner.next().equals("YES")) {
            gameContinues = false;
        } // Otherwise loop continues
    }

    private void highScoreViewing() {
    }

    private void handleGameStarting() throws InvocationTargetException, IllegalAccessException, FileNotFoundException {
        currentGame = true;
        System.out.println(gameMode);
        if (gameMode.contains("Word Recollection")) {
            wordRecollectionMechanics();
        } else {
            sumEliminationMechanics();
        }
    }

    private void sumEliminationMechanics() throws InvocationTargetException, IllegalAccessException {
        System.out.println("You will be faced with a number followed by a list of numbers");
        System.out.println("Write out the list of numbers "
                + "that you need to eliminate, separated by a comma to get the top number");
        while (currentGame) {
            sumEliminationGame = new SumElimination(gameDifficulty);
            gameInProgress = true;
            while (gameInProgress) {
                sumEliminationGameInProgress();
            }
            quittingCurrentGameQuestion();
        }
    }

    private void quittingCurrentGameQuestion() {
        System.out.println("If you would like to quit the game, say QUIT");
        playerAnswer = scanner.next();
        if (playerAnswer.equals("QUIT")) {
            currentGame = false;
        } // else we continue
    }

    private void sumEliminationGameInProgress() throws InvocationTargetException, IllegalAccessException {
        this.collectionOfNumbers = sumEliminationGame.getNextNumbers();
        System.out.println(sumEliminationGame.getTarget());
        System.out.println(sumEliminationGame.listOutTheNumbers(this.collectionOfNumbers));

        playerAnswer = scanner.next();

        if (this.sumEliminationGame.isAnswerCorrect(playerAnswer)) {
            System.out.println("Correct!");
            sumEliminationGame.addOneToScore();
        } else {
            leaderboard.addPlayerToLeaderboard(player, sumEliminationGame);
            System.out.println("Here");
            gameInProgress = false;
        }
    }

    private void wordRecollectionMechanics() throws InvocationTargetException, IllegalAccessException,
            FileNotFoundException {
        System.out.println("You will be given a word, you need to type "
                + "Yes if you have seen the word earlier in the round");
        System.out.println("Otherwise, type No for having not seen it");
        while (currentGame) {
            while (currentGame) {
                wordRecollectionGame = new WordRecollection(gameDifficulty);
                gameInProgress = true;
                while (gameInProgress) {
                    wordRecollectionGameInProgress();
                }
                quittingCurrentGameQuestion();
            }
        }
    }

    private void wordRecollectionGameInProgress() throws InvocationTargetException, IllegalAccessException {
        System.out.println("The next word is: " + this.wordRecollectionGame.getNextWord());
        System.out.println("Have you seen this yet?");

        playerAnswer = scanner.next();
        playerAnswer = playerAnswer.toLowerCase();
        if (playerAnswer.equals("yes") && wordRecollectionGame.isSeenLastWordBefore()
                || playerAnswer.equals("no") && !wordRecollectionGame.isSeenLastWordBefore()) {
            System.out.println("Correct!");
            wordRecollectionGame.addOneToScore();
        } else {
            leaderboard.addPlayerToLeaderboard(player, wordRecollectionGame);
            System.out.println("These are all the words you saw:");
            System.out.println(wordRecollectionGame.getWordsFound().toString());
            gameInProgress = false;
        }
    }

}

