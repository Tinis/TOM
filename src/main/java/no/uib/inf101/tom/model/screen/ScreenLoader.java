package no.uib.inf101.tom.model.screen;

import java.util.HashMap;

import no.uib.inf101.tom.Config;
import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.GameState;
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
        this.screens.put("chapterselection", chapterSelection());
        this.screens.put("pause", pause());

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
            this::loadIntro, "Start");
        mainmenu.putButton(startButton);
        Button howToPlayButton = new Button(new Coordinate(0, Config.BUTTON_MARGIN),
            this::loadHowToPlay, "How To Play");
        mainmenu.putButton(howToPlayButton);
        Button chapterSelectionButton = new Button(new Coordinate(0, Config.BUTTON_MARGIN * 2),
            this::loadChapterSelection, "Chapters");
        mainmenu.putButton(chapterSelectionButton);
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

    private Screen chapterSelection() {
        Screen chapterselection = new Screen("chapterselection");
        chapterselection.putButton(new Button(new Coordinate(0, -Config.BUTTON_MARGIN), 
            this::loadBedroom1, "Chapter 1"));     
        chapterselection.putButton(new Button(new Coordinate(-150, 90), 
            this::loadMainMenu, "Back"));
        return chapterselection;
    }

    private Screen pause() {
        Screen pause = new Screen("pause");
        Button continueButton = new Button(new Coordinate(0, 0),
            this::continueGame, "Continue");
        pause.putButton(continueButton);
        pause.putButton(new Button(new Coordinate(-150, 90), this::loadMainMenu, "Main Menu"));
        return pause;
    }

//////////////////
//BUTTON FUNCTIONS
//////////////////
    //loading levels and cutscenes
    private void loadDemoLevel() {
        this.model.loadLevel("demo", 1);
    }

    private void loadIntro() {
        this.model.loadCutscene("intro");
    }

    private void loadBedroom1() {
        this.model.loadLevel("bedroom1", 1);
    }

    //loading screens
    private void loadMainMenu() {
        this.model.setGameState(GameState.MAIN_MENU);
    }

    private void loadHowToPlay() {
        this.model.loadScreen("howtoplay");
    }

    private void loadChapterSelection() {
        this.model.loadScreen("chapterselection");
    }

    //other
    private void loadLastCheckPoint() {
        this.model.healPlayer();
        this.model.loadLevel(this.model.getLastLevelLoaded(), this.model.getLastEntrance());
    }

    private void continueGame() {
        this.model.setGameState(GameState.ACTIVE_GAME);
    }

    private void quit() {
        System.exit(0);
    }


}
