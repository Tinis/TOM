package no.uib.inf101.tom;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

public class Config {

    //WINDOW CONFIGURATIONS
    public static final String WINDOW_TITLE = "Tom";
    public static final Dimension WINDOW_DIMENSION = new Dimension(1920, 1080);
    public static final boolean WINDOW_RESIZABLE = true; //should be false
    public static final boolean WINDOW_FULLSCREEN = true; //should be true

    //KEYS
    public static final int KILL_KEY = KeyEvent.VK_F9;

    //COLORS
    public static final Color BACKGROUND_COLOR = Color.BLACK;
}
