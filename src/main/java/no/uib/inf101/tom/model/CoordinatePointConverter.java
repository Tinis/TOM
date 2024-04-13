package no.uib.inf101.tom.model;

import java.awt.geom.Point2D;

import no.uib.inf101.tom.Config;
import no.uib.inf101.tom.model.box.ViewableBox;

public class CoordinatePointConverter {
    private Coordinate coordAtCenter;
    private final Point2D screenCenter;
    private ViewableBox player;
    private ViewableBox currentlyFollowedCharacter;
    private boolean isFollowingCharacter;

    public CoordinatePointConverter(
        Point2D screenCenter, Coordinate coordAtCenter, 
        ViewableBox player) {

        this.screenCenter = screenCenter;
        this.coordAtCenter = coordAtCenter;
        this.player = player;
        this.currentlyFollowedCharacter = player;
        this.isFollowingCharacter = true;
    }

    public void reactToGameState(GameState newGameState) {
        if (newGameState.equals(GameState.ACTIVE_GAME)) {
            setPlayerInCenter();
        }
    }

    public void setPlayerInCenter() {
        this.currentlyFollowedCharacter = this.player;
        this.isFollowingCharacter = true;
    }

    private void updateCenter() {
        if (this.isFollowingCharacter) {
            this.coordAtCenter = this.currentlyFollowedCharacter.getCenter();
        }
    }

    public Point2D pointFromCoordinate(Coordinate coord) {
        updateCenter();
        double xCoordDiff = coord.x() - this.coordAtCenter.x();
        double yCoordDiff = coord.y() - this.coordAtCenter.y();
        double xPointDiff = xCoordDiff * Config.SCALING;
        double yPointDiff = yCoordDiff * Config.SCALING;
        double newX = this.screenCenter.getX() + xPointDiff;
        double newY = this.screenCenter.getY() + yPointDiff;
        return new Point2D.Double(newX, newY);
    }

    public Coordinate coordinateFromPoint(Point2D point) {
        updateCenter();
        double xPointDiff = point.getX() - this.screenCenter.getX();
        double yPointDiff = point.getY() - this.screenCenter.getY();
        double xCoordDiff = xPointDiff / Config.SCALING;
        double yCoordDiff = yPointDiff / Config.SCALING;
        double newX = this.coordAtCenter.x() + xCoordDiff;
        double newY = this.coordAtCenter.y() + yCoordDiff;
        return new Coordinate(newX, newY);
    }    
}
