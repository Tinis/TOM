package no.uib.inf101.tom.model.action;

import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.character.ActionableCharacter;

public abstract class Action implements ViewableAction{
    protected String actionName;

    protected ActionableCharacter actingCharacter; 

    protected int actionFrame;
    protected int actionStateDuration; //the duration of a state measured in frames.
    protected int actionState;
    protected int actionStateAmount;
    protected int abilityState; //the actionstate at which an ability is performed.

    protected boolean looping;
    protected boolean overridable;

    protected Coordinate pointer;

    public Action() {
        this.actionName = "undefined action";

        this.actionFrame = 0;
        this.actionState = 0;
    }

    public void updateActionFrame() {
        actionFrame ++;
        if (actionFrame >= actionStateDuration) {
            updateActionState();
            actionFrame = 0;
        }
    }

    /**
     * updates the action to the current actionstate. 
     * Loops it around if it's loopable. Makes an ability happen if that's ideal. 
     */
    protected void updateActionState() {
        this.actionState++;
        if (this.actionState == this.abilityState) {
            doAbility();
        }
        if (this.actionState == this.actionStateAmount) {
            if (this.looping) {
                this.actionState = 0;
            } else {
                stop();
            }
        }
    }

    /**
     * performs the ability asociated with this action. 
     * Such as the actual punch of a punch action. 
     */
    protected abstract void doAbility();

    /**
     * update the fields based on the acting character. 
     * Is only to be called after the acting character has been defined. 
     */
    protected abstract void customizeActionToActingCharacter();

    /**
     * 
     * @return true if the action is overridable. false if not. 
     */
    public boolean isOverrideable() {
        return this.overridable;
    }

    /**
     * 
     * @param character the character that is performing this action. 
     */
    public void setActingCharacter(ActionableCharacter character) {
        this.actingCharacter = character;
        customizeActionToActingCharacter();
    }

    public void setPointer(Coordinate pointer) {
        this.pointer = pointer;
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
     * stops the current action by replacing it with another one. idling by default. 
     */
    public void stop() {
        this.actingCharacter.overrideAction(new Idle());
    }
}
