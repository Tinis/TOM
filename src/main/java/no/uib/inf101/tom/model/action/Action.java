package no.uib.inf101.tom.model.action;

public abstract class Action {
    protected String actionName;

    protected int actionLength;
    protected int actionState;

    protected boolean looping;
    protected boolean overridable;

    public abstract void updateActionState();
}
