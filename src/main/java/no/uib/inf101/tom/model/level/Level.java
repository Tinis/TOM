package no.uib.inf101.tom.model.level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import no.uib.inf101.tom.model.character.NPC;
import no.uib.inf101.tom.model.character.Player;
import no.uib.inf101.tom.model.character.ViewableCharacter;

public class Level {
    private String name;
    private Player player;
    private ArrayList<NPC> npcList;

    public Level(String name, Player player) {
        this.name = name;
        this.player = player;
        this.npcList = new ArrayList<>();
    }

    public ViewableCharacter getPlayer() {
        return this.player;
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
