package no.uib.inf101.tom.model.action;

import no.uib.inf101.tom.Config;

public class Walk extends Action{

    public Walk() {
        super();
        this.actionName = "walk";

        this.actionStateDuration = Config.STANDARD_ACTION_STATE_DURATION;
        this.actionStateAmount = 4;
    
        this.looping = true;
        this.overridable = true;
    }

    @Override
    public void doAbility() {
        //do nothing
    }


}
