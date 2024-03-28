package ui.panels;

import model.Leaderboards;
import model.Player;
import ui.GameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

// Leaderboard showcasing panel
public class LeaderboardPanel extends JPanel implements ActionListener {

    private final int height;
    private final int width;
    private Leaderboards leaderboards;
    private boolean gettingInformation;
    private ButtonGroup buttonGameGroup;
    private ButtonGroup buttonDifficultyGroup;
    private JRadioButton wordRecollectionButton;
    private JRadioButton sumEliminationButton;
    private JRadioButton easyButton;
    private JRadioButton mediumButton;
    private JRadioButton hardButton;
    private JButton backButton;
    private JTextField numberOfDesiredPlayersTextField;
    private JButton goAndShowButton;
    private JButton continueButton;
    private final GameGUI gameGUI;
    private int numOfPlayersToView;
    private int position;
    private ArrayList<Player> topN;
    private String leaderboardText;
    private String errorText;

    public LeaderboardPanel(int width, int height, Leaderboards leaderboards, GameGUI gameGUI) {
        this.setPreferredSize(new Dimension(width, height));
        this.height = height;
        this.width = width;
        this.leaderboards = leaderboards;
        this.gettingInformation = true;
        this.setLayout(null);
        this.setUpButtons();
        this.gameGUI = gameGUI;
        this.numOfPlayersToView = 0;
        this.leaderboardText = "";
        this.errorText = "";
    }

    // EFFECTS: calls the methods to set up specified buttons
    private void setUpButtons() {
        this.setUpGameButtons();
        this.setUpDifficultyButtons();
        this.setUpOtherScreenOneButtons();

        this.setUpContinueButton();
    }

    // MODIFIES: this
    // EFFECTS: sets up the continue button
    private void setUpContinueButton() {
        this.continueButton = new JButton("Continue");
        this.continueButton.setSize(200, 50);
        this.continueButton.setLocation(200, 700);
        this.continueButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: sets up buttons and text field for first screen
    private void setUpOtherScreenOneButtons() {
        this.backButton = new JButton("back");
        this.backButton.addActionListener(this);
        this.backButton.setSize(65, 50);
        this.backButton.setLocation(0, 0);

        this.numberOfDesiredPlayersTextField = new JTextField();
        this.numberOfDesiredPlayersTextField.setLocation(275, 550);
        this.numberOfDesiredPlayersTextField.setSize(50, 50);
        this.numberOfDesiredPlayersTextField.addActionListener(this);

        this.goAndShowButton = new JButton("GO!");
        this.goAndShowButton.addActionListener(this);
        this.goAndShowButton.setLocation(270, 650);
        this.goAndShowButton.setSize(60, 50);
    }

    // MODIFIES: this
    // EFFECTS: updates the leaderboard
    public void resetLeaderboardAfterLoad(Leaderboards l) {
        this.leaderboards = l;
    }


    // MODIFIES: this
    // EFFECTS: sets up the buttons for choosing the game
    private void setUpGameButtons() {
        this.buttonGameGroup = new ButtonGroup();
        this.wordRecollectionButton = new JRadioButton("Word Recollection");
        this.wordRecollectionButton.addActionListener(this);
        this.sumEliminationButton = new JRadioButton("Sum Elimination");
        this.sumEliminationButton.addActionListener(this);

        buttonGameGroup.add(this.sumEliminationButton);
        buttonGameGroup.add(this.wordRecollectionButton);

        Dimension size = new Dimension(150, 100);
        this.sumEliminationButton.setSize(size);
        this.wordRecollectionButton.setSize(size);

        this.sumEliminationButton.setHorizontalTextPosition(SwingConstants.CENTER);
        this.sumEliminationButton.setVerticalTextPosition(JRadioButton.TOP);
        this.wordRecollectionButton.setHorizontalTextPosition(SwingConstants.CENTER);
        this.wordRecollectionButton.setVerticalTextPosition(JRadioButton.TOP);

        this.sumEliminationButton.setLocation(width / 2 - 125, 250);
        wordRecollectionButton.setLocation(width / 2 + 25, 250);
    }

    // MODIFIES: this
    // EFFECTS: sets up the buttons for choosing the difficulty buttons
    private void setUpDifficultyButtons() {
        this.buttonDifficultyGroup = new ButtonGroup();
        this.easyButton = new JRadioButton("Easy");
        this.easyButton.addActionListener(this);
        this.mediumButton = new JRadioButton("Medium");
        this.mediumButton.addActionListener(this);
        this.hardButton = new JRadioButton("Hard");
        this.hardButton.addActionListener(this);
        buttonDifficultyGroup.add(this.easyButton);
        buttonDifficultyGroup.add(this.mediumButton);
        buttonDifficultyGroup.add(this.hardButton);
        Dimension size = new Dimension(60, 50);
        this.easyButton.setSize(size);
        this.mediumButton.setSize(size);
        this.hardButton.setSize(size);

        this.easyButton.setHorizontalTextPosition(SwingConstants.CENTER);
        this.easyButton.setVerticalTextPosition(JRadioButton.TOP);
        this.mediumButton.setHorizontalTextPosition(SwingConstants.CENTER);
        this.mediumButton.setVerticalTextPosition(JRadioButton.TOP);
        this.hardButton.setHorizontalTextPosition(SwingConstants.CENTER);
        this.hardButton.setVerticalTextPosition(JRadioButton.TOP);

        this.easyButton.setLocation(this.width / 2 - 30 - 60, 400);
        this.mediumButton.setLocation(this.width / 2 - 30, 400);
        this.hardButton.setLocation(this.width / 2 + 30, 400);

    }

    // MODIFIES: this
    // EFFECTS: calls the correct methods to paint the desired screen based on the situation
    @Override
    public void paintComponent(final Graphics graphics) {
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.clearRect(0, 0, width, height);
        graphics.fillRect(0, 0, width, height);
        if (gettingInformation) {
            paintQuestionButtons(graphics);
            paintGettingTexts(graphics);
        } else {
            paintContinueButton(graphics);
            paintCorrectLeaderboard(graphics);
        }

    }

    // MODIFIES: this
    // EFFECTS: puts the leaderboard on the screen
    private void paintCorrectLeaderboard(Graphics graphics) {
        graphics.setFont(new Font("MonoLisa", Font.PLAIN, 15));
        graphics.setColor(Color.BLACK);
        drawStringWithLineBreaks(graphics, this.leaderboardText, 50, 50);
    }

    // MODIFIES: this
    // EFFECTS: draws the given text while respecting the line breaks
    private void drawStringWithLineBreaks(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n")) {
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
        }
    }

