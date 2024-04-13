package no.uib.inf101.tom.model.action;

public class Walk extends Action{

    public Walk() {
        this.actionName = "walk";

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
