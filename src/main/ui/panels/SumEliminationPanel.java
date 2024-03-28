package ui.panels;

import javax.swing.*;
import java.awt.*;

// panel for Sum Elimination game
public class SumEliminationPanel extends JPanel {

    private final int height;
    private final int width;

    public SumEliminationPanel(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.height = height;
        this.width = width;
    }


}
