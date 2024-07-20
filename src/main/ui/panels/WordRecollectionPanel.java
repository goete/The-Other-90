package ui.panels;

import model.WordRecollection;
import ui.GameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

// manages the Word Recollection game and respective panel
public class WordRecollectionPanel extends GamePanel implements ActionListener {
    private final int height;
    private final int width;
    private JButton seenButton;
    private JButton haveNotSeenButton;
    private WordRecollection wordRecollectionGame;
    private String displayWord;

    public WordRecollectionPanel(int width, int height, GameGUI game) {
        super(game);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(null);
        this.height = height;
        this.width = width;
        this.setUpButtonsGame();
        this.displayWord = null;
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
            removeGameButtons();
        } else {
            paintGameButtons(graphics);
            paintRuleText(graphics);
            paintGameText(graphics);
            removeDifficultyAndBackButtons();
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
    // EFFECTS: puts the game buttons on the screen
    private void paintGameButtons(Graphics graphics) {
        this.add(this.haveNotSeenButton);
        this.add(this.seenButton);
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

    @Override
    // MODIFIES: this
    // EFFECTS: removes the game buttons
    protected void removeGameButtons() {
        this.remove(this.haveNotSeenButton);
        this.remove(this.seenButton);
    }

    // MODIFIES: this
    // EFFECTS: manages the button pressing and calls the correct methods
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

    @Override
    // MODIFIES: this, gameGUI
    // EFFECTS: switches the screen back to the main menu
    //          sets the text at the bottom to show the score
    protected void gameOver() {
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

}
