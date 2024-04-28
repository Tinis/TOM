package no.uib.inf101.tom.model;

import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;

import no.uib.inf101.tom.Config;
import no.uib.inf101.tom.controller.ControllableModel;
import no.uib.inf101.tom.gameloop.Updatable;
import no.uib.inf101.tom.model.action.Action;
import no.uib.inf101.tom.model.action.ActionCommand;
import no.uib.inf101.tom.model.action.Walk;
import no.uib.inf101.tom.model.box.CollisionBox;
import no.uib.inf101.tom.model.box.HitBox;
import no.uib.inf101.tom.model.box.Projectile;
import no.uib.inf101.tom.model.box.ViewableBox;
import no.uib.inf101.tom.model.box.interactions.Interaction;
import no.uib.inf101.tom.model.character.CharacterViewableModel;
import no.uib.inf101.tom.model.character.NPC;
import no.uib.inf101.tom.model.character.Player;
import no.uib.inf101.tom.model.character.ViewableCharacter;
import no.uib.inf101.tom.model.cutscene.Cutscene;
import no.uib.inf101.tom.model.cutscene.CutsceneLoader;
import no.uib.inf101.tom.model.level.Level;
import no.uib.inf101.tom.model.level.LevelLoader;
import no.uib.inf101.tom.model.screen.Button;
import no.uib.inf101.tom.model.screen.Screen;
import no.uib.inf101.tom.model.screen.ScreenLoader;
import no.uib.inf101.tom.view.ViewableModel;

public class TomModel implements ViewableModel, ControllableModel, Updatable, ActionableModel, CharacterViewableModel{
    private LevelLoader levelLoader;
    private String levelName;
    private int levelStateAmount;
    private int levelState;
    private int levelFramesPerState;
    private int levelFrameCount;

    private CutsceneLoader cutsceneLoader;
    private Cutscene cutscene;

    private ArrayList<String> activeSounds;

    private ScreenLoader screenLoader;
    private Screen screen;

    private Player player;
    private ArrayList<NPC> npcList;
    private ArrayList<HitBox> hitList;
    private ArrayList<HitBox> hitsThisFrame;
    private ArrayList<Projectile> projectileList;
    private ArrayList<CollisionBox> buildingList;
    private ArrayList<Interaction> interactionList;
    private boolean debugMode;
    private CoordinatePointConverter coordinateConverter;
    private ObservableGameState gameState;    

    private String lastLevelLoaded;
    private int lastEntrance;

    private Coordinate mousePos;


    public TomModel(String startUpState) {
        //initializing lists
        this.hitList = new ArrayList<>();
        this.hitsThisFrame = new ArrayList<>();
        this.projectileList = new ArrayList<>();
        this.buildingList = new ArrayList<>();
        this.interactionList = new ArrayList<>();
        this.npcList = new ArrayList<>();
        this.activeSounds = new ArrayList<>();

        //placeholder values. Will probably not cause bugs.
        this.mousePos = new Coordinate(0, 0);
        this.player = new Player(new Coordinate(0, 0));
        this.coordinateConverter = new CoordinatePointConverter(
            new Coordinate(0, 0), this.player);
        
        //Initial values
        this.debugMode = true;
        this.levelLoader = new LevelLoader();
        this.screenLoader = new ScreenLoader(this);
        this.cutsceneLoader = new CutsceneLoader(this);
        this.gameState = new ObservableGameState(GameState.ACTIVE_GAME);
        this.gameState.addGameStateListener(this::reactToStateChange);

        //Starts up
        if (startUpState == "demo") {
            System.out.println("loading demo");
            this.loadLevel("demo", 1);
            this.player.setCanFire(true);
        } else if (startUpState == "city1") {
            System.out.println("loading city1");
            this.loadLevel("city1", 1);
        } else if (startUpState == "happyapartment1") {
            System.out.println("loading happyapartment1");
            this.loadLevel("happyapartment1", 1);
        } else if (startUpState == "nightclub1") {
            System.out.println("loading nightclub1");
            this.loadLevel("nightclub1", 1);
        } else if (startUpState == "main") {
            this.gameState.setGameState(GameState.MAIN_MENU);
            this.debugMode = false;
        }

    }

//////////////////////////
//LOADING-RELATED
//////////////////////////
    public void loadCutscene(String cutsceneName) {
        this.activeSounds.clear();
        this.gameState.setGameState(GameState.CUT_SCENE);
        this.cutscene = this.cutsceneLoader.getCutscene(cutsceneName);
        if (this.cutscene.getSongName() != null) {
            this.activeSounds.add(this.cutscene.getSongName());
        }
    }

