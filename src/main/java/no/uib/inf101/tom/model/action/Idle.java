package no.uib.inf101.tom.model.action;

import no.uib.inf101.tom.Config;

public class Idle extends Action{

    public Idle() {
        super();
        this.actionName = "idle";

        this.actionStateDuration = Config.STANDARD_ACTION_STATE_DURATION * 4;
        this.actionStateAmount = 2;
    
        this.looping = true;
        this.overridable = true;
    }

    @Override
    public void doAbility() {
        //do nothing
    }

    @Override
    public void stop() {
        this.actingCharacter.overrideAction(new StandingStill());
    }

    @Override
    protected void customizeActionToActingCharacter() {
        //do nothing
    }

    
}
