package ui;

import model.*;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;

public class TerminalGame {
    private final Scanner scanner;
    private boolean gameContinues;
    private boolean currentGame;
    private boolean gameInProgress;
    private String gameMode;
    private String nextInput;
    private String gameDifficulty;
    private WordRecollection wordRecollectionGame;
    private SumElimination sumEliminationGame;
    private final Leaderboards leaderboard;
    private ArrayList<Integer> collectionOfNumbers;
    private ArrayList<Player> topN;
    private boolean highScoreViewing;
    private Player player;
    private String playerAnswer;
    private int numOfPlayersToView;
    private int position;


    public TerminalGame() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
            IllegalAccessException, FileNotFoundException {
        scanner = new Scanner(System.in);
        gameContinues = true;
        leaderboard = new Leaderboards();
        collectionOfNumbers = new ArrayList<>();
        highScoreViewing = false;
        position = 1;
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
            } else {
                gameMode = "Sum Elimination ";
            }
            if (!highScoreViewing) {
                difficultySelectingAndContinuing();
            } else {
                highScoreViewing();
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
        System.out.println("Say YES to close program, anything else to play a new game");
        if (scanner.next().equals("YES")) {
            gameContinues = false;
        } // Otherwise loop continues
    }

    private void highScoreViewing() throws InvocationTargetException, IllegalAccessException {
        while (highScoreViewing) {
            System.out.println("What game would you like to view?");
            System.out.println("Type Word to view Word Recollection");
            System.out.println("Type Sum to view Sum Elimination");
            nextInput = scanner.next();
            if (nextInput.equals("Word")) {
                gameMode = "Word Recollection ";
            } else if (nextInput.equals("HS")) {
                highScoreViewing = true;
            } else {
                gameMode = "Sum Elimination ";
            }
            highScoreDifficultyHandling();
        }
    }

    private void highScoreDifficultyHandling() throws InvocationTargetException, IllegalAccessException {
        System.out.println("Type Easy, Medium or Hard to view that difficulty");
        nextInput = scanner.next();
        if (nextInput.equals("Easy")) {
            gameDifficulty = "Easy";
        } else if (nextInput.equals("Medium")) {
            gameDifficulty = "Medium";
        } else {
            gameDifficulty = "Hard";
        }
        System.out.println("How many of the top players would you like to view?");
        numOfPlayersToView = scanner.nextInt();
        if (gameMode.contains("Word")) {
            handleHighScorePrintingOutWord();
        } else {
            handleHighScorePrintingOutSum();
        }
        System.out.println("Would you like to view high scores again?"
                + " If so, type YES, to return to main menu, say anything else");
        nextInput = scanner.next();
        if (!nextInput.equals("YES")) {
            this.highScoreViewing = false;
        }
    }

    private void handleHighScorePrintingOutSum() throws InvocationTargetException, IllegalAccessException {
        position = 1;
        if (gameDifficulty.equals("Easy")) {
            if (leaderboard.getSumEliminationEasy().isEmpty()) {
                System.out.println("Nobody has played that game yet! Go be the first!");
            } else {
                handleSumEasyHighScore();
            }
        } else if (gameDifficulty.equals("Medium")) {
            if (leaderboard.getSumEliminationMedium().isEmpty()) {
                System.out.println("Nobody has played that game yet! Go be the first!");
            } else {
                handleSumMediumHighScore();
            }
        } else {
            if (leaderboard.getSumEliminationHard().isEmpty()) {
                System.out.println("Nobody has played that game yet! Go be the first!");
            } else {
                handleSumHardHighScore();
            }
        }
    }

    private void handleSumHardHighScore() throws InvocationTargetException, IllegalAccessException {
        topN = leaderboard.getSumEliminationHard().getTopNPlayers(numOfPlayersToView);
        for (Player player : topN) {
            System.out.println(position + ". " + player.getName() + " has a score of "
                    + player.getSumEliminationHardHighScore());
            position++;
        }
    }

    private void handleSumMediumHighScore() throws InvocationTargetException, IllegalAccessException {
        topN = leaderboard.getSumEliminationMedium().getTopNPlayers(numOfPlayersToView);
        for (Player player : topN) {
            System.out.println(position + ". " + player.getName() + " has a score of "
                    + player.getSumEliminationMediumHighScore());
            position++;
        }
    }

    private void handleSumEasyHighScore() throws InvocationTargetException, IllegalAccessException {
        topN = leaderboard.getSumEliminationEasy().getTopNPlayers(numOfPlayersToView);
        for (Player player : topN) {
            System.out.println(position + ". " + player.getName() + " has a score of "
                    + player.getSumEliminationEasyHighScore());
            position++;
        }
    }

    private void handleHighScorePrintingOutWord() throws InvocationTargetException, IllegalAccessException {
        position = 1;
        if (gameDifficulty.equals("Easy")) {
            if (leaderboard.getWordRecollectionEasy().isEmpty()) {
                System.out.println("Nobody has played that game yet! Go be the first!");
            } else {
                handleWordEasyHighScore();
            }
        } else if (gameDifficulty.equals("Medium")) {
            if (leaderboard.getWordRecollectionMedium().isEmpty()) {
                System.out.println("Nobody has played that game yet! Go be the first!");
            } else {
                handleWordMediumHighScore();
            }
        } else {
            if (leaderboard.getWordRecollectionHard().isEmpty()) {
                System.out.println("Nobody has played that game yet! Go be the first!");
            } else {
                handleWordHardHighScore();
            }
        }
    }

    private void handleWordHardHighScore() throws InvocationTargetException, IllegalAccessException {
        topN = leaderboard.getWordRecollectionHard().getTopNPlayers(numOfPlayersToView);
        for (Player player : topN) {
            System.out.println(position + ". " + player.getName() + " has a score of "
                    + player.getWordRecollectionHardHighScore());
            position++;
        }
    }

    private void handleWordMediumHighScore() throws InvocationTargetException, IllegalAccessException {
        topN = leaderboard.getWordRecollectionMedium().getTopNPlayers(numOfPlayersToView);
        for (Player player : topN) {
            System.out.println(position + ". " + player.getName() + " has a score of "
                    + player.getWordRecollectionMediumHighScore());
            position++;
        }
    }

    private void handleWordEasyHighScore() throws InvocationTargetException, IllegalAccessException {
        topN = leaderboard.getWordRecollectionEasy().getTopNPlayers(numOfPlayersToView);
        for (Player player : topN) {
            System.out.println(position + ". " + player.getName() + " has a score of "
                    + player.getWordRecollectionEasyHighScore());
            position++;
        }
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
                + "that you need to eliminate, separated by a comma to get the remaining numbers to add to the target");
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
        System.out.println("Target: " + sumEliminationGame.getTarget());
        System.out.println(sumEliminationGame.listOutTheNumbers(this.collectionOfNumbers));

        playerAnswer = scanner.next();

        if (this.sumEliminationGame.isAnswerCorrect(playerAnswer)) {
            System.out.println("Correct!");
            sumEliminationGame.addOneToScore();
        } else {
            leaderboard.addPlayerToLeaderboard(player, sumEliminationGame);
            System.out.println("Game Over. You scored " + sumEliminationGame.getCurrentScore());
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
            System.out.println("Game Over. You scored " + wordRecollectionGame.getCurrentScore());
            System.out.println("These are all the words you saw:");
            System.out.println(wordRecollectionGame.getWordsFound().toString());
            gameInProgress = false;
        }
    }

}

