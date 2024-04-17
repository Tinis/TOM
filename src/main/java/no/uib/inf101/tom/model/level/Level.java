package no.uib.inf101.tom.model.level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import no.uib.inf101.tom.Config;
import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.GameState;
import no.uib.inf101.tom.model.buildingsandinteractions.Building;
import no.uib.inf101.tom.model.character.NPC;
import no.uib.inf101.tom.model.character.Player;
import no.uib.inf101.tom.model.character.ViewableCharacter;
import no.uib.inf101.tom.model.character.ai.BasicPunchingAI;

public class Level {
    private String name;
    private Player player;
    private ArrayList<NPC> npcList;
    private ArrayList<Building> buildingList;
    private HashMap<Integer, Coordinate> enctrances;
    private GameState state;

    public Level(String name, Player player, Coordinate enctrance1, GameState initialState) {
        this.name = name;
        this.state = initialState;
        this.player = player;
        this.npcList = new ArrayList<>();
        this.buildingList = new ArrayList<>();
        this.enctrances = new HashMap<>();
        this.enctrances.put(1, enctrance1);
    }

    public Level(String name, Player player, Coordinate enctrance1) {
        this(name, player, enctrance1, GameState.ACTIVE_GAME);
    }

    public Level(String name, Player player) {
        this(name, player, new Coordinate(0, 0));
    }
/////////
//GETTERS
/////////
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
    public ArrayList<Building> getBuildings() {
        return this.buildingList;
    }


////////////////////////
//PUT THINGS ON THE LEVEL
////////////////////////
    public void setPlayer(Player player) {
        this.player = player;
    }

    public void putNPC(NPC npc) {
        this.npcList.add(npc);
    }

    public void putNightclubGuard(Coordinate pos) {
        NPC nightclubGuard = new NPC(pos, new BasicPunchingAI(), false, "nightclubGuard");
        putNPC(nightclubGuard);
    }

    public void putBuilding(Building building) {
        this.buildingList.add(building);
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
    //putDad(Coordinate topLeft)
    //ELLER HELLER putCharacter(String name, Coordinate topLeft, AI ai)
    //AI kan være en CutsceneDirector?

    
}
