package no.uib.inf101.tom.model.action;

import no.uib.inf101.tom.Config;
import no.uib.inf101.tom.model.box.HitBox;

public class Punch extends Action{

    public Punch() {
        super();
        this.actionName = "punch";

        this.actionStateDuration = Config.STANDARD_ACTION_STATE_DURATION;
        this.actionStateAmount = 2;
        this.abilityState = 1; 

        this.looping = false;
        this.overridable = false;

        this.width = Config.STANDARD_PUNCH_WIDTH;
    }

    @Override
    protected void doAbility() {
        HitBox hit = new HitBox(
            this.actingCharacter.getPos(), this.pointer, 
            this.actingCharacter.getReach(), this.width);
        this.actingCharacter.dealHit(hit);
    }

    @Override
    public void stop() {
        this.actingCharacter.overrideAction(new Walk());
    }

    

}
