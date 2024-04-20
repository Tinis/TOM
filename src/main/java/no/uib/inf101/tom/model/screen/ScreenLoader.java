package no.uib.inf101.tom.model.screen;

import java.util.HashMap;

import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.TomModel;

public class ScreenLoader {

    private HashMap<String, Screen> screens;
    //some buttons need model access. 
    private TomModel model;

    public ScreenLoader(TomModel model) {
        this.screens = new HashMap<>();
        this.screens.put("mainmenu", mainMenu());
        this.screens.put("gameover", gameOver());

        this.model = model;
    }

    public Screen getScreen(String name) {
        return this.screens.get(name);
    }

    private Screen mainMenu() {
        Screen mainmenu = new Screen("mainmenu");
        Button startButton = new Button(new Coordinate(0, 0),
            this::loadDemoLevel, "Start");
        mainmenu.putButton(startButton);
        return mainmenu;
    }

    private void loadDemoLevel() {
        this.model.loadLevel("demo", 1);
    }

    private Screen gameOver() {
        Screen gameover = new Screen("gameover");
        return gameover;
    }
}
