package ui;

import ui.listeners.Mouse;
import ui.frames.*;

import java.awt.*;
import javax.swing.*;

public class GameGUI extends Canvas {

    private final int fps = 60;
    private final int width = 600;
    private final int height = 800;
    private final String titleMenu = "Game";
    private final String titleWord = "Word Recollection";
    private final String titleSum = "Sum Recollection";
    private final String titleLeaderboard = "Leaderboards";
    private final String titleSplash = "Loading";
    private final String startingScreenName = titleMenu; // TODO: CHANGE TO SPLASH
    private MenuPanel menuPanel;
    private JFrame overallFrame;
    private Mouse mouse;
    private JPanel cards;
    private Container pane;


    public GameGUI() {
        cards = new JPanel(new CardLayout());
        mouse = new Mouse();
        cards.addMouseListener(mouse);
        cards.addMouseMotionListener(mouse);
        cards.addMouseWheelListener(mouse);
        setUpPanels();
        addingPanels();
        pane = overallFrame.getContentPane();
        pane.add(cards);

        showFrameByString(startingScreenName);
        //Display the window.
        overallFrame.pack();
        overallFrame.setVisible(true);
        overallFrame.setResizable(false);

        showFrameByString(startingScreenName);
    }

    private void addingPanels() {
        cards.add(menuPanel, titleMenu);
    }

    private void setUpPanels() {
        menuPanel = new MenuPanel(width, height);
        overallFrame = new JFrame();
        overallFrame.setSize(600, 600);
        overallFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void showFrameByString(String frameTitle) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, frameTitle);

    }

}
