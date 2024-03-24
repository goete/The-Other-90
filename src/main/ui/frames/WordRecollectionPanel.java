package ui.frames;

import javax.swing.*;
import java.awt.*;

public class WordRecollectionPanel extends JPanel {

    private final int height;
    private final int width;

    public WordRecollectionPanel(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.height = height;
        this.width = width;
    }
}
