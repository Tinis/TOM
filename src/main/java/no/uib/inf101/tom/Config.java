package no.uib.inf101.tom;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import no.uib.inf101.tom.model.Direction;

public class Config {

    //-----VIEW RELATED
    //SCREEN CONFIGURATIONS
    public static final int PICTURE_TILE_SIZE = 16;
    public static final int SCALING = 3;
    public static final int ACTUAL_TILE_SIZE = PICTURE_TILE_SIZE * SCALING; //48
    public static final double SMALL_MARGIN = 10;
    public static final double MEDIUM_MARGIN = 50;
    
    //GAMELOOP CONFIGURATIONS
    public static final int FPS = 60;
    public static final boolean PRINT_ACTUAL_FPS = true;

    //SWING CONSTANTS (THESE ARE UNCHANGEABLE BECAUSE THEY ARE SWING-RELATED)
    public static final int SWING_COMPONENT_MAX_WIDTH = 1280;
    public static final int SWING_COMPONENT_MAX_HEIGHT = 720;

    //WINDOW CONFIGURATIONS
    public static final String WINDOW_TITLE = "Tom";
    public static final Dimension WINDOW_DIMENSION = new Dimension(1280, 720);
    public static final boolean WINDOW_RESIZABLE = false; //should be false (has option for debugpurposes)
    public static final boolean WINDOW_FULLSCREEN = true; //should be true
    public static final boolean DOUBLE_BUFFERED = true;

    //COLORS
    public static final Color BACKGROUND_COLOR = Color.BLACK;
    public static final Color PLAYER_DEBUG_COLOR = new Color(0, 128, 0, 128);
    public static final Color DEBUG_TEXT_COLOR = Color.WHITE;

    

    //-----CONTROLLER RELATED
    //KEYS
    public static final int KILL_KEY = KeyEvent.VK_F9;
    public static final int DEBUG_KEY = KeyEvent.VK_F3;



    //-----MODEL RELATED
    //CHARACTER STATS
    public static final double STANDARD_CHARACTER_HEIGHT = 32;
    public static final double STANDARD_CHARACTER_WIDTH = 16;
    public static final double STANDARD_SPEED = 1.5;
    public static final Direction STANDARD_DIRECTION = Direction.SOUTH;

    public static final int STANDARD_ACTION_STATE_DURATION = 10;

    public static final double COORDINATE_ALMOSTEQUALS_PRECISION = 0.5;
}
