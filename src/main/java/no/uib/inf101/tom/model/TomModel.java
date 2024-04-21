package no.uib.inf101.tom.model;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import no.uib.inf101.tom.controller.ControllableModel;
import no.uib.inf101.tom.gameloop.Updatable;
import no.uib.inf101.tom.model.action.Action;
import no.uib.inf101.tom.model.action.ActionCommand;
import no.uib.inf101.tom.model.action.Walk;
import no.uib.inf101.tom.model.box.Building;
import no.uib.inf101.tom.model.box.CollisionBox;
import no.uib.inf101.tom.model.box.HitBox;
import no.uib.inf101.tom.model.box.ViewableBox;
import no.uib.inf101.tom.model.box.interactions.Interaction;
import no.uib.inf101.tom.model.character.CharacterViewableModel;
import no.uib.inf101.tom.model.character.NPC;
import no.uib.inf101.tom.model.character.Player;
import no.uib.inf101.tom.model.character.ViewableCharacter;
import no.uib.inf101.tom.model.level.Level;
import no.uib.inf101.tom.model.level.LevelLoader;
import no.uib.inf101.tom.model.screen.Button;
import no.uib.inf101.tom.model.screen.Screen;
import no.uib.inf101.tom.model.screen.ScreenLoader;
import no.uib.inf101.tom.view.ViewableModel;

public class TomModel implements ViewableModel, ControllableModel, Updatable, ActionableModel, CharacterViewableModel{
    private LevelLoader levelLoader;
    private String levelName;

    private ScreenLoader screenLoader;
    private Screen screen;

    private Player player;
    private ArrayList<NPC> npcList;
    private ArrayList<HitBox> hitList;
    private ArrayList<Building> buildingList;
    private ArrayList<Interaction> interactionList;
    private boolean debugMode;
    private CoordinatePointConverter coordinateConverter;
    private ObservableGameState gameState;    

    private String lastLevelLoaded;
    private int lastEntrance;

    private Coordinate mousePos;


    public TomModel(String startUpState) {
        //placeholder values. Will probably not cause bugs.
        this.hitList = new ArrayList<>();
        this.buildingList = new ArrayList<>();
        this.interactionList = new ArrayList<>();
        this.npcList = new ArrayList<>();
        this.mousePos = new Coordinate(0, 0);
        this.player = new Player(mousePos);
        this.coordinateConverter = new CoordinatePointConverter(
            new Coordinate(0, 0), this.player);
        
        //Initial values
        this.debugMode = true;
        this.levelLoader = new LevelLoader();
        this.screenLoader = new ScreenLoader(this);
        this.gameState = new ObservableGameState(GameState.ACTIVE_GAME);
        this.gameState.addGameStateListener(this::reactToStateChange);

        //Starts up
        if (startUpState == "demo") {
            this.loadLevel("demo", 1);
        } else if (startUpState == "main") {
            this.gameState.setGameState(GameState.MAIN_MENU);
            this.debugMode = false;
        }

    }

//////////////////////////
//LEVEL-RELATED and SCREEN
//////////////////////////
    public void loadScreen(String screenName) {
        this.coordinateConverter = new CoordinatePointConverter(new Coordinate(0, 0), this.player);
        this.coordinateConverter.stopFollowingCharacter();
        this.screen = this.screenLoader.getScreen(screenName);
    }

