package no.uib.inf101.tom.model;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import no.uib.inf101.tom.Config;
import no.uib.inf101.tom.controller.ControllableModel;
import no.uib.inf101.tom.gameloop.Updatable;
import no.uib.inf101.tom.model.action.Walk;
import no.uib.inf101.tom.model.character.NPC;
import no.uib.inf101.tom.model.character.Player;
import no.uib.inf101.tom.model.character.ViewableCharacter;
import no.uib.inf101.tom.model.level.Level;
import no.uib.inf101.tom.model.level.LevelLoader;
import no.uib.inf101.tom.view.ViewableModel;

public class TomModel implements ViewableModel, ControllableModel, Updatable{
    private LevelLoader levelLoader;
    private String levelName;

    private Player player;
    private ArrayList<NPC> npcList;
    private boolean debugMode;
    private CoordinatePointConverter coordinateConverter;
    private ObservableGameState gameState;

    private Coordinate mousePos;


    public TomModel() {
        //loads the demo level
        this.levelLoader = new LevelLoader();
        this.loadLevel("demo");

        //makes the active game
        this.gameState = new ObservableGameState(GameState.ACTIVE_GAME);
        Point2D screenCenter = new Point2D.Double(
            Config.SWING_COMPONENT_MAX_WIDTH/2, Config.SWING_COMPONENT_MAX_HEIGHT/2);
        this.coordinateConverter = new CoordinatePointConverter(
            screenCenter, new Coordinate(0, 0), player);
        this.gameState.addGameStateListener(this.coordinateConverter::reactToGameState);
        this.gameState.setGameState(GameState.ACTIVE_GAME);

        //extra stuff
        this.debugMode = true;
        this.mousePos = new Coordinate(0, 0); //placeholder. Will probably not cause bugs.
    }

    private void loadLevel(String levelName) {
        this.levelName = levelName;
        Level level = this.levelLoader.loadLevel(levelName);
        if (this.player == null) {
            this.player = new Player(level.getPlayer().getBox().getCenter());
        } else {
            this.player.setPos(level.getPlayer().getBox().getCenter());
        }
        this.npcList = level.getNpcs();

    }

    private void writeLevel(String levelName) {
        Level level = this.levelLoader.loadLevel(levelName);
        level.setPlayer(this.player);
    }

    @Override
    public void update() {
        this.player.updateCharacter();
        for (NPC npc : npcList) {
            npc.updateCharacter();
        }
    }


    @Override
    public CoordinatePointConverter getCoordinateConverter() {
        return this.coordinateConverter;
    }

    @Override
    public GameState getGameState() {
        return this.gameState.getGameState();
    }

    @Override
    public String getLevelName() {
        return this.levelName;
    }

    @Override
    public ArrayList<ViewableCharacter> getCharacters() {
        ArrayList<ViewableCharacter> characters = new ArrayList<>();
        characters.add(this.player);
        for (ViewableCharacter npc : this.npcList) {
            characters.add(npc);
        }
        return characters;
    }

    @Override
    public boolean isDebugging() {
        return this.debugMode;
    }

    @Override
    public void walk(Point2D point) {
        Coordinate clickedCoordinate = this.coordinateConverter.coordinateFromPoint(point);
        player.sendActionCommand(new Walk(), clickedCoordinate);
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
