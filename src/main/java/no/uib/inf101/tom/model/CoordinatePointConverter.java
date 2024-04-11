package no.uib.inf101.tom.model;

import java.awt.geom.Point2D;

import no.uib.inf101.tom.model.box.ViewableBox;

public class CoordinatePointConverter {
    private Point2D zerozero;
    private ViewableBox player;
    private GameState gameState;

    public CoordinatePointConverter(Point2D zerozero, ViewableBox player, GameState gameState) {
        this.zerozero = zerozero;
        this.player = player;
        this.gameState = gameState;
    }

    
}
