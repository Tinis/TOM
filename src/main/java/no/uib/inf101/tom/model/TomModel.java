package no.uib.inf101.tom.model;

import java.awt.geom.Point2D;

import no.uib.inf101.tom.Config;
import no.uib.inf101.tom.controller.ControllableModel;
import no.uib.inf101.tom.gameloop.Updatable;
import no.uib.inf101.tom.model.action.Walk;
import no.uib.inf101.tom.model.character.Player;
import no.uib.inf101.tom.model.character.ViewableCharacter;
import no.uib.inf101.tom.view.ViewableModel;

public class TomModel implements ViewableModel, ControllableModel, Updatable{
    private Player player;
    private boolean debugMode;
    private CoordinatePointConverter coordinateConverter;
    private ObservableGameState gameState;

    private Coordinate mousePos;


    public TomModel() {
        //disse initializersne blir erstatta av en slags levelloader
        this.player = new Player(new Coordinate(0, 0));
        this.gameState = new ObservableGameState(GameState.ACTIVE_GAME);

        Point2D screenCenter = new Point2D.Double(
            Config.SWING_COMPONENT_MAX_WIDTH/2, Config.SWING_COMPONENT_MAX_HEIGHT/2);
        this.coordinateConverter = new CoordinatePointConverter(
            screenCenter, new Coordinate(0, 0), player);
        this.gameState.addGameStateListener(this.coordinateConverter::reactToGameState);
        this.gameState.setGameState(GameState.ACTIVE_GAME);

        this.debugMode = true;
        this.mousePos = new Coordinate(0, 0);
    }

    @Override
    public void update() {
        this.player.updateCharacter();
    }


    @Override
    public CoordinatePointConverter getCoordinateConverter() {
        return this.coordinateConverter;
    }

    @Override
    public ViewableCharacter getPlayer() {
        return this.player;
    }

    @Override
    public boolean isDebugging() {
        return this.debugMode;
    }

    @Override
    public void walk(Point2D point) {
        Coordinate clickedCoordinate = this.coordinateConverter.coordinateFromPoint(point);
        player.setDestination(clickedCoordinate);
        player.startAction(new Walk());
    }

    @Override
    public void toggleDebug() {
        this.debugMode = !this.debugMode;
    }

    @Override
    public void mouseIsAt(Point2D point) {
        this.mousePos = this.coordinateConverter.coordinateFromPoint(point);
    }

    @Override
    public Coordinate getMousePos() {
        return this.mousePos;
    }


    
    


}
