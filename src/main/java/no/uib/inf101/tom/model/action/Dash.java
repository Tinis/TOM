package no.uib.inf101.tom.model.action;

import no.uib.inf101.tom.Config;

public class Dash extends Action{

    public Dash() {
        super();
        this.actionName = "dash";

        this.actionStateDuration = Config.STANDARD_ACTION_STATE_DURATION;
        this.actionStateAmount = 3;
        this.abilityState = 0;

        this.isMovingAction = true;

        this.looping = false;
        this.overridable = false;

    }

    @Override
    protected void doAbility() {
        this.actingCharacter.scaleSpeed(Config.DASH_SPEED_SCALER);
    }

    @Override
    public void stop() {
        this.actingCharacter.scaleSpeed(1 / Config.DASH_SPEED_SCALER);
        this.actingCharacter.overrideAction(new Walk());
    }

}