    // MODIFIES: this
    // EFFECTS: puts the continue button on the screen, removing the previous screens buttons
    private void paintContinueButton(Graphics graphics) {
        this.add(this.continueButton);

        this.remove(this.hardButton);
        this.remove(this.easyButton);
        this.remove(this.mediumButton);
        this.remove(this.wordRecollectionButton);
        this.remove(this.sumEliminationButton);
        this.remove(this.goAndShowButton);
        this.remove(numberOfDesiredPlayersTextField);
    }

    // MODIFIES: this
    // EFFECTS: puts the text on the screen
    private void paintGettingTexts(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("MonoLisa", Font.BOLD, 18));
        graphics.drawString("Please select the desired mode, difficulty, and player count", 25, 80);
        graphics.setFont(new Font("MonoLisa", Font.PLAIN, 12));
        graphics.drawString("Input the number of players you would like to see",
                this.numberOfDesiredPlayersTextField.getX() - 100,
                this.numberOfDesiredPlayersTextField.getY() - 20);
        this.add(this.numberOfDesiredPlayersTextField);
        this.add(this.goAndShowButton);
        graphics.setColor(Color.RED);
        graphics.drawString(errorText, this.numberOfDesiredPlayersTextField.getX() - 50,
                this.numberOfDesiredPlayersTextField.getY() + 90);

    }

    // MODIFIES: this
    // EFFECTS: puts the question buttons on the screen, removing the other screens buttons
    private void paintQuestionButtons(Graphics graphics) {
        this.add(easyButton);
        this.add(mediumButton);
        this.add(hardButton);

        this.add(wordRecollectionButton);
        this.add(sumEliminationButton);

        this.add(backButton);
        this.add(numberOfDesiredPlayersTextField);

        this.remove(this.continueButton);
    }

    // MODIFIES: this
    // EFFECTS: controls the actions after buttons are pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.goAndShowButton) {
            if (this.buttonGameGroup.getSelection() != null && this.buttonDifficultyGroup != null
                    && this.numberOfDesiredPlayersTextField.getText() != null
                    && isAllNumbers(this.numberOfDesiredPlayersTextField.getText())) {
                switchAndShowDesired();
                setTheTextForLeaderboards();
            } else {
                this.errorText = "Please select valid inputs";
            }
        } else if (e.getSource() == this.continueButton) {
            switchAndShowDesired();
        } else if (e.getSource() == this.backButton) {
            this.gameGUI.showFrameByString(gameGUI.getTitleMenu());
            this.gettingInformation = true;
            this.errorText = "";
        }
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: sets the leaderboard text
    private void setTheTextForLeaderboards() {
        this.numOfPlayersToView = Integer.parseInt(this.numberOfDesiredPlayersTextField.getText());
        this.position = 1;
        try {
            if (this.wordRecollectionButton.isSelected()) {
                if (this.easyButton.isSelected()) {
                    this.leaderboardText = handleWordEasyHighScore();
                } else if (this.mediumButton.isSelected()) {
                    this.leaderboardText = handleWordMediumHighScore();
                } else if (this.hardButton.isSelected()) {
                    this.leaderboardText = handleWordHardHighScore();
                }
            } else if (this.sumEliminationButton.isSelected()) {
                if (this.easyButton.isSelected()) {
                    this.leaderboardText = handleSumEasyHighScore();
                } else if (this.mediumButton.isSelected()) {
                    this.leaderboardText = handleSumMediumHighScore();
                } else if (this.hardButton.isSelected()) {
                    this.leaderboardText = handleSumHardHighScore();
                }
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: switches the screen, resetting the errors
    private void switchAndShowDesired() {
        this.gettingInformation = !this.gettingInformation;
        this.errorText = "";
        repaint();
    }

    // EFFECTS: makes sure that the given text is all numbers
    private boolean isAllNumbers(String text) {
        return text.matches("[0-9]+");
    }

    // MODIFIES: this
    // EFFECT: Prints out the top requested players for Word Recollection Hard
    private String handleWordHardHighScore() throws InvocationTargetException, IllegalAccessException {
        StringBuilder stringBuilder = new StringBuilder();
        topN = leaderboards.getWordRecollectionHard().getTopNPlayers(numOfPlayersToView);
        for (Player player : topN) {
            stringBuilder.append(position).append(". ").append(player.getName())
                    .append(" has a score of ").append(player.getWordRecollectionHardHighScore()).append("\n");
            position++;
        }
        return stringBuilder.toString();
    }

    // MODIFIES: this
    // EFFECT: Prints out the top requested players for Word Recollection Medium
    private String handleWordMediumHighScore() throws InvocationTargetException, IllegalAccessException {
        StringBuilder stringBuilder = new StringBuilder();
        topN = leaderboards.getWordRecollectionMedium().getTopNPlayers(numOfPlayersToView);
        for (Player player : topN) {
            stringBuilder.append(position).append(". ").append(player.getName())
                    .append(" has a score of ").append(player.getWordRecollectionMediumHighScore()).append("\n");
            position++;
        }
        return stringBuilder.toString();
    }

    // MODIFIES: this
    // EFFECT: returns the top requested players for Word Recollection Easy
    private String handleWordEasyHighScore() throws InvocationTargetException, IllegalAccessException {
        StringBuilder stringBuilder = new StringBuilder();
        topN = leaderboards.getWordRecollectionEasy().getTopNPlayers(numOfPlayersToView);
        for (Player player : topN) {
            stringBuilder.append(position).append(". ").append(player.getName())
                    .append(" has a score of ").append(player.getWordRecollectionEasyHighScore()).append("\n");
            position++;
        }
        return stringBuilder.toString();
    }

    // MODIFIES: this
    // EFFECT: returns the top requested players for Sum Elimination Hard
    private String handleSumHardHighScore() throws InvocationTargetException, IllegalAccessException {
        StringBuilder stringBuilder = new StringBuilder();
        topN = leaderboards.getSumEliminationHard().getTopNPlayers(numOfPlayersToView);
        for (Player player : topN) {
            stringBuilder.append(position).append(". ").append(player.getName())
                    .append(" has a score of ").append(player.getSumEliminationHardHighScore()).append("\n");
            position++;
        }
        return stringBuilder.toString();
    }

    // MODIFIES: this
    // EFFECT: returns the top requested players for Sum Elimination Medium
    private String handleSumMediumHighScore() throws InvocationTargetException, IllegalAccessException {
        StringBuilder stringBuilder = new StringBuilder();
        topN = leaderboards.getSumEliminationMedium().getTopNPlayers(numOfPlayersToView);
        for (Player player : topN) {
            stringBuilder.append(position).append(". ").append(player.getName()).append(" has a score of ")
                    .append(player.getSumEliminationMediumHighScore()).append("\n");
            position++;
        }
        return stringBuilder.toString();
    }

    // MODIFIES: this
    // EFFECT: Returns the top requested players for Sum Elimination Easy
    private String handleSumEasyHighScore() throws InvocationTargetException, IllegalAccessException {
        StringBuilder stringBuilder = new StringBuilder();
        topN = leaderboards.getSumEliminationEasy().getTopNPlayers(numOfPlayersToView);
        for (Player player : topN) {
            stringBuilder.append(position).append(". ").append(player.getName()).append(" has a score of ")
                    .append(player.getSumEliminationEasyHighScore()).append("\n");
            position++;
        }
        return stringBuilder.toString();
    }

}
