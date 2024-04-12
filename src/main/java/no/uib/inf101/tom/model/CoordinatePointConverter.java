package no.uib.inf101.tom.model;

import java.awt.geom.Point2D;

import no.uib.inf101.tom.model.box.ViewableBox;

public class CoordinatePointConverter {
    private Coordinate coordAtCenter;
    private final Point2D screenCenter;
    private ViewableBox player;

    public CoordinatePointConverter(
        Point2D screenCenter, Coordinate coordAtCenter, 
        ViewableBox player) {

        this.screenCenter = screenCenter;
        this.coordAtCenter = coordAtCenter;
        this.player = player;
    }

    public void reactToGameState(GameState newGameState) {
        if (newGameState.equals(GameState.ACTIVE_GAME)) {
            this.coordAtCenter = player.getCenter();
        }
    }

    public Point2D pointFromCoordinate(Coordinate coord) {
        //TODO: implement this when i have figured out the dimensions of the app. 
        return null;
    }



    
}
