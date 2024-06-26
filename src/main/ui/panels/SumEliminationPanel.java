package ui.panels;

import model.SumElimination;
import model.WordRecollection;
import ui.GameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

// panel for Sum Elimination game
public class SumEliminationPanel extends GamePanel implements ActionListener {
    private final int height;
    private final int width;

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
        } else {
            // paintGameButtons(graphics);
            // paintRuleText(graphics);
            // paintGameText(graphics);
        }

    }

    @Override
    protected void gameOver() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == this.easyButton) {
                this.gettingDifficulty = false;
            } else if (e.getSource() == this.mediumButton) {
                this.gettingDifficulty = false;
            } else if (e.getSource() == this.hardButton) {
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
