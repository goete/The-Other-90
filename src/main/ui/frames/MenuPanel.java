package ui.frames;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private int height;
    private int width;

    public MenuPanel(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.height = height;
        this.width = width;
    }


    @Override
    public void paintComponent(final Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.clearRect(0, 0, width, height);
        // paintShuffleHandButton(graphics);
    }

    // Reference for using painting
    // Citation: https://github.com/goete/Scrabble
    /* private void paintShuffleHandButton(final Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillOval(93, 530, 20, 20);
        graphics.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        graphics.setColor(Color.BLACK);
        graphics.drawString("⇄", 95, 547);
    }*/
}
