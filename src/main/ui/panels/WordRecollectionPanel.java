package ui.panels;

import model.WordRecollection;
import ui.GameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

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
    }

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

    @Override
    public void paintComponent(final Graphics graphics) {
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.clearRect(0, 0, width, height);
        graphics.fillRect(0, 0, width, height);
        if (gettingDifficulty) {
            paintDifficultyButtons(graphics);
        } else {
            paintGameButtons(graphics);
            paintRuleText(graphics);
            paintGameText(graphics);
        }

    }

    private void paintGameText(Graphics graphics) {
        if (displayWord == null) {
            displayWord = this.wordRecollectionGame.getNextWord();
        }
        graphics.setFont(new Font("MonoLisa", Font.BOLD, 27));
        graphics.drawString("Have you seen: " + displayWord, 10, 30);
    }

    private void paintDifficultyButtons(Graphics graphics) {
        this.add(this.easyButton);
        this.add(this.mediumButton);
        this.add(this.hardButton);

        this.remove(this.haveNotSeenButton);
        this.remove(this.seenButton);
    }

    private void paintRuleText(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("MonoLisa", Font.BOLD, 27));
        graphics.drawString("Rules", 50, 530);
        graphics.setFont(new Font("MonoLisa", Font.PLAIN, 15));
        graphics.drawString("If you have seen the listed word previously in this round, click yes.", 50, 560);
        graphics.drawString("Otherwise, click no", 50, 575);
    }

    private void paintGameButtons(Graphics graphics) {
        this.add(this.haveNotSeenButton);
        this.add(this.seenButton);

        this.remove(this.easyButton);
        this.remove(this.mediumButton);
        this.remove(this.hardButton);
    }

    private void gameOver() {
        this.gettingDifficulty = true;
        this.gameGUI.showFrameByString(gameGUI.getTitleMenu());
    }


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
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        }
        this.repaint();

    }
}
