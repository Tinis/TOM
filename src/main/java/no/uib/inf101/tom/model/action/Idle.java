package no.uib.inf101.tom.model.action;

public class Idle extends Action{

    public Idle() {
        this.actionName = "idle";

        // this.actionLength;
        // this.actionState;
    
        this.looping = true;
        this.overridable = true;
    }

    @Override
    public void updateActionState() {
        //TODO: something spritesomething.
    }
}
