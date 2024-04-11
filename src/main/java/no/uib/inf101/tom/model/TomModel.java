package no.uib.inf101.tom.model;

import java.awt.geom.Point2D;

import no.uib.inf101.tom.controller.ControllableModel;
import no.uib.inf101.tom.model.character.Player;
import no.uib.inf101.tom.view.ViewableModel;

public class TomModel implements ViewableModel, ControllableModel{
    private Player player;
    private boolean debug_mode;
    private CoordinatePointConverter coordinateConverter;
    private GameState gameState;


    public TomModel() {
        this.player = new Player(new Coordinate(0, 0), 0, 0);
        this.gameState = GameState.ACTIVE_GAME;

        Point2D screenCenter = new Point2D.Double(1920/2, 1080/2);
        this.coordinateConverter = new CoordinatePointConverter(screenCenter, player, gameState);
        
        this.debug_mode = true;
    }

    
    

}
