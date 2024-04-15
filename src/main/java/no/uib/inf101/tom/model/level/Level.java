package no.uib.inf101.tom.model.level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.GameState;
import no.uib.inf101.tom.model.character.NPC;
import no.uib.inf101.tom.model.character.Player;
import no.uib.inf101.tom.model.character.ViewableCharacter;

public class Level {
    private String name;
    private Player player;
    private ArrayList<NPC> npcList;
    private HashMap<Integer, Coordinate> enctrances;
    private GameState state;

    public Level(String name, Player player, Coordinate enctrance1, GameState initialState) {
        this.name = name;
        this.state = initialState;
        this.player = player;
        this.npcList = new ArrayList<>();
        this.enctrances = new HashMap<>();
        this.enctrances.put(1, enctrance1);
    }

    public Level(String name, Player player, Coordinate enctrance1) {
        this(name, player, enctrance1, GameState.ACTIVE_GAME);
    }

    public Level(String name, Player player) {
        this(name, player, new Coordinate(0, 0));
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
     * @return the list of this level containing the npcs. 
     * (Meaning if it is changed the list in the level object also changes)
     */
    public ArrayList<NPC> getNpcs() {
        return this.npcList;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void putNPC(NPC npc) {
        this.npcList.add(npc);
    }

    //TODO: putNormalHouse(Coordinate topLeft)
    //putNightclubGuard(Coordinate topLeft)
    //putDad(Coordinate topLeft)
    //ELLER HELLER putCharacter(String name, Coordinate topLeft, AI ai)
    //AI kan være en CutsceneDirector?

    
}
