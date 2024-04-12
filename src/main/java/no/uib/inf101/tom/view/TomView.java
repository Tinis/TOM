package no.uib.inf101.tom.view;

import javax.swing.JPanel;

import no.uib.inf101.tom.Config;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class TomView extends JPanel {

    private ViewableModel model;
    private ImageFinder imageFinder;

    public TomView(ViewableModel model) {
        this.setBackground(Config.BACKGROUND_COLOR);
        this.setFocusable(true);
        this.setPreferredSize(Config.WINDOW_DIMENSION);
        this.setDoubleBuffered(Config.DOUBLE_BUFFERED);

        this.model = model;
        this.imageFinder = new ImageFinder();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        drawDemoWorld(g2);
    }

    private void drawDemoWorld(Graphics2D g2) {
        BufferedImage tileDemo = this.imageFinder.findImage("example_floor_tile");
        
    }


}
