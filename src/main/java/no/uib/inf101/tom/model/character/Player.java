package no.uib.inf101.tom.model.character;

import no.uib.inf101.tom.model.Coordinate;

public class Player extends Character{

    public Player(Coordinate pos) {
        super(pos);
        this.name = "tom";
    }

    @Override
    public String getName() {
        return this.name;
    }

}
