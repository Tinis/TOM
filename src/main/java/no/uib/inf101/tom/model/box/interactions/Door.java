package no.uib.inf101.tom.model.box.interactions;

import no.uib.inf101.tom.Config;
import no.uib.inf101.tom.model.Coordinate;

public class Door extends Item {
    private String level;
    private int entrance;

    public Door(Coordinate pos, double width, double height, String level, int entrance) {
        super(pos, width, height);
        this.level = level;
        this.entrance = entrance;
    }

    public Door(Coordinate pos, String level, int entrance) {
        this(pos, Config.DOOR_WIDTH, Config.DOOR_HEIGHT, level, entrance);
    }

    /**
     * this constructor sets the entrance to 1. 
     * @param pos
     * @param level
     */
    public Door(Coordinate pos, String level) {
        this(pos, level, 1);
    }

    @Override
    protected void wasClicked() {
        this.model.loadLevel(this.level, this.entrance);
    }

}
