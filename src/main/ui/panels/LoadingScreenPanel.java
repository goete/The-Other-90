package ui.panels;

import javax.swing.*;
import java.awt.*;

public class LoadingScreenPanel extends JPanel {

    private final int height;
    private final int width;

    public LoadingScreenPanel(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.height = height;
        this.width = width;
    }
}