    public void loadScreen(String screenName) {
        this.activeSounds.clear();
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
        //prepares to load new level
        this.projectileList.clear();
        this.activeSounds.clear();
        this.lastLevelLoaded = levelName;
        this.lastEntrance = entrance;
        writeLevel(this.levelName);

        //finds how many levelstates the level has
        this.levelStateAmount = 0;
        for (String filename : new File("src/main/resources/levels").list()) {
            int underScoreIndex = filename.indexOf("_");
            String fileLevelName = filename.substring(0, underScoreIndex);
            if (fileLevelName.equals(levelName)) {
                this.levelStateAmount ++;
            }
        }
        this.levelState = 1;
        //finds the frames per state the level has
        this.levelFramesPerState = findLevelFramesPerState(levelName);
        this.levelFrameCount = 0;

        //loads the new level
        this.levelName = levelName;
        Level level = this.levelLoader.getLevel(levelName);
        System.out.println("loading level: " + levelName + ". With song: " + level.getSongName());
        //loads the song
        if (level.getSongName() != null) {
            this.activeSounds.add(level.getSongName());
        }
        //loads characters
        if (this.player == null) {
            this.player = new Player(level.getPlayer().getBox().getCenter());
        } else {
            this.player.setPos(level.getEnteredCoordinate(entrance));
        }
        this.player.setDestination(this.player.getCenter());
        if (levelName.equals("city2")) {
            this.player.setCanFire(true);
        }
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

    private int findLevelFramesPerState(String levelName) {
        if (levelName.equals("livingroom1")) {
            return 60;
        } else if (levelName.equals("happyapartment1")) {
            return 30;
        }
        return Config.STANDARD_LEVEL_FRAMES_PER_STATE;

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
            //update the hitlists
            if (this.hitList.size() > 10) {
                this.hitList.remove(0);
            }
            this.hitsThisFrame.clear();
            //update the projectile list
            for (int i = this.projectileList.size() - 1; i >= 0; i--) {
                Projectile projectile = this.projectileList.get(i);
                if (projectile.updateProjectile()) {
                    this.projectileList.remove(i);
                }
            }
            //update the characters
            this.player.updateCharacter(this);
            for (NPC npc : npcList) {
                npc.updateCharacter(this);
            }
            //update the levelstate
            updateLevelFrameCount();
        } else if (this.gameState.getGameState() == GameState.CUT_SCENE) {
            this.cutscene.updateFrameCount();
        }
    }

    private void updateLevelFrameCount() {
        this.levelFrameCount ++;
        if (this.levelFrameCount > this.levelFramesPerState) {
            this.levelFrameCount = 0;
            this.levelState ++;
            if (this.levelState > this.levelStateAmount) {
                this.levelState = 1;
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
    public ArrayList<Projectile> getProjectiles() {
        return this.projectileList;
    }
    @Override
    public ArrayList<String> getActiveSounds() {
        return this.activeSounds;
    }
    @Override
    public Cutscene getCutscene() {
        return this.cutscene;
    }
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
    public ArrayList<ViewableBox> getHitBoxesThisFrame() {
        ArrayList<ViewableBox> viewableHitsThisFrame = new ArrayList<>();
        for (HitBox hit : this.hitsThisFrame) {
            viewableHitsThisFrame.add((ViewableBox) hit);
        }
        return viewableHitsThisFrame;
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
        return this.levelName + "_" + this.levelState;
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
        if (getGameState() == GameState.CUT_SCENE) {
            this.cutscene.skip();
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
    /**
     * this method checks if there is something that is supposed to be happening after an npc dies.
     */
    private void checkDeathTriggers(NPC deadNPC) {
        if (this.levelName.equals("happyapartment1")) {
            if (this.npcList.size() == 0) {
                this.loadCutscene("objective2");
                this.player.setCanFire(true);
            }
        }
        if (this.levelName.equals("nightclub1")) {
            if (deadNPC.getName().equals("nightclubboss")) {
                this.loadCutscene("objective3");
            }
        }
    }

    @Override
    public void addProjectile(Projectile projectile) {
        this.projectileList.add(projectile);
    }

    @Override
    public void hitCharactersInBox(HitBox hit, boolean actorIsGood, int strength) {
        this.hitList.add(hit);
        this.hitsThisFrame.add(hit);
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
                    checkDeathTriggers(this.npcList.get(i));
                    this.npcList.remove(i);
                }
            }      
        }
    }

    public void reactToStateChange(GameState newState) {
        if (newState == GameState.MAIN_MENU) {
            this.levelLoader = new LevelLoader();
            this.levelName = null;
            this.player = null;
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
