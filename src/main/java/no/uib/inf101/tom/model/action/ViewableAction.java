package no.uib.inf101.tom.model.action;

public interface ViewableAction {

    /**
     * 
     * @return the name of the current action.
     */
    public String getActionName();

    /**
     * 
     * @return the state of the current action.
     */
    public int getActionState();
}
