package no.uib.inf101.tom.model;

import java.awt.geom.Point2D;

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

    public Point2D pointFromCoordinate(Coordinate coord) {
        double xDiff = coord.x() - this.coordAtCenter.x();
        double yDiff = coord.y() - this.coordAtCenter.y();
        double newX = this.screenCenter.getX() + xDiff;
        double newY = this.screenCenter.getY() + yDiff;
        return new Point2D.Double(newX, newY);
    }

    public Coordinate coordinateFromPoint(Point2D point) {
        double xDiff = point.getX() - this.screenCenter.getX();
        double yDiff = point.getY() - this.screenCenter.getY();
        double newX = this.coordAtCenter.x() + xDiff;
        double newY = this.coordAtCenter.y() + yDiff;
        return new Coordinate(newX, newY);
    }    
}
