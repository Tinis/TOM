package no.uib.inf101.tom.model.action;

public abstract class Action {
    private String actionName;

    private int actionLength;
    private int actionState;

    private boolean looping;
    private boolean overridable;

    public abstract void updateActionState();
}
