package no.uib.inf101.tom.model.character;

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
     * @return the current action that the character is doing.
     */
    public ViewableAction getAction();

}
