package no.uib.inf101.tom.model.character;

import no.uib.inf101.tom.model.action.Action;

public interface ActionableCharacter {


    /**
     * attempt to start a new action (it will not start if its current action is not overridable). 
     * @param action the new action that will be started. 
     */
    public void startAction(Action action);

    /**
     * starts a new action (will override any action).
     * @param action the new action that will be started. 
     */
    public void overrideAction(Action action);
}
