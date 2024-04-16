package no.uib.inf101.tom.model.character;

import no.uib.inf101.tom.model.Direction;
import no.uib.inf101.tom.model.action.ViewableAction;
import no.uib.inf101.tom.model.box.ViewableBox;

public interface ViewableCharacter {

    /**
     * 
     * @return the box of the character as a ViewableBox object. 
     */
    public ViewableBox getBox();

    /**
     * 
     * @return the PlaneVector object of the character as a string on this format: [x, y]. with 3 decimalplaces. 
     */
    public String getViewableMovementVector();

    /**
     * 
     * @return the direction that the character is facing (as a Direction object).
     */
    public Direction getFacing();

    /**
     * 
     * @return the current action that the character is doing.
     */
    public ViewableAction getViewableAction();

    /**
     * 
     * @return the name of the current commander (such as player or the name of the current ai.
     * For debug purposes. 
     */
    public String getCommander();

    /**
     * 
     * @return a String with Good if the character is good. Bad if not. 
     */
    public String getGoodOrBad();

}
