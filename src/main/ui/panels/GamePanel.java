package ui.panels;

import ui.GameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class GamePanel extends JPanel implements ActionListener {
    protected final int height;
    protected final int width;
    protected boolean gettingDifficulty;
    protected JButton easyButton;
    protected JButton mediumButton;
    protected JButton hardButton;
    protected JButton backButton;
    protected GameGUI gameGUI;
    private String currentDifficulty;
    private Integer time;

    public GamePanel(int width, int height, GameGUI game) {
        this.gettingDifficulty = true;
        this.gameGUI = game;
        setUpButtonsDifficulty();
        this.height = height;
        this.width = width;
    }

    // MODIFIES: this
    // EFFECTS: sets up all the buttons for choosing which difficulty is desired
    protected void setUpButtonsDifficulty() {
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

        this.backButton = new JButton("back");
        this.backButton.addActionListener(this);
        this.backButton.setSize(65, 50);
        this.backButton.setLocation(0, 0);
    }

    // MODIFIES: this
    // EFFECTS: adds the difficulty buttons
    protected void paintDifficultyButtons(Graphics graphics) {
        this.add(this.easyButton);
        this.add(this.mediumButton);
        this.add(this.hardButton);
    }

    // MODIFIES: this
    // EFFECTS: removes the difficulty and back buttons
    protected void removeDifficultyAndBackButtons() {
        this.remove(this.easyButton);
        this.remove(this.mediumButton);
        this.remove(this.hardButton);
        this.remove(this.backButton);
    }


    protected void screenReset(final Graphics graphics) {
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.clearRect(0, 0, width, height);
        graphics.fillRect(0, 0, width, height);
    }

    // MODIFIES: this, gameGUI
    // EFFECTS: switches the screen back to the main menu
    //          sets the text at the bottom to show the score
    protected abstract void gameOver();

    protected abstract void paintTimer(Graphics graphics);

    protected abstract void removeGameButtons();

    // MODIFIES: this
    // EFFECTS: adds the back button to the screen
    protected void paintBackArrowButton() {
        this.add(this.backButton);
    }

    // MODIFIES: this
    // EFFECTS: manages the button pressing and calls the correct methods
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public abstract void actionPerformed(ActionEvent e);
}
