package no.uib.inf101.tom.model.action;

import no.uib.inf101.tom.model.character.ActionableCharacter;

public abstract class Action implements ViewableAction{
    protected String actionName;

    protected ActionableCharacter actingCharacter; 

    protected int actionLength;
    protected int actionFrame;
    protected int actionState;

    protected boolean looping;
    protected boolean overridable;


    public abstract void updateActionFrame();

    public boolean isOverrideable() {
        return this.overridable;
    }

    public void setActingCharacter(ActionableCharacter character) {
        this.actingCharacter = character;
    }

    @Override
    public String getActionName() {
        return this.actionName;
    }

    @Override
    public int getActionState() {
        return this.actionState;
    }

    /**
     * checks if two actions are the same type (have the same names). Two walk actions are the same. 
     * @param action the action to check. 
     * @return true if the actions are the same. 
     */
    public boolean isSameActionAs(Action action) {
        return (this.getActionName().equals(action.getActionName()));
    }

    /**
     * stops the current action by replacing it with another one. Often idling. 
     */
    public void stop() {
        this.actingCharacter.overrideAction(new Idle());
    }
}
