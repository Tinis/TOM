package no.uib.inf101.tom.model.action;

import no.uib.inf101.tom.Config;

public class Shoot extends Action{

    public Shoot() {
        super();
        this.actionName = "shoot";

        this.actionStateDuration = Config.SHOOTING_ACTION_STATE_DURATION;
        this.actionStateAmount = 2;
        this.abilityState = 1; 

        this.looping = false;
        this.overridable = false;
    }

    @Override
    protected void doAbility() {
        this.actingCharacter.fireProjectile(pointer);
    }

    @Override
    public void stop() {
        this.actingCharacter.overrideAction(new Walk());
    }

}
