package no.uib.inf101.tom.model;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import no.uib.inf101.tom.Config;
import no.uib.inf101.tom.controller.ControllableModel;
import no.uib.inf101.tom.gameloop.Updatable;
import no.uib.inf101.tom.model.action.Action;
import no.uib.inf101.tom.model.action.ActionCommand;
import no.uib.inf101.tom.model.action.Walk;
import no.uib.inf101.tom.model.box.Building;
import no.uib.inf101.tom.model.box.CollisionBox;
import no.uib.inf101.tom.model.box.HitBox;
import no.uib.inf101.tom.model.box.ViewableBox;
import no.uib.inf101.tom.model.character.CharacterViewableModel;
import no.uib.inf101.tom.model.character.NPC;
import no.uib.inf101.tom.model.character.Player;
import no.uib.inf101.tom.model.character.ViewableCharacter;
import no.uib.inf101.tom.model.level.Level;
import no.uib.inf101.tom.model.level.LevelLoader;
import no.uib.inf101.tom.view.ViewableModel;

public class TomModel implements ViewableModel, ControllableModel, Updatable, ActionableModel, CharacterViewableModel{
    private LevelLoader levelLoader;
    private String levelName;

    private Player player;
    private ArrayList<NPC> npcList;
    private ArrayList<HitBox> hitList;
    private ArrayList<Building> buildingList;
    private boolean debugMode;
    private CoordinatePointConverter coordinateConverter;
    private ObservableGameState gameState;

    private Coordinate mousePos;


    public TomModel(String startUpState) {
        this.levelLoader = new LevelLoader();
        this.gameState = new ObservableGameState(GameState.ACTIVE_GAME);
        //loads the demo level
        if (startUpState == "demo") {
            this.loadLevel("demo", 1);
        }

        //extra stuff
        this.debugMode = true;
        this.mousePos = new Coordinate(0, 0); //placeholder. Will probably not cause bugs.
        this.hitList = new ArrayList<>();
    }

///////////////
//LEVEL-RELATED
///////////////
    /**
     * load a level onto the model
     * @param levelName the name of the level to load. 
     * @param entrance the number of the entrance that the player enters from (often 1). 
     */
    public void loadLevel(String levelName, int entrance) {
        this.levelName = levelName;
        Level level = this.levelLoader.getLevel(levelName);
        //loads characters
        if (this.player == null) {
            this.player = new Player(level.getEnteredCoordinate(entrance));
        } else {
            this.player.setPos(level.getEnteredCoordinate(entrance));
        }
        this.npcList = level.getNpcs();
        giveAllCharactersActionAccess();
        //loads buildings
        this.buildingList = level.getBuildings();
        //creates coordConverter
        this.coordinateConverter = new CoordinatePointConverter(
            new Coordinate(0, 0), this.player);
        //sets the gamestate
        this.gameState.addGameStateListener(this.coordinateConverter::reactToGameState);
        this.gameState.setGameState(level.getGameState());
    }

    private void giveAllCharactersActionAccess() {
        this.player.setModel(this);
        for (NPC npc : this.npcList) {
            npc.setModel(this);
        }
    }

    private void writeLevel(String levelName) {
        Level level = this.levelLoader.getLevel(levelName);
        level.setPlayer(this.player);
    }

////////////////
//UPDATE-RELATED
////////////////

    @Override
    public void update() {
        if (this.hitList.size() > 10) {
            this.hitList.remove(0);
        }
        this.player.updateCharacter(this);
        for (NPC npc : npcList) {
            npc.updateCharacter(this);
        }
    }

//////////////
//VIEW-RELATED
//////////////

//Getters

    @Override
    public ArrayList<ViewableBox> getHitBoxes() {
        ArrayList<ViewableBox> viewableHitBoxes = new ArrayList<>();
        for (HitBox hit : this.hitList) {
            viewableHitBoxes.add((ViewableBox) hit);
        }
        return viewableHitBoxes;
    }

    @Override
    public ArrayList<CollisionBox> getCollisionBoxesForCharacter() {
        ArrayList<CollisionBox> collisions = new ArrayList<>();
        for (CollisionBox box : this.buildingList) {
            collisions.add(box);
        }
        return collisions;
    }

    @Override
    public Coordinate getPlayerCenter() {
        return this.player.getCenter();
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
        for (ViewableCharacter npc : this.npcList) {
            characters.add(npc);
        }
        characters.add(this.player);
        return characters;
    }

    @Override
    public ViewableCharacter getPlayer() {
        return this.player;
    }

    @Override
    public ArrayList<ViewableBox> getCollisionBoxes() {
        ArrayList<ViewableBox> collisions = new ArrayList<>();
        for (ViewableBox box : this.buildingList) {
            collisions.add(box);
        }
        return collisions;
    }

    @Override
    public boolean isDebugging() {
        return this.debugMode;
    }

    @Override
    public Coordinate getMousePos() {
        return this.mousePos;
    }

////////////////////
//CONTROLLER-RELATED
////////////////////

    @Override
    public void mouseIsAt(Point2D point) {
        this.mousePos = this.coordinateConverter.coordinateFromPoint(point);
    }

    //Controlls
    @Override
    public void walk(Point2D point) {
        Coordinate clickedCoordinate = this.coordinateConverter.coordinateFromPoint(point);
        player.sendActionCommand(new ActionCommand(new Walk(), clickedCoordinate));
    }

    @Override
    public void sendAction(Action action) {
        player.sendActionCommand(new ActionCommand(action, this.mousePos));
    }

    @Override
    public void toggleDebug() {
        this.debugMode = !this.debugMode;
    }

    
///////////////
//EXTRA METHODS
///////////////
    @Override
    public void hitCharactersInBox(HitBox hit, boolean actorIsGood, int strength) {
        this.hitList.add(hit);
        if (this.player.overlapsWith(hit)) {
            this.player.takeHit(actorIsGood, strength);
        }
        for (int i = this.npcList.size() - 1; i >= 0; i--) {
            NPC npc = this.npcList.get(i);
            if (npc.overlapsWith(hit)) {
                if (npc.takeHit(actorIsGood, strength)) {
                    //if the npc dies i remove it from the npcList
                    //(this is why i iterate backwards)
                    this.npcList.remove(i);
                }
            }      
        }
    }
    

}
