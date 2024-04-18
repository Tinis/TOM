package no.uib.inf101.tom.model.character.ai;

import no.uib.inf101.tom.model.action.ActionCommand;
import no.uib.inf101.tom.model.character.CharacterViewableModel;

/**
 * An ai that dashes from side to side while shooting the player. 
 */
public class BasicShootingAI extends AICommander{

    public BasicShootingAI() {
        this.state = 0;
        this.stateAmount = 120; //one second worth of states.
    }

    
    @Override
    protected ActionCommand getActionCommandForState(CharacterViewableModel model, int state) {
        if (state == 119) {
            // shoot
        } else if (state == 30) {
            // dash to the left of the player
        } else if (state == 60) {
            //dash to the right of the player
        }
        return null;
    }

    @Override
    public String getCommanderName() {
        return "basic shooting ai";
    }
}
