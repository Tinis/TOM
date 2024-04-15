package no.uib.inf101.tom.model.character;

import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.action.Action;

public interface ActionableCharacter {


    /**
     * attempt to start a new action (it will not start if its current action is not overridable). 
     * @param action the new action that will be started. 
     * @return will return true if a new action was started. false if not. 
     */
    public boolean startAction(Action action);

    /**
     * starts a new action (will override any action).
     * @param action the new action that will be started. 
     */
    public void overrideAction(Action action);

    /**
     * 
     * @return the pos of the character. (this is used in calculating an actions effect). 
     */
    public Coordinate getPos();

    /**
     * 
     * @return the reach of the character.
     */
    public double getReach();
}
