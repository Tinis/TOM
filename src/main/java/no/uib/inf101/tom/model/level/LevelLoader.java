package no.uib.inf101.tom.model.level;

import java.util.HashMap;

import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.box.Building;
import no.uib.inf101.tom.model.box.Wall;
import no.uib.inf101.tom.model.character.NPC;
import no.uib.inf101.tom.model.character.Player;
import no.uib.inf101.tom.model.character.ai.NullAI;

public class LevelLoader {

    private HashMap<String, Level> levels;

    public LevelLoader() {
        this.levels = new HashMap<>();
        this.levels.put("demo", demo());
        this.levels.put("demo2", demo2());
        this.levels.put("bedroom1", bedroom1());
        this.levels.put("livingroom1", livingroom1());
        this.levels.put("city1", city1());
        this.levels.put("hallway1", hallway1());
        this.levels.put("happyapartment1", happyapartment1());
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
        demo.putEntrance(2, new Coordinate(235, -45 + 16));
        return demo;
    }

    private Level demo2() {
        Level demo2 = new Level("demo2", new Coordinate(0, 0));
        demo2.putNightclubGuard(new Coordinate(0, -60));
        demo2.putDoor(new Coordinate(0, 0), "demo", 2);
        return demo2; 
    }

    private Level bedroom1() {
        Level bedroom1 = new Level("bedroom1", new Player(new Coordinate(9, -32)),
            new Coordinate(-9, 34));
        
        bedroom1.putBoundsRectangle(new Coordinate(-33.5, -48));
        bedroom1.putDoor(new Coordinate(-25, 40), "livingroom1", 1);
        return bedroom1;
    }

    private Level livingroom1() {
        Level livingroom1 = new Level("livingroom1", new Coordinate(20, -70));
        livingroom1.putBoundsRectangle(new Coordinate(-30, -95));
        livingroom1.putDoor(new Coordinate(24, -90), "bedroom1", 1);
        livingroom1.putDoor(new Coordinate(-6.5, 86.5), "city1", 1);
        livingroom1.putDoor(new Coordinate(6.5, 86.5), "city1", 1);
        livingroom1.putEntrance(2, new Coordinate(6.5, 76.5));
        return livingroom1;
    }

    private Level city1() {
        Level city1 = new Level("city1", new Coordinate(-350.5, -99.5));
        //add the doors
        city1.putDoor(new Coordinate(-352.7, -128.5), "livingroom1", 2);
        city1.putDoor(new Coordinate(-96, 285.5), "hallway1", 1);
        //add the bounds
        city1.putBoundsRectangle(new Coordinate(-400, -400));
        city1.putBlock(new Coordinate(-400, -400));
        city1.putBlock(new Coordinate(-145, -400));
        city1.putBlock(new Coordinate(16, -400));
        city1.putBlock(new Coordinate(272, -400));
        city1.putBlock(new Coordinate(-400, 14));
        city1.putBlock(new Coordinate(-145, 14));
        city1.putBlock(new Coordinate(16, 14));
        city1.putBlock(new Coordinate(272, 14));
        city1.putBuilding(new Building(new Coordinate(-272, -400), new Coordinate(-246, -112)));
        //add walls
        city1.putWall(new Wall(new Coordinate(-85, -114), new Coordinate(-85, 400)));
        //add npcs
        city1.putBully(new Coordinate(-228, -370));
        city1.putBully(new Coordinate(-228, -340));
        city1.putBully(new Coordinate(-228, -310));
        return city1;
    }

    private Level hallway1() {
        Level hallway1 = new Level("hallway1", new Coordinate(0, 69));
        hallway1.putDoor(new Coordinate(0, -69), "happyapartment1", 1);
        return hallway1;
    }

    private Level happyapartment1() {
        Level happyapartment1 = new Level("happyapartment1", new Coordinate(0, 0));
        //put some bullies
        happyapartment1.putBully(new Coordinate(-120, -100));
        happyapartment1.putBully(new Coordinate(120, -100));
        happyapartment1.putBully(new Coordinate(0, -120));
        happyapartment1.putBully(new Coordinate(-120, 0));
        happyapartment1.putBully(new Coordinate(120, 0));
        return happyapartment1;
    }

    private Level city2() {
        Level city2 = new Level("city2", new Coordinate(0, 0));
        return city2;
    }

    private Level nightclubBossFight() {
        Level nighclubBossFight = new Level("nighclubBossFight", new Coordinate(0, 0));
        nighclubBossFight.putNightclubBoss(new Coordinate(0, -100));
        //TODO: finish this
        return null;
    }

    

}
