package no.uib.inf101.tom.view;

import javax.swing.JPanel;

import no.uib.inf101.tom.Config;
import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.box.CollisionBox;
import no.uib.inf101.tom.model.box.HitBox;
import no.uib.inf101.tom.model.box.ViewableBox;
import no.uib.inf101.tom.model.box.interactions.Interaction;
import no.uib.inf101.tom.model.character.NPC;
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

        drawLevel(g2);
        drawCharacters(g2);
        if (this.model.isDebugging()) {
            drawCollisionBoxes(g2);
            drawInteractionBoxes(g2);
            drawHits(g2);
            setDebugFont(g2);
            drawDebugText(g2);
            drawMouseCoordinates(g2);
        }
    }

////////////
//DEBUGSTUFF
////////////
    private void drawInteractionBoxes(Graphics2D g2) {
        for (ViewableBox interaction : this.model.getInteractions()) {
            drawDebugBox(g2, interaction);
        }
    }

    private void drawCollisionBoxes(Graphics2D g2) {
        for (ViewableBox collision : this.model.getCollisionBoxes()) {
            drawDebugBox(g2, collision);
        }
    }

    private void drawHits(Graphics2D g2) {
        for (ViewableBox hit : this.model.getHitBoxes()) {
            drawDebugBox(g2, hit);
        }
    }

    private void setDebugFont(Graphics2D g2) {
        g2.setPaint(Config.DEBUG_TEXT_COLOR);
        g2.setFont(Config.DEBUG_TEXT_FONT);
    }

    private void drawDebugText(Graphics2D g2) {
        String gameState = this.model.getGameState().toString();
        String levelName = this.model.getLevelName();
        String debugString = String.format("state: %s, level: %s", 
            gameState, levelName);
        Inf101Graphics.drawCenteredString(g2, debugString, this.getWidth()/2, 20);
    }

    private void drawMouseCoordinates(Graphics2D g2) {
        g2.setPaint(Config.DEBUG_TEXT_COLOR);
        Coordinate mouseCoord = this.model.getMousePos();
        Point2D mousePoint = this.model.getCoordinateConverter().pointFromCoordinate(mouseCoord);
        String pos = getPosString(mouseCoord);
        Inf101Graphics.drawCenteredString(g2, pos, mousePoint);
    }

    private void drawDebugBox(Graphics2D g2, ViewableBox box) {
        if (box instanceof Player) {
            FillBoxWithColor(g2, box, Config.PLAYER_DEBUG_COLOR);
        } else if (box instanceof NPC) {
            FillBoxWithColor(g2, box, Config.NPC_DEBUG_COLOR);
        } else if (box instanceof HitBox) {
            FillBoxWithColor(g2, box, Config.HIT_DEBUG_COLOR);
        } else if (box instanceof CollisionBox) {
            FillBoxWithColor(g2, box, Config.COLLISION_DEBUG_COLOR);
        } else if (box instanceof Interaction) {
            FillBoxWithColor(g2, box, Config.INTERACTION_DEBUG_COLOR);
        }
    }

////////////////
//CHARACTERSTUFF
////////////////
    private void drawCharacters(Graphics2D g2) {
        for (ViewableCharacter character : this.model.getCharacters()) {
            drawCharacter(g2, character);
        }
    }

    private void drawCharacter(Graphics2D g2, ViewableCharacter character) {
        //draw the actual sprite
        //draw the debuginfo
        if (this.model.isDebugging()) {
            ViewableBox box = character.getBox();
            //drawing the box
            drawDebugBox(g2, box);
            //drawing the name and commander name and good-value
            setDebugFont(g2);
            Point2D center = this.model.getCoordinateConverter().pointFromCoordinate(
                box.getCenter());
            double x = center.getX();
            double y = center.getY() - Config.MEDIUM_MARGIN;
            String nameString = String.format(
                "%s - %s - %s", 
                box.getName(), character.getCommander(), character.getGoodOrBad());
            Inf101Graphics.drawCenteredString(g2, nameString, x, y);
            //drawing the character data in the box
            String movement = character.getViewableMovementVector();
            String pos = getPosString(box.getCenter());
            String action = character.getViewableAction().getActionName();
            int actionState = character.getViewableAction().getActionState();
            String facing = character.getFacing().toString();
            String characterData = String.format(
                "move: %s, pos: %s, action: %s;%s, face: %s", 
                movement, pos, action, actionState, facing);
            Inf101Graphics.drawCenteredString(
                g2, characterData, 
                box.getShape(this.model.getCoordinateConverter()));
            //drawing the stats under the box
            y += Config.MEDIUM_MARGIN * 2;
            Inf101Graphics.drawCenteredString(g2, character.getStatsString(), x, y);

        }
    }

////////////
//LEVELSTUFF
////////////
    private void drawLevel(Graphics2D g2) {
        String levelName = this.model.getLevelName();
        BufferedImage levelImage = this.imageFinder.findImage(levelName);
        drawImageAtCoords(g2, levelImage, new Coordinate(0, 0));
    }


///////////////
//EXTRA METHODS
///////////////
    private void FillBoxWithColor(Graphics2D g2, ViewableBox box, Color color) {
        g2.setPaint(color);
        g2.fill(box.getShape(this.model.getCoordinateConverter()));
    }

    private void drawImageAtCoords(Graphics2D g2, BufferedImage image, Coordinate coords) {
        Point2D point = model.getCoordinateConverter().pointFromCoordinate(coords);
        if (image != null) {
            Inf101Graphics.drawCenteredImage(g2, image, point.getX(), point.getY(), Config.SCALING);
        } else {
            Inf101Graphics.drawCenteredString(g2, "Image not found", point.getX(), point.getY());
        }
    }

    private String getPosString(Coordinate pos) {
        return String.format("(%.3f, %.3f)", pos.x(), pos.y());
    }



}
