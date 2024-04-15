package no.uib.inf101.tom.model.action;

import no.uib.inf101.tom.Config;
import no.uib.inf101.tom.model.Coordinate;

public class Punch extends Action{

    private double reach;
    private double width;

    public Punch() {
        super();
        this.actionName = "punch";

        this.actionStateDuration = Config.STANDARD_ACTION_STATE_DURATION;
        this.actionStateAmount = 2;

        this.looping = false;
        this.overridable = false;

        this.width = Config.STANDARD_PUNCH_WIDTH;
    }

    @Override
    protected void doAbility() {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void customizeActionToActingCharacter() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void stop() {
        this.actingCharacter.overrideAction(new Walk());
    }

    

}
