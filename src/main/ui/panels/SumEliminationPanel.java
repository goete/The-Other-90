package ui.panels;

import model.SumElimination;
import ui.GameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

// panel for Sum Elimination game
public class SumEliminationPanel extends GamePanel implements ActionListener {
    private SumElimination sumEliminationGame;
    private JToggleButton buttonOne;
    private JToggleButton buttonTwo;
    private JToggleButton buttonThree;
    private JToggleButton buttonFour;
    private JToggleButton buttonFive;
    private JToggleButton buttonSix;
    private JButton submitButton;
    private int target;
    private int numberOfValues;
    private ArrayList<Integer> values;
    private ArrayList<JToggleButton> buttons;
    private boolean numberChange = true;

    public SumEliminationPanel(int width, int height, GameGUI game) {
        super(width, height, game);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(null);
        this.target = Integer.MIN_VALUE;
        this.numberOfValues = 0;
        this.setUpButtonsGame();

        this.buttons = new ArrayList<>();
        buttons.add(buttonOne);
        buttons.add(buttonTwo);
        buttons.add(buttonThree);
        buttons.add(buttonFour);
        buttons.add(buttonFive);
        buttons.add(buttonSix);
    }

    private void setUpButtonsGame() {
        this.submitButton = new JButton("SUBMIT");

        this.buttonOne = new JToggleButton();
        this.buttonTwo = new JToggleButton();
        this.buttonThree = new JToggleButton();
        this.buttonFour = new JToggleButton();
        this.buttonFive = new JToggleButton();
        this.buttonSix = new JToggleButton();

        this.submitButton.addActionListener(this);
        this.buttonOne.addActionListener(this);
        this.buttonTwo.addActionListener(this);
        this.buttonThree.addActionListener(this);
        this.buttonFour.addActionListener(this);
        this.buttonFive.addActionListener(this);
        this.buttonSix.addActionListener(this);

        this.submitButton.setSize(200, 50);
        this.buttonOne.setSize(100, 100);
        this.buttonTwo.setSize(100, 100);
        this.buttonThree.setSize(100, 100);
        this.buttonFour.setSize(100, 100);
        this.buttonFive.setSize(100, 100);
        this.buttonSix.setSize(100, 100);

        this.submitButton.setLocation(200, 550);
    }

    // MODIFIES: this
    // EFFECTS: paints over the screen then calls the correct methods based on the game status
    @Override
    public void paintComponent(final Graphics graphics) {
        screenReset(graphics);
        if (gettingDifficulty) {
            paintDifficultyButtons(graphics);
            paintBackArrowButton();
            removeGameButtons();
            this.sumEliminationGame = null;
        } else {
            if (numberChange) {
                paintGameButtons(graphics);
                numberChange = false;
            }
            removeDifficultyAndBackButtons();
            paintRuleText(graphics);
            paintGameText(graphics);
            paintTimer(graphics);
        }

    }

