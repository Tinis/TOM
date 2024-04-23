package no.uib.inf101.tom;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.io.File;

import no.uib.inf101.tom.model.Direction;

public class Config {

//-----VIEW RELATED
    //SCREEN CONFIGURATIONS
    public static final int PICTURE_TILE_SIZE = 16;
    public static final int SCALING = 3;
    public static final int ACTUAL_TILE_SIZE = PICTURE_TILE_SIZE * SCALING; //48
    public static final double SMALL_MARGIN = 10;
    public static final double MEDIUM_MARGIN = 50;
    public static final double BUTTON_MARGIN = 35;
    public static final double WALL_THICKNESS = 1;
    
    //GAMELOOP CONFIGURATIONS
    public static final int FPS = 60;
    public static final boolean PRINT_ACTUAL_FPS = true;

    //SWING CONSTANTS (THESE ARE UNCHANGEABLE BECAUSE THEY ARE SWING-RELATED)
    public static final int SWING_COMPONENT_MAX_WIDTH = 1280;
    public static final int SWING_COMPONENT_MAX_HEIGHT = 720;
    public static final Point2D SCREEN_CENTER = new Point2D.Double(
        SWING_COMPONENT_MAX_WIDTH/2, SWING_COMPONENT_MAX_HEIGHT/2);

    //WINDOW CONFIGURATIONS
    public static final String WINDOW_TITLE = "Tom";
    public static final Dimension WINDOW_DIMENSION = new Dimension(1280, 720);
    public static final boolean WINDOW_RESIZABLE = false; //should be false (has option for debugpurposes)
    public static final boolean WINDOW_FULLSCREEN = true; //should be true
    public static final boolean DOUBLE_BUFFERED = true; //should be true (reduces lag)

    //COLORS
    public static final Color BACKGROUND_COLOR = Color.BLACK;
    public static final Color DEBUG_TEXT_COLOR = Color.WHITE;
    public static final Color PLAYER_DEBUG_COLOR = new Color(0, 255, 0, 128);
    public static final Color NPC_DEBUG_COLOR = new Color(255, 255, 0, 128);
    public static final Color HIT_DEBUG_COLOR = new Color(255, 0, 0, 128);
    public static final Color COLLISION_DEBUG_COLOR = new Color(0, 0, 255, 128);
    public static final Color INTERACTION_DEBUG_COLOR = new Color(255, 0, 128, 128);
    public static final Color BUTTON_TEXT_COLOR = Color.WHITE;

    //FONTS
    public static final Font DEBUG_TEXT_FONT = new Font("Monospaced", Font.BOLD, 14);
    public static final String BUTTON_FONT_PATH = "fonts/CurvyPixelText.ttf";
    public static final Float BUTTON_FONT_SIZE = 14f;

    //OTHER
    public static final double BUTTON_WIDTH = 87;
    public static final double BUTTON_HEIGHT = 30;

    
//-----CONTROLLER RELATED
    //KEYS
    public static final int KILL_KEY = KeyEvent.VK_F9;
    public static final int DEBUG_KEY = KeyEvent.VK_F3;

    public static final int PUNCH_KEY = KeyEvent.VK_Q;
    public static final int BLOCK_KEY = KeyEvent.VK_W;
    public static final int DASH_KEY = KeyEvent.VK_E;
    public static final int HEAVY_KEY = KeyEvent.VK_R;

    public static final int PAUSE_KEY = KeyEvent.VK_ESCAPE;



//-----MODEL RELATED
    public static final String START_UP_STATE = "main"; //options: "demo", "main"

    //CHARACTER PROPERTIES
    public static final double CHILD_CHARACTER_HEIGHT = 16;
    public static final double STANDARD_CHARACTER_HEIGHT = 32;
    public static final double STANDARD_CHARACTER_WIDTH = 16;
    //CHARACTER STATS
    public static final double STANDARD_SPEED = 1.5;
    public static final double PLAYER_SPEED = 1.8;
    public static final double DASH_SPEED_SCALER = 2;
    public static final Direction STANDARD_DIRECTION = Direction.SOUTH;
    public static final double STANDARD_PUNCH_REACH = 30;
    public static final double STANDARD_PUNCH_WIDTH = 10;
    public static final int STANDARD_MAX_HEALTH = 100; 
    public static final int STANDARD_STRENGTH = 0; //should be 10
    public static final int PLAYER_STRENGTH = 35;

    public static final int STANDARD_ACTION_STATE_DURATION = 10;
    //BUILDINGS AND INTERACTIONS
    public static final int NORMAL_HOUSE_WIDTH = 16*10;
    public static final int NORMAL_HOUSE_HEIGHT = 16*4;
    public static final int DOOR_WIDTH = 16;
    public static final int DOOR_HEIGHT = 16;
    public static final double INTERACTION_RANGE = 64;

    //EXTRA
    public static final double COORDINATE_ALMOSTEQUALS_PRECISION = 0.1;
    public static final int STANDARD_CUTSCENE_STATE_DURATION = 5; //fps: 12 (5 frame updates at 60fps)
    public static final boolean DISABLE_CUTSCENES = false; 
    //sets cutscene stateAmount to zero (meaning the cutscenes are skipped). And doesn't load them. 
}
