package no.uib.inf101.tom.model.screen;

import java.util.HashMap;

public class ScreenLoader {

    private HashMap<String, Screen> screens;

    public ScreenLoader() {
        this.screens = new HashMap<>();
        this.screens.put("mainmenu", mainMenu());
        this.screens.put("gameover", gameOver());
    }

    public Screen getScreen(String name) {
        return this.screens.get(name);
    }

    private Screen mainMenu() {
        Screen mainmenu = new Screen("mainmenu");
        return mainmenu;
    }

    private Screen gameOver() {
        Screen gameover = new Screen("gameover");
        return gameover;
    }
}
