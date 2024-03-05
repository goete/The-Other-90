package ui;

import model.*;
import persistence.JsonReaderLeaderboards;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
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
    private Leaderboards leaderboard;
    private ArrayList<Integer> collectionOfNumbers;
    private ArrayList<Player> topN;
    private boolean highScoreViewing;
    private Player player;
    private String playerAnswer;
    private int numOfPlayersToView;
    private int position;
    private final JsonReaderLeaderboards jsonReaderLeaderboards;
    private final JsonWriter jsonWriter;
    private String name;


    public TerminalGame() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
            IllegalAccessException, FileNotFoundException {
        scanner = new Scanner(System.in);
        gameContinues = true;
        leaderboard = new Leaderboards();
        collectionOfNumbers = new ArrayList<>();
        highScoreViewing = false;
        position = 1;
        jsonWriter = new JsonWriter("./data/Players.json");
        jsonReaderLeaderboards = new JsonReaderLeaderboards("./data/Players.json");

        // EVERYTHING ABOVE HERE
        gameMechanics();
    }

    // MODIFIES: this
    // EFFECTS: begins the game
    private void gameMechanics() throws InvocationTargetException, IllegalAccessException, FileNotFoundException {
        beginningGameLines();
        name = scanner.next();
        this.player = new Player(name);
        while (gameContinues) {
            printOutputs();
            if (nextInput.equals("word") || nextInput.equals("sum") || nextInput.equals("hs")) {
                dealWithInput();
            } else if (nextInput.equals("load")) {
                loadLeaderboards();
                continue;
            } else if (nextInput.equals("save")) {
                saveLeaderboards();
                continue;
            } else {
                System.out.println("Input an option");
                continue;
            }
            if (!highScoreViewing) {
                difficultySelectingAndContinuing();
            } else {
                highScoreViewing();
            }
        }
    }

    private void beginningGameLines() {
        System.out.println("What is your player name?");
        System.out.println("If you already have an account, type the exact name (case-sensitive) "
                + "to log in once loading saved progress");
    }

    private void dealWithInput() {
        switch (nextInput) {
            case "word":
                gameMode = "Word Recollection ";
                break;
            case "hs":
                highScoreViewing = true;
                break;
            case "sum":
                gameMode = "Sum Elimination ";
                break;
        }
    }

    private void loadLeaderboards() {
        try {
            this.leaderboard = this.jsonReaderLeaderboards.readLeaderboards();
            System.out.println("Loading Success!");
            potentiallyLoggingIn();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void potentiallyLoggingIn() {
        for (Player p : this.leaderboard.getAllPlayers()) {
            if (p.getName().equals(name)) {
                this.player = p;
                break;
            }
        }
        System.out.println("Successfully logged you in as " + this.player.getName());
    }

    private void saveLeaderboards() {
        try {
            this.jsonWriter.open();
            this.jsonWriter.write(this.leaderboard);
            this.jsonWriter.close();
            System.out.println("Saving Success!");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void printOutputs() {
        this.gameMode = "";
        System.out.println("Type Word to play Word Recollection");
        System.out.println("Type Sum to play Sum Elimination");
        System.out.println("Type HS to view high scores");
        System.out.println("Type Load to load in saved high scores");
        System.out.println("Type Save to save current high scores");
        this.nextInput = scanner.next().toLowerCase();
    }

    //MODIFIES: this
    // EFFECTS: gets the user to select game mode and continues from there
    private void difficultySelectingAndContinuing() throws InvocationTargetException, IllegalAccessException,
            FileNotFoundException {
        System.out.println("Type Easy, Medium, or Hard to choose your difficulty");
        nextInput = scanner.next().toLowerCase();
        if (nextInput.equals("easy")) {
            gameDifficulty = "Easy";
        } else if (nextInput.equals("medium")) {
            gameDifficulty = "Medium";
        } else {
            gameDifficulty = "Hard";
        }
        handleGameStarting();
        System.out.println("Say QUIT to close program or continue to keep playing");
        if (scanner.next().equals("QUIT")) {
            gameContinues = false;
        } // Otherwise loop continues
    }

    // MODIFIES: this
    // EFFECTS: Asks user what high score they want to see and helps produce it
    private void highScoreViewing() throws InvocationTargetException, IllegalAccessException {
        while (highScoreViewing) {
            System.out.println("What game would you like to view?");
            System.out.println("Type Word to view Word Recollection");
            System.out.println("Type Sum to view Sum Elimination");
            nextInput = scanner.next().toUpperCase();
            if (nextInput.equals("WORD")) {
                gameMode = "Word Recollection ";
            } else {
                gameMode = "Sum Elimination ";
            }
            highScoreDifficultyHandling();
        }
    }

    // MODIFIES: this
    // EFFECTS: gets the users desired difficulty and number of players
    //          then calls appropriate next steps
    private void highScoreDifficultyHandling() throws InvocationTargetException, IllegalAccessException {
        System.out.println("Type Easy, Medium or Hard to view that difficulty");
        nextInput = scanner.next().toUpperCase();
        if (nextInput.equals("EASY")) {
            gameDifficulty = "Easy";
        } else if (nextInput.equals("MEDIUM")) {
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
        System.out.println("To view high scores again, say again. Otherwise say return to return to main menu");
        nextInput = scanner.next().toLowerCase();
        if (!nextInput.equals("again")) {
            this.highScoreViewing = false;
        }
    }

    // MODIFIES: this
    // EFFECTS: call appropriate method for each Sum high score
    //          prints out if no users have played that game before
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

    // MODIFIES: this
    // EFFECT: Prints out the top requested players for Sum Elimination Hard
    private void handleSumHardHighScore() throws InvocationTargetException, IllegalAccessException {
        topN = leaderboard.getSumEliminationHard().getTopNPlayers(numOfPlayersToView);
        for (Player player : topN) {
            System.out.println(position + ". " + player.getName() + " has a score of "
                    + player.getSumEliminationHardHighScore());
            position++;
        }
    }

    // MODIFIES: this
    // EFFECT: Prints out the top requested players for Sum Elimination Medium
    private void handleSumMediumHighScore() throws InvocationTargetException, IllegalAccessException {
        topN = leaderboard.getSumEliminationMedium().getTopNPlayers(numOfPlayersToView);
        for (Player player : topN) {
            System.out.println(position + ". " + player.getName() + " has a score of "
                    + player.getSumEliminationMediumHighScore());
            position++;
        }
    }

    // MODIFIES: this
    // EFFECT: Prints out the top requested players for Sum Elimination Easy
    private void handleSumEasyHighScore() throws InvocationTargetException, IllegalAccessException {
        topN = leaderboard.getSumEliminationEasy().getTopNPlayers(numOfPlayersToView);
        for (Player player : topN) {
            System.out.println(position + ". " + player.getName() + " has a score of "
                    + player.getSumEliminationEasyHighScore());
            position++;
        }
    }

    // MODIFIES: this
    // EFFECTS: call appropriate method for each Word high score
    //          prints out if no users have played that game before
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

    // MODIFIES: this
    // EFFECT: Prints out the top requested players for Word Recollection Hard
    private void handleWordHardHighScore() throws InvocationTargetException, IllegalAccessException {
        topN = leaderboard.getWordRecollectionHard().getTopNPlayers(numOfPlayersToView);
        for (Player player : topN) {
            System.out.println(position + ". " + player.getName() + " has a score of "
                    + player.getWordRecollectionHardHighScore());
            position++;
        }
    }

    // MODIFIES: this
    // EFFECT: Prints out the top requested players for Word Recollection Medium
    private void handleWordMediumHighScore() throws InvocationTargetException, IllegalAccessException {
        topN = leaderboard.getWordRecollectionMedium().getTopNPlayers(numOfPlayersToView);
        for (Player player : topN) {
            System.out.println(position + ". " + player.getName() + " has a score of "
                    + player.getWordRecollectionMediumHighScore());
            position++;
        }
    }

    // MODIFIES: this
    // EFFECT: Prints out the top requested players for Word Recollection Easy
    private void handleWordEasyHighScore() throws InvocationTargetException, IllegalAccessException {
        topN = leaderboard.getWordRecollectionEasy().getTopNPlayers(numOfPlayersToView);
        for (Player player : topN) {
            System.out.println(position + ". " + player.getName() + " has a score of "
                    + player.getWordRecollectionEasyHighScore());
            position++;
        }
    }

    // MODIFIES: this
    // EFFECTS: calls the requested game's starting method
    private void handleGameStarting() throws InvocationTargetException, IllegalAccessException, FileNotFoundException {
        currentGame = true;
        System.out.println(gameMode);
        if (gameMode.contains("Word Recollection")) {
            wordRecollectionMechanics();
        } else {
            sumEliminationMechanics();
        }
    }

    // MODIFIES: this
    // EFFECTS: starts a sum elimination game
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

    // MODIFIES: this
    // EFFECTS: determines if game is being quit or not
    private void quittingCurrentGameQuestion() {
        System.out.println("If you would like to quit the game, say QUIT");
        playerAnswer = scanner.next();
        if (playerAnswer.equals("QUIT")) {
            currentGame = false;
        } // else we continue
    }

    // MODIFIES: this
    // EFFECTS: controls the game play of Sum Elimination
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

    // MODIFIES: this
    // EFFECTS: starts a Word Recollection game
    private void wordRecollectionMechanics() throws InvocationTargetException, IllegalAccessException,
            FileNotFoundException {
        System.out.println("You will be given a word, you need to type "
                + "Yes if you have seen the word earlier in the round");
        System.out.println("Otherwise, type No for having not seen it");
        while (currentGame) {
            wordRecollectionGame = new WordRecollection(gameDifficulty);
            gameInProgress = true;
            while (gameInProgress) {
                wordRecollectionGameInProgress();
            }
            quittingCurrentGameQuestion();

        }
    }

    // MODIFIES: this
    // EFFECTS: controls the game play of Word Recollection
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

