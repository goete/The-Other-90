package ui.panels;

import model.SumElimination;
import ui.GameGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

// panel for Sum Elimination game
public class SumEliminationPanel extends GamePanel implements ActionListener {
    private final int height;
    private final int width;
    private SumElimination sumEliminationGame;

    public SumEliminationPanel(int width, int height, GameGUI game) {
        super(game);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(null);
        this.height = height;
        this.width = width;
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
            removeDifficultyAndBackButtons();
            // paintRuleText(graphics);
            // paintGameText(graphics);
        }

    }

    @Override
    // MODIFIES: this
    // EFFECTS: removes the game buttons
    protected void removeGameButtons() {
    }

    private void paintGameButtons(Graphics graphics) {
    }

    @Override
    // MODIFIES: this, gameGUI
    // EFFECTS: switches the screen back to the main menu
    //          sets the text at the bottom to show the score
    protected void gameOver() {
        this.gettingDifficulty = true;
        try {
            this.gameGUI.getLeaderboards().addPlayerToLeaderboard(this.gameGUI.getPlayer(), this.sumEliminationGame);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        this.gameGUI.showFrameByString(gameGUI.getTitleMenu());
        String hold = "You scored: " + this.sumEliminationGame.getCurrentScore();
        this.gameGUI.getMenuPanel().setBottomTextField(hold);

    }

    // MODIFIES: this
    // EFFECTS: manages the button pressing and calls the correct methods
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == this.easyButton) {
                this.sumEliminationGame = new SumElimination("Easy");
                this.gettingDifficulty = false;
            } else if (e.getSource() == this.mediumButton) {
                this.sumEliminationGame = new SumElimination("Medium");
                this.gettingDifficulty = false;
            } else if (e.getSource() == this.hardButton) {
                this.sumEliminationGame = new SumElimination("Hard");
                this.gettingDifficulty = false; //add after here
            } else if (e.getSource() == this.backButton) {
                this.gameGUI.showFrameByString(gameGUI.getTitleMenu());
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        }
        this.repaint();

    }
}
