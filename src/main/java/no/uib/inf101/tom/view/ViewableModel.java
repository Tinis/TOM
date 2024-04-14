package no.uib.inf101.tom.view;

import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.CoordinatePointConverter;
import no.uib.inf101.tom.model.GameState;
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

    public ViewableCharacter getPlayer();
    //might replace with ArrayList<ViewableCharacter> getCharacters() later;

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
