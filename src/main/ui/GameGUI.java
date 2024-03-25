package ui;

import model.Leaderboards;
import model.Player;
import persistence.JsonReaderLeaderboards;
import persistence.JsonWriter;
import ui.listeners.Mouse;
import ui.panels.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

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
    private LeaderboardPanel leaderboardPanel;
    private SumEliminationPanel sumEliminationPanel;
    private WordRecollectionPanel wordRecollectionPanel;
    private LoadingScreenPanel loadingScreenPanel;
    private JFrame overallFrame;
    private Mouse mouse;
    private JPanel cards;
    private Container pane;
    private final long drawInterval;
    private String currentFrame;
    private Player player;
    private String name;
    private Leaderboards leaderboards;
    private final JsonReaderLeaderboards jsonReaderLeaderboards;
    private final JsonWriter jsonWriter;


    public GameGUI() {
        cards = new JPanel(new CardLayout());
        settingUpMouseListener();
        setUpPanels();
        addingPanels();
        pane = overallFrame.getContentPane();
        pane.add(cards);
        this.drawInterval = 2000 / this.fps;
        this.currentFrame = startingScreenName;
        showFrameByString(startingScreenName);
        overallFrame.pack();
        overallFrame.setVisible(true);
        overallFrame.setResizable(false);
        showFrameByString(startingScreenName);
        centreOnScreen();
        overallFrame.repaint();
        setVisible(true);
        overallFrame.repaint();
        this.setVisible(true);
        this.leaderboards = new Leaderboards();
        jsonWriter = new JsonWriter("./data/Players.json");
        jsonReaderLeaderboards = new JsonReaderLeaderboards("./data/Players.json");
    }

    private void settingUpMouseListener() {
        mouse = new Mouse();
        cards.addMouseListener(mouse);
        cards.addMouseMotionListener(mouse);
        cards.addMouseWheelListener(mouse);
    }


    // Centres frame on desktop
    // modifies: this
    // effects:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }


    private void addingPanels() {
        cards.add(menuPanel, titleMenu);
        cards.add(sumEliminationPanel, titleSum);
        cards.add(wordRecollectionPanel, titleWord);
        cards.add(leaderboardPanel, titleLeaderboard);
        cards.add(loadingScreenPanel, titleSplash);
    }

    private void setUpPanels() {
        this.menuPanel = new MenuPanel(width, height, this);
        this.sumEliminationPanel = new SumEliminationPanel(width, height);
        this.wordRecollectionPanel = new WordRecollectionPanel(width, height);
        this.leaderboardPanel = new LeaderboardPanel(width, height);
        this.loadingScreenPanel = new LoadingScreenPanel(width, height);
        overallFrame = new JFrame();
        overallFrame.setSize(600, 600);
        overallFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void showFrameByString(String frameTitle) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, frameTitle);
        this.currentFrame = frameTitle;
        overallFrame.repaint();
    }


    public void loadHighScores() {
        try {
            this.leaderboards = this.jsonReaderLeaderboards.readLeaderboards();
            // TODO: System.out.println("Loading Success!");
            potentiallyLoggingIn();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void potentiallyLoggingIn() {
        for (Player p : this.leaderboards.getAllPlayers()) {
            if (p.getName().equals(this.name)) {
                this.player = p;
                // TODO: System.out.println("Successfully logged you in as " + this.player.getName());
                break;
            }
        }

    }

    // TODO
    public void saveHighScores() {
        try {
            this.jsonWriter.open();
            this.jsonWriter.write(this.leaderboards);
            this.jsonWriter.close();
            // System.out.println("Saving Success!"); todo
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void repaint() {
        super.repaint();
        this.repaintingCurrentFrame(super.getGraphics());
    }

    public void repaintingCurrentFrame(Graphics g) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, this.currentFrame);
        super.repaint();
    }

    public void update() {
        repaint();
        loop();
    }


    public String getTitleLeaderboard() {
        return titleLeaderboard;
    }

    public String getTitleWord() {
        return titleWord;
    }

    public String getTitleSum() {
        return titleSum;
    }

    public void loop() {
        long lastLoopTime = System.currentTimeMillis();
        while (overallFrame.isActive()) {
            update();
            long delta = System.currentTimeMillis() - lastLoopTime;
            lastLoopTime = System.currentTimeMillis();
            long waitTime = drawInterval - delta;
            // if we have taken up too much time, don't wait!
            if (waitTime < 0) {
                waitTime = 0;
            }
            // finally pause for a bit.
            try {
                Thread.sleep(waitTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
