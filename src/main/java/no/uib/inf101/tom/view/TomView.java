package no.uib.inf101.tom.view;

import javax.swing.JPanel;

import no.uib.inf101.tom.Config;
import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.PlaneVector;
import no.uib.inf101.tom.model.box.ViewableBox;
import no.uib.inf101.tom.model.character.Player;
import no.uib.inf101.tom.model.character.ViewableCharacter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
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

        drawDemoTile(g2);
        drawPlayer(g2);
        if (this.model.isDebugging()) {
            drawMouseCoordinates(g2);
        }
    }

    private void drawMouseCoordinates(Graphics2D g2) {
        Coordinate mouseCoord = this.model.getMousePos();
        Point2D mousePoint = this.model.getCoordinateConverter().pointFromCoordinate(mouseCoord);
        String pos = getPosString(mouseCoord);
        Inf101Graphics.drawCenteredString(g2, pos, mousePoint);
    }

    private void drawPlayer(Graphics2D g2) {
        drawCharacter(g2, this.model.getPlayer());
    }

    private void drawCharacter(Graphics2D g2, ViewableCharacter character) {
        if (this.model.isDebugging()) {
            //drawing the box
            drawDebugBox(g2, character.getBox());
            //drawing the character data in the box
            g2.setPaint(Config.DEBUG_TEXT_COLOR);
            String movement = character.getViewableMovementVector();
            String pos = getPosString(character.getBox().getCenter());
            String characterData = String.format("move: %s, pos: %s", 
                movement, pos);
            Inf101Graphics.drawCenteredString(
                g2, characterData, 
                character.getBox().getShape(this.model.getCoordinateConverter()));
        }
    }

    private void drawDebugBox(Graphics2D g2, ViewableBox box) {
        g2.setPaint(Config.DEBUG_TEXT_COLOR);
        Point2D center = this.model.getCoordinateConverter().pointFromCoordinate(box.getCenter());
        double x = center.getX();
        double y = center.getY() - Config.MEDIUM_MARGIN;
        Inf101Graphics.drawCenteredString(g2, box.getName(), x, y);

        if (box instanceof Player) {
            FillBoxWithColor(g2, box, Config.PLAYER_DEBUG_COLOR);
        }
    }

    private String getPosString(Coordinate pos) {
        return String.format("(%.3f, %.3f)", pos.x(), pos.y());
    }

    private void drawDemoTile(Graphics2D g2) {
        BufferedImage tileDemo = this.imageFinder.findImage("example_floor_tile");
        drawImageAtCoords(g2, tileDemo, new Coordinate(0, 0));
    }

    private void FillBoxWithColor(Graphics2D g2, ViewableBox box, Color color) {
        g2.setPaint(color);
        g2.fill(box.getShape(this.model.getCoordinateConverter()));
    }

    private void drawImageAtCoords(Graphics2D g2, BufferedImage image, Coordinate coords) {
        Point2D point = model.getCoordinateConverter().pointFromCoordinate(coords);
        Inf101Graphics.drawCenteredImage(g2, image, point.getX(), point.getY(), Config.SCALING);
    }


}
