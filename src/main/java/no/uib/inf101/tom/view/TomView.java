package no.uib.inf101.tom.view;

import javax.swing.JPanel;

import no.uib.inf101.tom.Config;
import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.CoordinatePointConverter;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class TomView extends JPanel {

    private ViewableModel model;
    private CoordinatePointConverter converter;
    private ImageFinder imageFinder;


    public TomView(ViewableModel model) {
        this.setBackground(Config.BACKGROUND_COLOR);
        this.setFocusable(true);
        this.setPreferredSize(Config.WINDOW_DIMENSION);
        this.setDoubleBuffered(Config.DOUBLE_BUFFERED);

        this.model = model;
        this.converter = this.model.getCoordinateConverter();

        this.imageFinder = new ImageFinder();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        drawDemoTile(g2);
        drawPlayer(g2);
    }

    private void drawPlayer(Graphics2D g2) {
        //TODO: implement this.
    }

    private void drawDemoTile(Graphics2D g2) {
        BufferedImage tileDemo = this.imageFinder.findImage("example_floor_tile");
        drawImageAtCoords(g2, tileDemo, new Coordinate(0, 0));
    }

    private void drawImageAtCoords(Graphics2D g2, BufferedImage image, Coordinate coords) {
        Point2D point = model.getCoordinateConverter().pointFromCoordinate(coords);
        Inf101Graphics.drawCenteredImage(g2, image, point.getX(), point.getY(), Config.TILE_SCALING);
    }


}
