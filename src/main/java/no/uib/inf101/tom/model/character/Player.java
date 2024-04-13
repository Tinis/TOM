package no.uib.inf101.tom.model.character;

import no.uib.inf101.tom.model.Coordinate;

public class Player extends Character{

    public Player(Coordinate pos, double width, double height) {
        super(pos, width, height);
        this.name = "tom";
    }

    @Override
    public String getName() {
        return this.name;
    }

}
