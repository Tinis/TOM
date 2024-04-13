package no.uib.inf101.tom;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

public class Config {

    //SCREEN CONFIGURATIONS
    public static final int PICTURE_TILE_SIZE = 16;
    public static final int SCALING = 3;
    public static final int ACTUAL_TILE_SIZE = PICTURE_TILE_SIZE * SCALING; //48

    //SWING CONSTANTS (THESE ARE UNCHANGEABLE BECAUSE THEY ARE SWING-RELATED)
    public static final int SWING_COMPONENT_MAX_WIDTH = 1280;
    public static final int SWING_COMPONENT_MAX_HEIGHT = 720;

    //WINDOW CONFIGURATIONS
    public static final String WINDOW_TITLE = "Tom";
    public static final Dimension WINDOW_DIMENSION = new Dimension(1280, 720);
    public static final boolean WINDOW_RESIZABLE = false; //should be false (has option for debugpurposes)
    public static final boolean WINDOW_FULLSCREEN = true; //should be true
    public static final boolean DOUBLE_BUFFERED = true;

    //KEYS
    public static final int KILL_KEY = KeyEvent.VK_F9;

    //COLORS
    public static final Color BACKGROUND_COLOR = Color.BLACK;
    public static final Color PLAYER_DEBUG_COLOR = new Color(0, 128, 0, 255);
}
