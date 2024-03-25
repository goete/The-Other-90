package ui.panels;

import javax.swing.*;
import java.awt.*;

public class LeaderboardPanel extends JPanel {

    private final int height;
    private final int width;

    public LeaderboardPanel(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.height = height;
        this.width = width;
    }
}
