package no.uib.inf101.tom.model;

import java.awt.geom.Point2D;

import no.uib.inf101.tom.Config;
import no.uib.inf101.tom.controller.ControllableModel;
import no.uib.inf101.tom.model.character.Player;
import no.uib.inf101.tom.view.ViewableModel;

public class TomModel implements ViewableModel, ControllableModel{
    private Player player;
    private boolean debug_mode;
    private CoordinatePointConverter coordinateConverter;
    private ObservableGameState gameState;


    public TomModel() {
        //disse initializersne blir erstatta av en slags levelloader
        this.player = new Player(new Coordinate(0, 0), 0, 0);
        this.gameState = new ObservableGameState(GameState.ACTIVE_GAME);

        Point2D screenCenter = new Point2D.Double(
            Config.SWING_COMPONENT_MAX_HEIGHT/2, Config.SWING_COMPONENT_MAX_WIDTH/2);
        this.coordinateConverter = new CoordinatePointConverter(
            screenCenter, new Coordinate(0, 0), player);
        this.gameState.addGameStateListener(this.coordinateConverter::reactToGameState);
        this.debug_mode = true;
    }


    @Override
    public CoordinatePointConverter getCoordinateConverter() {
        return this.coordinateConverter;
    }


}