    /**
     * load a level onto the model
     * @param levelName the name of the level to load. 
     * @param entrance the number of the entrance that the player enters from (often 1). 
     */
    public void loadLevel(String levelName, int entrance) {
        this.lastLevelLoaded = levelName;
        this.lastEntrance = entrance;
        writeLevel(this.levelName);
        this.levelName = levelName;
        Level level = this.levelLoader.getLevel(levelName);
        //loads characters
        if (this.player == null) {
            this.player = new Player(level.getEnteredCoordinate(entrance));
        } else {
            this.player.setPos(level.getEnteredCoordinate(entrance));
        }
        this.player.setDestination(this.player.getCenter());
        this.npcList = level.getNpcs();
        giveAllCharactersActionAccess();
        //loads buildings
        this.buildingList = level.getBuildings();
        //loads interactions
        this.interactionList = level.getInteractions();
        for (Interaction interaction : this.interactionList) {
            interaction.setModel(this);
        }
        //creates coordConverter
        this.coordinateConverter = new CoordinatePointConverter(
            new Coordinate(0, 0), this.player);
        //sets the gamestate
        this.gameState.setGameState(level.getGameState());
    }

    private void giveAllCharactersActionAccess() {
        this.player.setModel(this);
        for (NPC npc : this.npcList) {
            npc.setModel(this);
        }
    }

    private void writeLevel(String levelName) {
        if (levelName != null) {
            //clears the hitList
            this.hitList.clear();
            Level level = this.levelLoader.getLevel(levelName);
            //writes player information
            level.setPlayer(this.player);
            //we dont have to write npcList, buildingList or interactionlist 
            //because those lists are the same instances as the ones in the model. 
        }
    }


////////////////
//UPDATE-RELATED
////////////////

    @Override
    public void update() {
        if (this.gameState.getGameState() == GameState.ACTIVE_GAME) {
            if (this.hitList.size() > 10) {
                this.hitList.remove(0);
            }
            this.player.updateCharacter(this);
            for (NPC npc : npcList) {
                npc.updateCharacter(this);
            }
        }
    }

    public void healPlayer() {
        this.player.setHealthToFull();
    }

//////////////
//VIEW-RELATED
//////////////

//Getters
    @Override
    public Screen getScreen() {
        return this.screen;
    }

    public String getLastLevelLoaded() {
        return lastLevelLoaded;
    }

    public int getLastEntrance() {
        return lastEntrance;
    }

    @Override
    public ArrayList<ViewableBox> getInteractions() {
        ArrayList<ViewableBox> viewableInteractions = new ArrayList<>();
        for (ViewableBox interaction : this.interactionList) {
            viewableInteractions.add(interaction);
        }
        return viewableInteractions;
    }

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
    public void pause() {
        if (getGameState() == GameState.ACTIVE_GAME) {
            this.gameState.setGameState(GameState.PAUSED_GAME);
        } else if (getGameState() == GameState.PAUSED_GAME) {
            this.gameState.setGameState(GameState.ACTIVE_GAME);
        }
    }

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
    public void interact(Point2D point) {
        Coordinate click = this.coordinateConverter.coordinateFromPoint(point);
        if (this.gameState.getGameState() == GameState.ACTIVE_GAME) {
            for (Interaction interaction : this.interactionList) {
                interaction.checkIfClicked(click);
            }
        } else if (this.gameState.getGameState() == GameState.MAIN_MENU ||
        this.gameState.getGameState() == GameState.PAUSED_GAME ||
        this.gameState.getGameState() == GameState.GAME_OVER) {
            for (Button button : this.screen.getButtons()) {
                button.checkIfClicked(click);
            }
        }
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
            if (this.player.takeHit(actorIsGood, strength)) {
                this.gameState.setGameState(GameState.GAME_OVER);
            }
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

    public void reactToStateChange(GameState newState) {
        if (newState == GameState.MAIN_MENU) {
            loadScreen("mainmenu");
        } else if (newState == GameState.GAME_OVER) {
            this.levelLoader = new LevelLoader();
            this.levelName = null;
            loadScreen("gameover");
        } else if (newState == GameState.PAUSED_GAME) {
            loadScreen("pause");
        } else if (newState == GameState.ACTIVE_GAME) {
            this.coordinateConverter.setPlayerInCenter();
        }
    }
    
    public void setGameState(GameState nextState) {
        this.gameState.setGameState(nextState);
    }

}
