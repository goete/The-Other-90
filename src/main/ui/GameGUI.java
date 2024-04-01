package ui;

import model.Leaderboards;
import model.Player;
import model.events.EventLog;
import model.events.Event;
import persistence.JsonReaderLeaderboards;
import persistence.JsonWriter;
import ui.listeners.Mouse;
import ui.panels.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

public class GameGUI extends Canvas implements WindowListener, WindowFocusListener, WindowStateListener {
    private final int fps = 60;
    private final int width = 600;
    private final int height = 800;
    private final String titleMenu = "Game";
    private final String titleWord = "Word Recollection";
    private final String titleSum = "Sum Recollection";
    private final String titleLeaderboard = "Leaderboards";
    private final String titleSplash = "Loading";
    private final String startingScreenName = titleSplash;
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
    private BufferedImage cornerLogo;
    private String topCornerFilePath = "data/top-Left-Corner.jpg";

    public GameGUI() {
        this.cornerLogo = loadImage(topCornerFilePath);
        this.leaderboards = new Leaderboards();
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
        overallFrame.repaint();
        setVisible(true);
        overallFrame.repaint();
        this.setVisible(true);
        jsonWriter = new JsonWriter("./data/Players.json");
        jsonReaderLeaderboards = new JsonReaderLeaderboards("./data/Players.json");
        overallFrame.addWindowListener(this);
    }

    public BufferedImage getCornerLogo() {
        return cornerLogo;
    }

    // MODIFIES: this
    // EFFECTS: sets up mouse listener (currently unused but will need once sum elimination is implemented)
    private void settingUpMouseListener() {
        mouse = new Mouse();
        cards.addMouseListener(mouse);
        cards.addMouseMotionListener(mouse);
        cards.addMouseWheelListener(mouse);
    }

    // EFFECTS: loads in images based on the given file path
    private BufferedImage loadImage(String filename) {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bufferedImage;
    }

    // MODIFIES: this
    // EFFECTS: adds the panels to the CardLayout
    private void addingPanels() {
        cards.add(menuPanel, titleMenu);
        cards.add(sumEliminationPanel, titleSum);
        cards.add(wordRecollectionPanel, titleWord);
        cards.add(leaderboardPanel, titleLeaderboard);
        cards.add(loadingScreenPanel, titleSplash);
    }

    // MODIFIES: this
    // EFFECTS: sets up the panels and the screen
    private void setUpPanels() {
        this.menuPanel = new MenuPanel(width, height, this);
        this.sumEliminationPanel = new SumEliminationPanel(width, height);
        this.wordRecollectionPanel = new WordRecollectionPanel(width, height, this);
        this.leaderboardPanel = new LeaderboardPanel(width, height, this.leaderboards, this);
        this.loadingScreenPanel = new LoadingScreenPanel(width, height, this);
        overallFrame = new JFrame();
        overallFrame.setSize(600, 600);
        overallFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // REQUIRES: cards contains a frame gettable from frameTitle
    // MODIFIES: this
    // EFFECTS: shows the correct frame based on the given string
    public void showFrameByString(String frameTitle) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, frameTitle);
        this.currentFrame = frameTitle;
    }


    // MODIFIES: this
    // EFFECTS: loads in the previous high scores
    public void loadHighScores() {
        try {
            this.leaderboards = this.jsonReaderLeaderboards.readLeaderboards();
            this.menuPanel.setBottomTextField(" Loading high scores a success!");
            potentiallyLoggingIn();
            this.leaderboardPanel.resetLeaderboardAfterLoad(this.leaderboards);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.repaint();
    }

    // MODIFIES: this
    // EFFECTS: after loading, if current player name is one in the leaderboard, it logs them back in
    private void potentiallyLoggingIn() {
        for (Player p : this.leaderboards.getAllPlayers()) {
            if (p.getName().equals(this.name)) {
                this.player = p;
                this.menuPanel.setBottomTextField(" Loading high scores a success! \n "
                        + "Welcome back " + this.player.getName() + "!");
                this.player.setSumEliminationEasyHighScore(p.getSumEliminationEasyHighScore());
                this.player.setSumEliminationMediumHighScore(p.getSumEliminationMediumHighScore());
                this.player.setSumEliminationHardHighScore(p.getSumEliminationHardHighScore());
                this.player.setWordRecollectionEasyHighScore(p.getWordRecollectionEasyHighScore());
                this.player.setWordRecollectionMediumHighScore(p.getWordRecollectionMediumHighScore());
                this.player.setSumEliminationHardHighScore(p.getWordRecollectionHardHighScore());
                break;
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: writes the leaderboard to the file to save
    public void saveHighScores() {
        try {
            this.jsonWriter.open();
            this.jsonWriter.write(this.leaderboards);
            this.jsonWriter.close();
            this.menuPanel.setBottomTextField("Successfully saved your high scores!");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.repaint();
    }

    // MODIFIES: this
    // EFFECTS: repaints the screen
    @Override
    public void repaint() {
        super.repaint();
        this.repaintingCurrentPanel(super.getGraphics());
    }

    // MODIFIES: this
    // EFFECTS: repaints the current panel
    public void repaintingCurrentPanel(Graphics g) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, this.currentFrame);
        super.repaint();
    }

    // EFFECTS: updates the screen and loop
    public void update() {
        repaint();
        loop();
    }

    // MODIFIES: this
    // EFFECTS: sets the current player based on the given name
    public void setPlayerBasedOnName(String name) {
        this.player = new Player(name);
        this.name = name;
        this.leaderboards.addToAllLeaderboards(player);
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

    public SumEliminationPanel getSumEliminationPanel() {
        return sumEliminationPanel;
    }

    public WordRecollectionPanel getWordRecollectionPanel() {
        return wordRecollectionPanel;
    }

    // MODIFIES: this
    // EFFECTS: game loop logic
    // Citation: https://github.com/goete/Scrabble
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

    public Leaderboards getLeaderboards() {
        return leaderboards;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String getTitleMenu() {
        return titleMenu;
    }

    public MenuPanel getMenuPanel() {
        return this.menuPanel;
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("YES");
        EventLog eventLog = EventLog.getInstance();
        for (Event event : eventLog) {
            System.out.println(event.toString());
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void windowGainedFocus(WindowEvent e) {

    }

    @Override
    public void windowLostFocus(WindowEvent e) {

    }

    @Override
    public void windowStateChanged(WindowEvent e) {

    }
}
