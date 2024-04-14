package no.uib.inf101.tom.model.level;

import java.util.HashMap;

import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.character.Player;

public class LevelLoader {

    private HashMap<String, Level> levels;

    public LevelLoader() {
        this.levels = new HashMap<>();
        this.levels.put("demo", demo());
    }

    public Level loadLevel(String name) {
        return this.levels.get(name);
    }

    public void setLevel(String name, Level level) {
        this.levels.put(name, level);
    }

    private Level demo() {
        return new Level("demo", new Player(new Coordinate(0, 0)));
    }

    

}
