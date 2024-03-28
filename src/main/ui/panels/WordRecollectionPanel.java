package ui.panels;

import model.WordRecollection;
import ui.GameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

// manages the Word Recollection game and respective panel
public class WordRecollectionPanel extends JPanel implements ActionListener {
    private final int height;
    private final int width;
    private JButton seenButton;
    private JButton haveNotSeenButton;
    private WordRecollection wordRecollectionGame;
    private String currentDifficulty;
    private boolean gettingDifficulty;
    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;
    private JButton backButton;
    private GameGUI gameGUI;
    private String displayWord;

    public WordRecollectionPanel(int width, int height, GameGUI game) {
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(null);
        this.height = height;
        this.width = width;
        this.setUpButtonsGame();
        this.setUpButtonsDifficulty();
        this.gettingDifficulty = true;
        this.gameGUI = game;
        this.displayWord = null;
    }

    // MODIFIES: this
    // EFFECTS: sets up all the buttons for choosing which difficulty is desired
    private void setUpButtonsDifficulty() {
        this.easyButton = new JButton("Easy");
        this.mediumButton = new JButton("Medium");
        this.hardButton = new JButton("Hard");

        this.easyButton.addActionListener(this);
        this.mediumButton.addActionListener(this);
        this.hardButton.addActionListener(this);

        this.easyButton.setSize(150, 150);
        this.mediumButton.setSize(150, 150);
        this.hardButton.setSize(150, 150);

        this.easyButton.setLocation(50, 250);
        this.mediumButton.setLocation(200 + 25, 250);
        this.hardButton.setLocation(350 + 25 * 2, 250);

        this.backButton = new JButton("⮪");
        this.backButton.addActionListener(this);
        this.backButton.setSize(50, 50);
        this.backButton.setLocation(0, 0);
    }

    // MODIFIES: this
    // EFFECTS: sets up the buttons for once the game starts running
    private void setUpButtonsGame() {
        this.seenButton = new JButton("YES");
        this.haveNotSeenButton = new JButton("NO");

        this.seenButton.addActionListener(this);
        this.haveNotSeenButton.addActionListener(this);

        this.seenButton.setSize(200, 200);
        this.haveNotSeenButton.setSize(200, 200);

        this.seenButton.setLocation(50, 250);
        this.haveNotSeenButton.setLocation(350, 250);
    }

    // MODIFIES: this
    // EFFECTS: paints over the screen then calls the correct methods based on the game status
    @Override
    public void paintComponent(final Graphics graphics) {
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.clearRect(0, 0, width, height);
        graphics.fillRect(0, 0, width, height);
        if (gettingDifficulty) {
            paintDifficultyButtons(graphics);
            paintBackArrowButton();
        } else {
            paintGameButtons(graphics);
            paintRuleText(graphics);
            paintGameText(graphics);
        }

    }

    // MODIFIES: this
    // EFFECTS: controls painting the current state of the game to the screen
    private void paintGameText(Graphics graphics) {
        if (displayWord == null) {
            displayWord = this.wordRecollectionGame.getNextWord();
        }
        graphics.setFont(new Font("MonoLisa", Font.BOLD, 27));
        graphics.drawString("Have you seen: " + displayWord, 10, 30);
    }

    // MODIFIES: this
    // EFFECTS: adds the difficulty buttons, removing the game buttons
    private void paintDifficultyButtons(Graphics graphics) {
        this.add(this.easyButton);
        this.add(this.mediumButton);
        this.add(this.hardButton);

        this.remove(this.haveNotSeenButton);
        this.remove(this.seenButton);
    }

    // MODIFIES: this
    // EFFECTS: paints the rules to the screen
    private void paintRuleText(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("MonoLisa", Font.BOLD, 27));
        graphics.drawString("Rules", 50, 530);
        graphics.setFont(new Font("MonoLisa", Font.PLAIN, 15));
        graphics.drawString("If you have seen the listed word previously in this round, click yes.", 50, 560);
        graphics.drawString("Otherwise, click no", 50, 575);
    }

    // MODIFIES: this
    // EFFECTS: puts the game buttons on the screen and removes the difficulty buttons
    private void paintGameButtons(Graphics graphics) {
        this.add(this.haveNotSeenButton);
        this.add(this.seenButton);

        this.remove(this.easyButton);
        this.remove(this.mediumButton);
        this.remove(this.hardButton);
        this.remove(this.backButton);
    }

    // MODIFIES: this, gameGUI
    // EFFECTS: switches the screen back to the main menu
    //          sets the text at the bottom to show the score
    private void gameOver() {
        this.gettingDifficulty = true;
        try {
            this.gameGUI.getLeaderboards().addPlayerToLeaderboard(this.gameGUI.getPlayer(), this.wordRecollectionGame);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        this.gameGUI.showFrameByString(gameGUI.getTitleMenu());
        String hold = "You scored: " + this.wordRecollectionGame.getCurrentScore() + "\n"
                + "The words you saw throughout the game are: \n"
                + breakUpTheWordsByLines(this.wordRecollectionGame.getWordsFound().toString());
        this.gameGUI.getMenuPanel().setBottomTextField(hold);
    }

    // EFFECTS: breaking up the given string into 7 word chunks
    private String breakUpTheWordsByLines(String text) {
        StringBuilder answer = new StringBuilder();
        int counter = 0;
        for (String line : text.split(",")) {
            if (counter >= 7) {
                counter = 0;
                answer.append("\n");
            }
            counter++;
            answer.append(line);
            answer.append(",");
        }
        return answer.toString();
    }

    // MODIFIES: this
    // EFFECTS: adds the back button to the screen
    private void paintBackArrowButton() {
        this.add(this.backButton);
    }


    // MODIFIES: this
    // EFFECTS: manages the button pressing and calls the correct methods
    @Override
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == this.easyButton) {
                this.wordRecollectionGame = new WordRecollection("Easy");
                this.gettingDifficulty = false;
            } else if (e.getSource() == this.mediumButton) {
                this.wordRecollectionGame = new WordRecollection("Medium");
                this.gettingDifficulty = false;
            } else if (e.getSource() == this.hardButton) {
                this.wordRecollectionGame = new WordRecollection("Hard");
                this.gettingDifficulty = false;
            } else if (e.getSource() == this.seenButton) {
                if (this.wordRecollectionGame.isSeenLastWordBefore()) {
                    this.wordRecollectionGame.addOneToScore();
                    displayWord = this.wordRecollectionGame.getNextWord();
                } else {
                    gameOver();
                }
            } else if (e.getSource() == this.haveNotSeenButton) {
                if (!this.wordRecollectionGame.isSeenLastWordBefore()) {
                    this.wordRecollectionGame.addOneToScore();
                    displayWord = this.wordRecollectionGame.getNextWord();
                } else {
                    gameOver();
                }
            } else if (e.getSource() == this.backButton) {
                this.gameGUI.showFrameByString(gameGUI.getTitleMenu());
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        }
        this.repaint();

    }
}
