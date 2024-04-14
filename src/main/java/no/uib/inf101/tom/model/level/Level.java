package no.uib.inf101.tom.model.level;

import no.uib.inf101.tom.model.character.Player;
import no.uib.inf101.tom.model.character.ViewableCharacter;

public class Level {
    private String name;
    private Player player;

    public Level(String name, Player player) {
        this.name = name;
        this.player = player;
    }

    public ViewableCharacter getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    //TODO: putNormalHouse(Coordinate topLeft)
    //putNightclubGuard(Coordinate topLeft)
    //putDad(Coordinate topLeft)
    //ELLER HELLER putCharacter(String name, Coordinate topLeft, AI ai)
    //AI kan være en CutsceneDirector?

    
}
