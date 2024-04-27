package no.uib.inf101.tom.model.level;

import java.util.ArrayList;
import java.util.HashMap;

import no.uib.inf101.tom.Config;
import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.GameState;
import no.uib.inf101.tom.model.box.Building;
import no.uib.inf101.tom.model.box.CollisionBox;
import no.uib.inf101.tom.model.box.Wall;
import no.uib.inf101.tom.model.box.interactions.CutsceneClick;
import no.uib.inf101.tom.model.box.interactions.Door;
import no.uib.inf101.tom.model.box.interactions.Interaction;
import no.uib.inf101.tom.model.character.NPC;
import no.uib.inf101.tom.model.character.Player;
import no.uib.inf101.tom.model.character.ViewableCharacter;
import no.uib.inf101.tom.model.character.ai.BasicPunchingAI;
import no.uib.inf101.tom.model.character.ai.PlayerShootingAI;

public class Level {
    private String name;
    private String songName;

    private Player player;
    private ArrayList<NPC> npcList;
    private ArrayList<CollisionBox> buildingList;
    private ArrayList<Interaction> interactionList;
    private HashMap<Integer, Coordinate> enctrances;
    private GameState state;


    public Level(String name, Player player, Coordinate enctrance1, GameState initialState) {
        this.name = name;

        this.songName = null; //this is changed in another constructor if you give a song as an argument
        this.state = initialState;
        this.player = player;
        this.npcList = new ArrayList<>();
        this.buildingList = new ArrayList<>();
        this.interactionList = new ArrayList<>();
        this.enctrances = new HashMap<>();
        this.enctrances.put(1, enctrance1);
    }

    public Level(String name, Player player, Coordinate enctrance1) {
        this(name, player, enctrance1, GameState.ACTIVE_GAME);
    }

    public Level(String name, Player player) {
        this(name, player, new Coordinate(0, 0));
    }

    public Level(String name, Coordinate entrance1) {
        this(name, new Player(entrance1), entrance1);
    }

    public Level(String name, Coordinate entrance1, String songName) {
        this(name, entrance1);
        this.songName = songName;
    }

/////////
//GETTERS
/////////
    public String getSongName() {
        return this.songName;
    }

    public ViewableCharacter getPlayer() {
        return this.player;
    }

    public Coordinate getEnteredCoordinate(int entrance) {
        return this.enctrances.get(entrance);
    }

    public GameState getGameState() {
        return this.state;
    }

    /**
     * 
     * @return the list from this level that contains the npcs. 
     * (Meaning if it is changed the list in the level object also changes)
     */
    public ArrayList<NPC> getNpcs() {
        return this.npcList;
    }

    /**
     * 
     * @return the list of buildings for this level. 
     */
    public ArrayList<CollisionBox> getBuildings() {
        return this.buildingList;
    }

    public ArrayList<Interaction> getInteractions() {
        return this.interactionList;
    }


////////////////////////
//PUT THINGS ON THE LEVEL
////////////////////////
    //CHARACTERS
    public void setPlayer(Player player) {
        this.player = player;
    }

    public void putNPC(NPC npc) {
        this.npcList.add(npc);
    }

    public void putBully(Coordinate pos) {
        NPC bully = new NPC(pos, new BasicPunchingAI(), false, "bully");
        this.npcList.add(bully);
    }

    public void putNightclubGuard(Coordinate pos) {
        NPC nightclubGuard = new NPC(pos, new BasicPunchingAI(), false, "nightclubguard");
        putNPC(nightclubGuard);
    }

    public void putNightclubShooter(Coordinate pos) {
        NPC nightclubShooter = new NPC(pos, new PlayerShootingAI(), false, "nightclubguard");
        putNPC(nightclubShooter);
    }

    public void putNightclubBoss(Coordinate pos) {
        //NPC nightclubBocc = new NPC(pos, new ) //Basic shooting ai
    }

    //COLLISIONBOXES
    public void putBuilding(Building building) {
        this.buildingList.add(building);
    }

    public void putBlock(Coordinate topLeft) {
        //127.5 width, 287.5 height
        Building block = new Building(topLeft, 127.5, 287.5);
        this.buildingList.add(block);
    }
    
    /**
     * puts four walls around this rectangle, in the buildlingslist of the level. 
     * this will ensure that the player cannot walk out of bounds. 
     * @param topLeft
     * @param bottomRight
     */
    public void putBoundsRectangle(Coordinate topLeft, Coordinate bottomRight) {
        Coordinate topRight = new Coordinate(bottomRight.x(), topLeft.y());
        Coordinate bottomLeft = new Coordinate(topLeft.x(), bottomRight.y());
        Wall topWall = new Wall(topLeft, topRight);
        Wall rightWall = new Wall(topRight, bottomRight);
        Wall bottomWall = new Wall(bottomRight, bottomLeft);
        Wall leftWall = new Wall(bottomLeft, topLeft);
        this.buildingList.add(topWall);
        this.buildingList.add(rightWall);
        this.buildingList.add(bottomWall);
        this.buildingList.add(leftWall);
    }

    /**
     * puts four walls around this rectangle, in the buildlingslist of the level. 
     * this will ensure that the player cannot walk out of bounds. 
     * If you only give this methods one coordinate, 
     * the method assumes that the bottomright is at the opposite point from the center.
     * @param topLeft
     */
    public void putBoundsRectangle(Coordinate topLeft) {
        Coordinate bottomRight = new Coordinate(-topLeft.x(), -topLeft.y());
        putBoundsRectangle(topLeft, bottomRight);
    }

    public void putWall(Wall wall) {
        this.buildingList.add(wall);
    }

    /**
     * puts a normal house building (64*32).
     * 
     * @param topLeft the topleft of the house
     */
    public void putNormalHouse(Coordinate topLeft) {
        Building house = new Building(
            topLeft, Config.NORMAL_HOUSE_WIDTH, Config.NORMAL_HOUSE_HEIGHT);
        putBuilding(house);
    }

    //INTERACTIONS
    public void putDoor(Coordinate pos, String level, int entrance) {
        Door door = new Door(pos, level, entrance);
        this.interactionList.add(door);
    }

    public void putCutscene(Coordinate pos, String cutscene) {
        CutsceneClick cutsceneClick = new CutsceneClick(pos, cutscene);
        this.interactionList.add(cutsceneClick);
    }

    //OTHER
    public void putEntrance(int entrance, Coordinate pos) {
        this.enctrances.put(entrance, pos);
    }
    //putDad(Coordinate topLeft)
    //ELLER HELLER putCharacter(String name, Coordinate topLeft, AI ai)
    //AI kan være en CutsceneDirector?

    
}
