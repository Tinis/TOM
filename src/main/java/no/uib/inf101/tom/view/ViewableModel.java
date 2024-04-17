package no.uib.inf101.tom.view;

import java.util.ArrayList;

import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.CoordinatePointConverter;
import no.uib.inf101.tom.model.GameState;
import no.uib.inf101.tom.model.box.ViewableBox;
import no.uib.inf101.tom.model.character.ViewableCharacter;

public interface ViewableModel {

    /**
     * 
     * @return the coordinatePointConverter from the model.
     */
    public CoordinatePointConverter getCoordinateConverter();

    /**
     * 
     * @return the name of the level as a string.
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
     * @return a list of all current hitboxes as viewablebox objects. 
     */
    public ArrayList<ViewableBox> getHitBoxes();

    /**
     * 
     * @return a list of all current collisionboxes as viewablebox objects. 
     */
    public ArrayList<ViewableBox> getCollisionBoxes();

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
}
