package ui.panels;

import javax.swing.*;
import java.awt.*;

public class WordRecollectionPanel extends JPanel {

    private final int height;
    private final int width;

    public WordRecollectionPanel(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.height = height;
        this.width = width;
        repaint();
    }

    @Override
    public void paintComponent(final Graphics graphics) {
        graphics.setColor(Color.BLUE);
        graphics.clearRect(0, 0, width, height);
        graphics.fillRect(0, 0, width, height);
        paintButtons(graphics);
    }

    private void paintButtons(Graphics graphics) {

    }
}
