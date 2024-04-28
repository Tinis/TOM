package no.uib.inf101.tom.model.action;

import no.uib.inf101.tom.Config;

public class Dash extends Action{

    private double actualSpeed;

    public Dash() {
        super();
        this.actionName = "dash";

        this.actionStateDuration = Config.STANDARD_ACTION_STATE_DURATION;
        this.actionStateAmount = 3;
        this.abilityState = 1;

        this.isMovingAction = true;

        this.looping = false;
        this.overridable = false;

    }

    @Override
    public void setActingCharacter(ActionableCharacter character) {
        this.actingCharacter = character;
        this.actualSpeed = this.actingCharacter.getSpeed();
    }

    @Override
    protected void doAbility() {
        this.actingCharacter.scaleSpeed(Config.DASH_SPEED_SCALER);
        this.actingCharacter.setDestination(this.pointer);
    }

    @Override
    public void stop() {
        this.actingCharacter.setSpeed(this.actualSpeed);
        this.actingCharacter.overrideAction(new Idle());
    }

}

