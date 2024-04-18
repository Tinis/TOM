package no.uib.inf101.tom.model.level;

import java.util.HashMap;

import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.character.NPC;
import no.uib.inf101.tom.model.character.Player;

public class LevelLoader {

    private HashMap<String, Level> levels;

    public LevelLoader() {
        this.levels = new HashMap<>();
        this.levels.put("demo", demo());
        this.levels.put("demo2", demo2());
    }

    public Level getLevel(String name) {
        return this.levels.get(name);
    }

    public void setLevel(String name, Level level) {
        this.levels.put(name, level);
    }

    private Level demo() {
        Level demo = new Level("demo", new Player(new Coordinate(0, 0)),
            new Coordinate(50, 50));
        demo.putNPC(new NPC(new Coordinate(50, -50), false));
        demo.putNightclubGuard(new Coordinate(-50, 0));
        demo.putNormalHouse(new Coordinate(200, -100));
        demo.putDoor(new Coordinate(235, -45), "demo2", 1);
        return demo;
    }

    private Level demo2() {
        Level demo2 = new Level("demo2", new Coordinate(0, 0));
        demo2.putNightclubGuard(new Coordinate(0, -60));
        return demo2; 
    }

    

}
