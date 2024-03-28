package ui;

import model.Leaderboards;
import model.Player;
import persistence.JsonReaderLeaderboards;
import persistence.JsonWriter;
import ui.listeners.Mouse;
import ui.panels.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
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
    }

    public BufferedImage getCornerLogo() {
        return cornerLogo;
    }

    private void settingUpMouseListener() {
        mouse = new Mouse();
        cards.addMouseListener(mouse);
        cards.addMouseMotionListener(mouse);
        cards.addMouseWheelListener(mouse);
    }

    private BufferedImage loadImage(String filename) {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bufferedImage;
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
        this.wordRecollectionPanel = new WordRecollectionPanel(width, height, this);
        this.leaderboardPanel = new LeaderboardPanel(width, height, this.leaderboards, this);
        this.loadingScreenPanel = new LoadingScreenPanel(width, height, this);
        overallFrame = new JFrame();
        overallFrame.setSize(600, 600);
        overallFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void showFrameByString(String frameTitle) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, frameTitle);
        this.currentFrame = frameTitle;
    }


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

    private void potentiallyLoggingIn() {
        for (Player p : this.leaderboards.getAllPlayers()) {
            if (p.getName().equals(this.name)) {
                this.player = p;
                this.menuPanel.setBottomTextField(" Loading high scores a success! \n "
                        + "Successfully logged you in as " + this.player.getName());
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
}
