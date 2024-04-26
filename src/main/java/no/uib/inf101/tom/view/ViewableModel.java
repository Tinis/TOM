package no.uib.inf101.tom.view;

import java.util.ArrayList;

import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.CoordinatePointConverter;
import no.uib.inf101.tom.model.GameState;
import no.uib.inf101.tom.model.box.ViewableBox;
import no.uib.inf101.tom.model.character.ViewableCharacter;
import no.uib.inf101.tom.model.cutscene.Cutscene;
import no.uib.inf101.tom.model.screen.Screen;

public interface ViewableModel {

    /**
     * 
     * @return the coordinatePointConverter from the model.
     */
    public CoordinatePointConverter getCoordinateConverter();

    /**
     * 
     * @return the name of the levelimage as a string. This will be in this format:
     * "levelname_state"
     */
    public String getLevelName();

    /**
     * 
     * @return the current state of the game as a gamestate object.
     */
    public GameState getGameState();

    /**
     * 
     * @return a list of all current characters (including the player). 
     */
    public ArrayList<ViewableCharacter> getCharacters();

    /**
     * 
     * @return the player as a viewableCharacter.
     */
    public ViewableCharacter getPlayer();

    /**
     * 
     * @return a list of the past ten hitboxes as viewablebox objects. 
     */
    public ArrayList<ViewableBox> getHitBoxes();

    /**
     * 
     * @return a list of all hitboxes this frame as viewablebox objects.
     */
    public ArrayList<ViewableBox> getHitBoxesThisFrame();

    /**
     * 
     * @return a list of all current collisionboxes as viewablebox objects. 
     */
    public ArrayList<ViewableBox> getCollisionBoxes();

    /**
     * 
     * @return a list of all current interactions as viewablebox objects. 
     */
    public ArrayList<ViewableBox> getInteractions();

    /**
     * 
     * @return true if debug_mode is activated, false if not. 
     */
    public boolean isDebugging();

    /**
     * 
     * @return the position of the mouse currently as a coordinate object. 
     */
    public Coordinate getMousePos();

    /**
     * 
     * @return the current screen object. 
     */
    public Screen getScreen();

    /**
     * 
     * @return the current cutscene object.
     */
    public Cutscene getCutscene();

    /**
     * 
     * @return a list of the names of all active sounds (songs are sounds). 
     */
    public ArrayList<String> getActiveSounds();
}