    protected void paintTimer(final Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("MonoLisa", Font.BOLD, 40));
        graphics.drawString(
                Long.toString(sumEliminationGame.getRoundTimeInSeconds()
                        - (System.currentTimeMillis() - sumEliminationGame.getTimeThatRoundStarted()) / 1000),
                550,
                75);
        if ((sumEliminationGame.getRoundTimeInSeconds()
                - (System.currentTimeMillis() - sumEliminationGame.getTimeThatRoundStarted()) / 1000) < 0) {
            gameOver();
        }
    }


    // MODIFIES: this
    // EFFECTS: controls painting the current state of the game to the screen
    private void paintGameText(Graphics graphics) {
        if (target == Integer.MIN_VALUE) {
            this.values = this.sumEliminationGame.getNextNumbers();
            target = this.sumEliminationGame.getTarget();
            this.numberOfValues = values.size();
            numberChange = true;
        }
        graphics.setFont(new Font("MonoLisa", Font.BOLD, 27));
        graphics.drawString("Target: " + target, 10, 30);
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: paints the rules to the screen
    private void paintRuleText(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("MonoLisa", Font.BOLD, 27));
        graphics.drawString("Rules", 50, 630);
        graphics.setFont(new Font("MonoLisa", Font.PLAIN, 15));
        graphics.drawString("Click the numbers to eliminate from the grouping", 50, 660);
        graphics.drawString("to sum to the target listed up top", 50, 675);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: removes the game buttons
    protected void removeGameButtons() {
        for (JToggleButton button : buttons) {
            this.remove(button);
        }
        this.remove(submitButton);
    }

    // MODIFIES: this
    // EFFECTS: adds the game buttons
    private void paintGameButtons(Graphics graphics) {
        this.add(submitButton);
        for (int i = 0; i < numberOfValues; i++) {
            buttons.get(i).setText(values.get(i).toString());
            buttons.get(i).setSelected(false);
        }
        switch (numberOfValues) {
            case 3:
                paintThreeGameButtons(graphics);
                break;
            case 4:
                paintFourGameButtons(graphics);
                break;
            case 5:
                paintFiveGameButtons(graphics);
                break;
            case 6:
                paintSixGameButtons(graphics);
                break;
        }
    }

    private void paintSixGameButtons(Graphics graphics) {
        this.buttonOne.setText(values.get(0).toString());
        this.buttonTwo.setText(values.get(1).toString());
        this.buttonThree.setText(values.get(2).toString());
        this.buttonFour.setText(values.get(3).toString());
        this.buttonFive.setText(values.get(4).toString());
        this.buttonSix.setText(values.get(5).toString());

        this.buttonOne.setLocation(150, 150);
        this.buttonTwo.setLocation(350, 150);
        this.buttonThree.setLocation(150, 260);
        this.buttonFour.setLocation(350, 260);
        this.buttonFive.setLocation(150, 370);
        this.buttonSix.setLocation(350, 370);

        this.add(buttonOne);
        this.add(buttonTwo);
        this.add(buttonThree);
        this.add(buttonFour);
        this.add(buttonFive);
        this.add(buttonSix);
    }

    private void paintFiveGameButtons(Graphics graphics) {
        this.buttonOne.setText(values.get(0).toString());
        this.buttonTwo.setText(values.get(1).toString());
        this.buttonThree.setText(values.get(2).toString());
        this.buttonFour.setText(values.get(3).toString());
        this.buttonFive.setText(values.get(4).toString());


        this.buttonOne.setLocation(130, 180);
        this.buttonTwo.setLocation(370, 180);
        this.buttonThree.setLocation(130, 370);
        this.buttonFour.setLocation(370, 370);
        this.buttonFive.setLocation(250, 275);


        this.add(buttonOne);
        this.add(buttonTwo);
        this.add(buttonThree);
        this.add(buttonFour);
        this.add(buttonFive);
    }

    private void paintFourGameButtons(Graphics graphics) {
        this.buttonOne.setText(values.get(0).toString());
        this.buttonTwo.setText(values.get(1).toString());
        this.buttonThree.setText(values.get(2).toString());
        this.buttonFour.setText(values.get(3).toString());


        this.buttonOne.setLocation(175, 200);
        this.buttonTwo.setLocation(325, 200);
        this.buttonThree.setLocation(175, 350);
        this.buttonFour.setLocation(325, 350);


        this.add(buttonOne);
        this.add(buttonTwo);
        this.add(buttonThree);
        this.add(buttonFour);
    }

    private void paintThreeGameButtons(Graphics graphics) {
        this.buttonOne.setText(values.get(0).toString());
        this.buttonTwo.setText(values.get(1).toString());
        this.buttonThree.setText(values.get(2).toString());


        this.buttonOne.setLocation(175, 200);
        this.buttonTwo.setLocation(325, 200);
        this.buttonThree.setLocation(250, 350);


        this.add(buttonOne);
        this.add(buttonTwo);
        this.add(buttonThree);

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
        this.target = Integer.MIN_VALUE;

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
            } else if (e.getSource() == this.submitButton) {
                handleSubmission();
                this.values = this.sumEliminationGame.getNextNumbers();
                this.target = this.sumEliminationGame.getTarget();
                this.numberOfValues = this.values.size();
                numberChange = true;

            }
        } catch (Exception ee) {
            ee.printStackTrace();
        }
        this.repaint();

    }

    // MODIFIES: this, sumEliminationGame
    // EFFECTS: Handles the answer submitted by the player
    private void handleSubmission() {
        ArrayList<Integer> answer = new ArrayList<>();
        for (JToggleButton b : buttons) {
            this.remove(b);
        }
        for (int i = 0; i < numberOfValues; i++) {
            if (buttons.get(i).isSelected()) {
                answer.add(Integer.parseInt(buttons.get(i).getText()));
            }
        }
        if (this.sumEliminationGame.isAnswerCorrect(answer)) {
            this.sumEliminationGame.addOneToScore();
        } else {
            gameOver();
        }
    }
}
