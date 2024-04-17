package no.uib.inf101.tom.model.character.ai;

import no.uib.inf101.tom.model.action.ActionCommand;
import no.uib.inf101.tom.model.action.Punch;
import no.uib.inf101.tom.model.action.Walk;
import no.uib.inf101.tom.model.character.CharacterViewableModel;

/**
 * Walks toward the character while punching repeatedly. 
 * The walk destination is only set onto the character every few seconds. 
 */
public class BasicPunchingAI extends AICommander{

    public BasicPunchingAI() {
        this.state = 0;
        this.stateAmount = 60; //one second worth of states.
    }

    
    @Override
    protected ActionCommand getActionCommandForState(CharacterViewableModel model, int state) {
        if (state == 30) {
            return new ActionCommand(new Punch(), model.getPlayerCenter());
        } else if (state == 1) {
            return new ActionCommand(new Walk(), model.getPlayerCenter());
        } else {
            return null;
        }
    }

    @Override
    public String getCommanderName() {
        return "basic punching ai";
    }
    
}
