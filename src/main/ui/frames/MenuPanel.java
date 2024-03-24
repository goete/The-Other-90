package ui.frames;

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
    private final String titleWord = "Word Recollection";
    private final String titleSum = "Sum Recollection";
    private final String titleLeaderboard = "Leaderboards";
    private final GameGUI game;

    public MenuPanel(int width, int height, GameGUI game) {
        this.game = game;
        this.setPreferredSize(new Dimension(width, height));
        this.height = height;
        this.width = width;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.allTheButtons = new ArrayList<>();
        this.sumEliminationButton = new JButton("Sum Elimination");
        this.highScoresButton = new JButton("High Scores");
        this.wordRecollectionButton = new JButton("Word Recollection");
        this.loadingButton = new JButton("Loading");
        this.savingButton = new JButton("Saving");
        this.allTheButtons.add(this.savingButton);
        this.allTheButtons.add(this.sumEliminationButton);
        this.allTheButtons.add(this.wordRecollectionButton);
        this.allTheButtons.add(this.loadingButton);
        this.allTheButtons.add(this.highScoresButton);
        setUpButtons();
    }

    private void setUpButtons() {
        for (JButton button : this.allTheButtons) {
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.addActionListener(this);
            // TODO: why isn't this working?
            //button.setSize(this.width / this.allTheButtons.size(), this.height / this.allTheButtons.size());
        }
    }


    @Override
    public void paintComponent(final Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.clearRect(0, 0, width, height);
        paintButtons(graphics);
        // paintShuffleHandButton(graphics);
    }

    private void paintButtons(Graphics graphics) {

        this.add(this.sumEliminationButton);
        this.add(this.wordRecollectionButton);
        this.add(this.highScoresButton);
        this.add(this.savingButton);
        this.add(this.loadingButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.sumEliminationButton) {
            game.showFrameByString(this.titleSum);
        } else if (e.getSource() == this.wordRecollectionButton) {
            game.showFrameByString(this.titleWord);
        } else if (e.getSource() == this.highScoresButton) {
            game.showFrameByString(this.titleLeaderboard);
        } else if (e.getSource() == this.loadingButton) {
            game.loadHighScores();
        } else if (e.getSource() == this.savingButton) {
            game.saveHighScores();
        }

    }

    // Reference for using graphics
    // Citation: https://github.com/goete/Scrabble
    /* private void paintShuffleHandButton(final Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillOval(93, 530, 20, 20);
        graphics.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        graphics.setColor(Color.BLACK);
        graphics.drawString("⇄", 95, 547);
    }*/
}
