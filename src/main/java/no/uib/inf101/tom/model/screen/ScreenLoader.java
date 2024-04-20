package no.uib.inf101.tom.model.screen;

import java.util.HashMap;

import no.uib.inf101.tom.Config;
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
        this.screens.put("howtoplay", howToPlay());

        this.model = model;
    }

    public Screen getScreen(String name) {
        return this.screens.get(name);
    }

/////////
//SCREENS
/////////
    private Screen mainMenu() {
        Screen mainmenu = new Screen("mainmenu");
        Button startButton = new Button(new Coordinate(0, 0),
            this::loadDemoLevel, "Start");
        mainmenu.putButton(startButton);
        Button howToPlayButton = new Button(new Coordinate(0, Config.BUTTON_MARGIN),
            this::loadHowToPlay, "How To Play");
        mainmenu.putButton(howToPlayButton);
        Button quitButton = new Button(new Coordinate(150, 90),
            this::quit, "Quit");
        mainmenu.putButton(quitButton);
        return mainmenu;
    }

    private Screen gameOver() {
        Screen gameover = new Screen("gameover");
        gameover.putButton(new Button(
            new Coordinate(Config.BUTTON_WIDTH, 65), this::loadMainMenu, "Main menu"
        ));
        gameover.putButton(new Button(
            new Coordinate(-Config.BUTTON_WIDTH, 65), this::loadLastCheckPoint, "Retry"));
        return gameover;
    }

    private Screen howToPlay() {
        Screen howtoplay = new Screen("howtoplay");
        howtoplay.putButton(new Button(new Coordinate(-150, 90), this::loadMainMenu, "Back"));
        return howtoplay;
    }

//////////////////
//BUTTON FUNCTIONS
//////////////////
    //loading levels
    private void loadDemoLevel() {
        this.model.loadLevel("demo", 1);
    }

    //loading screens
    private void loadMainMenu() {
        this.model.loadScreen("mainmenu");
    }

    private void loadHowToPlay() {
        this.model.loadScreen("howtoplay");
    }

    //other
    private void loadLastCheckPoint() {
        this.model.healPlayer();
        this.model.loadLevel(this.model.getLastLevelLoaded(), this.model.getLastEntrance());
    }
    private void quit() {
        System.exit(0);
    }


}
