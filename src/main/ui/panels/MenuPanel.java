package ui.panels;

import ui.GameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuPanel extends JPanel implements ActionListener {
    private final int height;
    private final int width;
    private final JButton sumEliminationButton;
    private final JButton wordRecollectionButton;
    private final JButton highScoresButton;
    private final JButton loadingButton;
    private final JButton savingButton;
    private final ArrayList<JButton> allTheButtons;
    private final GameGUI game;
    private final int buttonHeight;
    private Image topLogo;
    private String bottomText;

    // Citation: https://github.com/goete/Scrabble
    public MenuPanel(int width, int height, GameGUI game) {
        this.game = game;
        this.setPreferredSize(new Dimension(width, height));
        this.height = height;
        this.width = width;
        this.setLayout(null);
        this.allTheButtons = new ArrayList<>();
        this.sumEliminationButton = new JButton("Sum Elimination");
        this.highScoresButton = new JButton("High Scores");
        this.wordRecollectionButton = new JButton("Word Recollection");
        this.loadingButton = new JButton("Loading");
        this.savingButton = new JButton("Saving");
        this.allTheButtons.add(this.sumEliminationButton);
        this.allTheButtons.add(this.wordRecollectionButton);
        this.allTheButtons.add(this.highScoresButton);
        this.allTheButtons.add(this.savingButton);
        this.allTheButtons.add(this.loadingButton);
        this.buttonHeight = (this.height - 50 - 300) / this.allTheButtons.size();
        this.bottomText = "Welcome!";
        this.setUpButtons();
        this.setUpBottomTextAndLogo();
        this.game.repaint();
    }

    private void setUpBottomTextAndLogo() {
        this.topLogo = this.game.getCornerLogo().getScaledInstance(50, 50, 0);
    }

    public void setBottomTextField(String text) {
        this.bottomText = text;
    }

    private void setUpButtons() {
        int counter = 0;
        for (JButton button : this.allTheButtons) {
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.addActionListener(this);
            button.setSize(this.width, this.buttonHeight);
            button.setLocation(0, this.buttonHeight * counter + 50);
            counter++;
        }
    }


    @Override
    public void paintComponent(final Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.clearRect(0, 0, width, height);
        paintButtons();
        paintTextField(graphics);
        paintLogo(graphics);
    }

    private void paintLogo(Graphics graphics) {
        graphics.drawImage(this.topLogo, 0, 0, this);
    }

    private void paintTextField(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("MonoLisa", Font.PLAIN, 15));
        this.drawStringWithLineBreaks(graphics, this.bottomText, 0, 500);
        graphics.setFont(new Font("MonoLisa", Font.PLAIN, 27));
        graphics.drawString("The Other 90", 210, 40);
    }


    private void drawStringWithLineBreaks(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n")) {
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
        }
    }

    private void paintButtons() {
        this.add(this.sumEliminationButton);
        this.add(this.wordRecollectionButton);
        this.add(this.highScoresButton);
        this.add(this.savingButton);
        this.add(this.loadingButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.sumEliminationButton) {
            game.showFrameByString(this.game.getTitleSum());
        } else if (e.getSource() == this.wordRecollectionButton) {
            // game.getWordRecollectionPanel().startingNewGame();
            game.showFrameByString(this.game.getTitleWord());
        } else if (e.getSource() == this.highScoresButton) {
            game.showFrameByString(this.game.getTitleLeaderboard());
        } else if (e.getSource() == this.loadingButton) {
            game.loadHighScores();
        } else if (e.getSource() == this.savingButton) {
            game.saveHighScores();
        }

    }

    // Reference for using graphics
    // Citation: https://github.com/goete/Scrabble
//    private void paintShuffleHandButton(final Graphics graphics) {
//        graphics.setColor(Color.WHITE);
//        graphics.fillOval(93, 530, 20, 20);
//        graphics.setFont(new Font("TimesRoman", Font.PLAIN, 18));
//        graphics.setColor(Color.BLACK);
//        graphics.drawString("⇄", 95, 547);
//    }
}
