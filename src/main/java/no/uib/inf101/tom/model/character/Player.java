package no.uib.inf101.tom.model.character;

import no.uib.inf101.tom.Config;
import no.uib.inf101.tom.model.Coordinate;

public class Player extends Character{

    public Player(Coordinate pos) {
        super(pos, Config.STANDARD_CHARACTER_WIDTH, Config.CHILD_CHARACTER_HEIGHT);
        this.name = "tom";
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getCommander() {
        return "player";
    }

    

}
