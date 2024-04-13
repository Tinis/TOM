package no.uib.inf101.tom.model.action;

import no.uib.inf101.tom.model.character.ActionableCharacter;

public abstract class Action {
    protected String actionName;

    protected ActionableCharacter actingCharacter; 

    protected int actionLength;
    protected int actionState;

    protected boolean looping;
    protected boolean overridable;

    public abstract void updateActionState();

    public boolean isOverrideable() {
        return this.overridable;
    }

    public String getName() {
        return this.actionName;
    }

    public boolean isSameActionAs(Action action) {
        return (this.getName().equals(action.getName()));
    }
}
