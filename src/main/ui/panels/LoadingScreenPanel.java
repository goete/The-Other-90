package ui.panels;

import ui.GameGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class LoadingScreenPanel extends JPanel implements ActionListener {

    private final int height;
    private final int width;
    private Image mainLogo;
    private final String mainLogoFilePath = "data/MainLoadingImage.jpg";
    private JTextField userEnteringNameTextField;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel eastPanel;
    private JPanel westPanel;
    private GameGUI game;
    private JTextField textField;
    private Image otherLogo;

    public LoadingScreenPanel(int width, int height, GameGUI gameGUI) {
        this.game = gameGUI;
        this.setPreferredSize(new Dimension(width, height));
        // making this an absolute layout
        this.setLayout(null);
        this.height = height;
        this.width = width;
        this.mainLogo = this.loadImage(mainLogoFilePath);
        this.mainLogo = this.mainLogo.getScaledInstance(400, 400, 0);
        this.textField = new JTextField();
        this.textField.addActionListener(this);
        this.textField.setSize(400, 50);
        this.textField.setLocation(100, 460);
        this.otherLogo = game.getCornerLogo().getScaledInstance(280, 280, 0);
    }

    @Override
    public void paintComponent(final Graphics graphics) {
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.drawRect(0, 0, this.getWidth(), this.getHeight());
        graphics.setColor(Color.BLACK);
        graphics.drawImage(mainLogo, 100, 20, this);
        graphics.setFont(new Font("MonoLisa", Font.PLAIN, 27));
        graphics.drawString("Enter your username below", 140, 430 + 27 / 2);
        this.add(this.textField);
        graphics.drawImage(this.otherLogo, 160, 520, this);
    }


    private Image loadImage(String filename) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    public Image getMainLogo() {
        return mainLogo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.textField) {
            if (this.textField != null) {
                game.setPlayerBasedOnName(this.textField.getText());
                game.showFrameByString(game.getTitleMenu());
            }
        }
    }
}
